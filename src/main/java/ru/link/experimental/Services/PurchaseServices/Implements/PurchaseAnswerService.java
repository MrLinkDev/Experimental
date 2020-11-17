package ru.link.experimental.Services.PurchaseServices.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.link.experimental.DTO.*;
import ru.link.experimental.Entities.*;
import ru.link.experimental.Exceptions.*;
import ru.link.experimental.Repositories.*;
import ru.link.experimental.Services.PurchaseServices.PurchaseAnswerServiceInterface;
import ru.link.experimental.Validate.*;

import java.util.*;

@Service
public class PurchaseAnswerService implements PurchaseAnswerServiceInterface {

    private final PurchaseAnswerRepository answerRepository;

    private final PurchaseQuestionRepository questionRepository;

    private final PurchaseAnswerValidator answerValidator;

    @Autowired
    public PurchaseAnswerService(PurchaseAnswerRepository answerRepository, PurchaseQuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        answerValidator = new PurchaseAnswerValidator();
    }

    @Override
    public void create(UUID questionId, String content, Boolean publicity) throws AnswerException {
        ValidatorResponse validatorResponse;

        if ((validatorResponse = answerValidator.validateQuestionId(questionId)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new AnswerException(validatorResponse);
        }

        if ((validatorResponse = answerValidator.validateContent(content)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new AnswerException(validatorResponse);
        }

        if ((validatorResponse = answerValidator.validatePublicity(publicity)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new AnswerException(validatorResponse);
        }

        PurchaseAnswerEntity answerEntity = new PurchaseAnswerEntity(
                questionRepository.getOne(questionId),
                content,
                publicity
        );

        answerRepository.save(answerEntity);
    }

    @Override
    public void update(UUID id, String content, Boolean publicity) throws AnswerException {
        ValidatorResponse validatorResponse;

        if ((validatorResponse = answerValidator.validateAnswerId(id)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new AnswerException(validatorResponse);
        }

        if ((validatorResponse = answerValidator.validateContent(content)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new AnswerException(validatorResponse);
        }

        if ((validatorResponse = answerValidator.validatePublicity(publicity)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new AnswerException(validatorResponse);
        }

        PurchaseAnswerEntity answerEntity = answerRepository.getOne(id);
        answerEntity.setContent(content);
        answerEntity.setPublicity(publicity);

        answerRepository.saveAndFlush(answerEntity);
    }

    @Override
    public void delete(UUID id) throws AnswerException {
        ValidatorResponse validatorResponse;

        if ((validatorResponse = answerValidator.validateAnswerId(id)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new AnswerException(validatorResponse);
        }

        answerRepository.deleteById(id);
    }

    @Override
    public PurchaseAnswerDTO get(UUID id) throws AnswerException {
        ValidatorResponse validatorResponse;

        if ((validatorResponse = answerValidator.validateAnswerId(id)).getValidatorStatus() == ValidatorStatus.ERROR){
            throw new AnswerException(validatorResponse);
        }

        PurchaseAnswerDTO answerDTO = new PurchaseAnswerDTO();
        answerDTO.setContent(answerRepository.getOne(id).getContent());

        return answerDTO;
    }
}
