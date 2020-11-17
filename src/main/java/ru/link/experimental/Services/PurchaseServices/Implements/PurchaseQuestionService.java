package ru.link.experimental.Services.PurchaseServices.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ru.link.experimental.DTO.*;
import ru.link.experimental.Entities.PurchaseQuestionEntity;
import ru.link.experimental.Exceptions.PageExceptions.*;
import ru.link.experimental.Exceptions.QuestionException;
import ru.link.experimental.Repositories.*;
import ru.link.experimental.Services.PurchaseServices.PurchaseQuestionServiceInterface;
import ru.link.experimental.Validate.*;

import java.util.*;

@Service
public class PurchaseQuestionService implements PurchaseQuestionServiceInterface {

    private final PurchaseQuestionRepository questionRepository;
    private final PurchaseQuestionValidator questionValidator;

    @Autowired
    public PurchaseQuestionService(PurchaseQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;

        questionValidator = new PurchaseQuestionValidator();
    }

    @Override
    public void create(UUID purchaseId, String name, String content) throws QuestionException {
        ValidatorResponse validatorResponse;

        if ((validatorResponse = questionValidator.validatePurchaseId(purchaseId)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new QuestionException(validatorResponse);
        }

        if ((validatorResponse = questionValidator.validateName(name)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new QuestionException(validatorResponse);
        }

        if ((validatorResponse = questionValidator.validateContent(content)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new QuestionException(validatorResponse);
        }

        PurchaseQuestionEntity questionEntity = new PurchaseQuestionEntity(purchaseId, name, content);

        questionRepository.save(questionEntity);
    }

    @Override
    public void update(UUID id, String name, String content) throws QuestionException {
        ValidatorResponse validatorResponse;

        if ((validatorResponse = questionValidator.validateQuestionId(id)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new QuestionException(validatorResponse);
        }

        if ((validatorResponse = questionValidator.validateName(name)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new QuestionException(validatorResponse);
        }

        if ((validatorResponse = questionValidator.validateContent(content)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new QuestionException(validatorResponse);
        }

        PurchaseQuestionEntity questionEntity = questionRepository.getOne(id);
        questionEntity.setName(name);
        questionEntity.setContent(content);

        questionRepository.saveAndFlush(questionEntity);
    }

    @Override
    public void delete(UUID id) throws QuestionException {
        ValidatorResponse validatorResponse;

        if ((validatorResponse = questionValidator.validateQuestionId(id)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new QuestionException(validatorResponse);
        }

        questionRepository.deleteById(id);
    }

    @Override
    public PurchaseQuestionDTO get(UUID id) throws QuestionException {
        ValidatorResponse validatorResponse;

        if ((validatorResponse = questionValidator.validateQuestionId(id)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new QuestionException(validatorResponse);
        }

        PurchaseQuestionEntity question = questionRepository.getOne(id);

        PurchaseAnswerDTO answerDTO = new PurchaseAnswerDTO();
        if (question.getAnswer() != null) answerDTO.setContent(question.getAnswer().getContent());

        PurchaseQuestionDTO questionDTO = new PurchaseQuestionDTO();
        questionDTO.setName(question.getName());
        questionDTO.setContent(question.getContent());
        questionDTO.setAnswer(answerDTO);

        return questionDTO;
    }

    @Override
    public List<PurchaseQuestionDTO> getPage(int pageIndex, int pageSize) throws PageIndexException, PageSizeException {
        if (pageIndex < 0) {
            throw new PageIndexException();
        }
        if (pageSize < 1) {
            throw new PageSizeException();
        }
        if (pageSize > 100) {
            pageSize = 10;
        }

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<PurchaseQuestionEntity> page = questionRepository.findAll(pageable);

        List<PurchaseQuestionDTO> out = new ArrayList<>();
        for (PurchaseQuestionEntity questionEntity : page){
            PurchaseQuestionDTO questionDTO = new PurchaseQuestionDTO(questionEntity.getName(), questionEntity.getContent());
            PurchaseAnswerDTO answerDTO = new PurchaseAnswerDTO(questionEntity.getAnswer().getContent(), questionEntity.getAnswer().isPublicity());

            questionDTO.setAnswer(answerDTO);
            out.add(questionDTO);
        }

        return out;
    }
}
