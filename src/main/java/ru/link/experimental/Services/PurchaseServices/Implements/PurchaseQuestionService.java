package ru.link.experimental.Services.PurchaseServices.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import ru.link.experimental.DTO.*;
import ru.link.experimental.Entities.PurchaseQuestionEntity;
import ru.link.experimental.Exceptions.PageExceptions.*;
import ru.link.experimental.Repositories.*;
import ru.link.experimental.Services.PurchaseServices.PurchaseQuestionServiceInterface;
import ru.link.experimental.Validate.PageValidator;

import java.util.*;

@Service
public class PurchaseQuestionService implements PurchaseQuestionServiceInterface {

    private final PurchaseQuestionRepository questionRepository;

    private final PurchaseAnswerRepository answerRepository;

    private final PageValidator pageValidator;

    @Autowired
    public PurchaseQuestionService(PurchaseQuestionRepository questionRepository, PurchaseAnswerRepository answerRepository, PageValidator pageValidator) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.pageValidator = pageValidator;
    }

    @Override
    public void create(UUID purchaseId, String name, String content) {
        PurchaseQuestionEntity questionEntity = new PurchaseQuestionEntity(purchaseId, name, content);

        questionRepository.save(questionEntity);
    }

    @Override
    public void create(UUID id, UUID purchaseId, String name, String content) {
        PurchaseQuestionEntity questionEntity = new PurchaseQuestionEntity(purchaseId, name, content);
        questionEntity.setId(id);

        questionRepository.save(questionEntity);
    }

    @Override
    public void update(UUID id, Optional<String> name, Optional<String> content) {
        Optional<PurchaseQuestionEntity> questionEntity = questionRepository.findById(id);
        if (name.isPresent()) questionEntity.get().setName(name.get());
        if (content.isPresent()) questionEntity.get().setContent(content.get());

        questionRepository.saveAndFlush(questionEntity.get());
    }

    @Override
    public void delete(UUID id) {
        questionRepository.deleteById(id);
    }

    @Override
    public PurchaseQuestionDTO get(UUID id) {
        Optional<PurchaseQuestionEntity> question = questionRepository.findByPurchaseId(id);

        PurchaseAnswerDTO answerDTO = new PurchaseAnswerDTO();
        answerDTO.setContent(answerRepository.findByQuestionId(question.get().getId()).getContent());

        PurchaseQuestionDTO questionDTO = new PurchaseQuestionDTO();
        questionDTO.setName(question.get().getName());
        questionDTO.setContent(question.get().getContent());
        questionDTO.setAnswer(answerDTO);

        return questionDTO;
    }

    @Override
    public List<PurchaseQuestionDTO> getPage(int pageIndex, int pageSize) throws PageIndexException, PageSizeException {
        pageValidator.checkPageIndex(pageIndex);
        pageValidator.checkPageSize(pageSize);

        if (pageSize > 100) {
            pageSize = 10;
        }

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<PurchaseQuestionEntity> page = questionRepository.findAll(pageable);

        List<PurchaseQuestionDTO> out = new ArrayList<>();
        for (PurchaseQuestionEntity questionEntity : page){
            PurchaseQuestionDTO questionDTO = new PurchaseQuestionDTO(questionEntity.getName(), questionEntity.getContent());
            PurchaseAnswerDTO answerDTO = new PurchaseAnswerDTO(
                    answerRepository.findByQuestionId(questionEntity.getId()).getContent(),
                    answerRepository.findByQuestionId(questionEntity.getId()).isPublicity()
            );
            questionDTO.setAnswer(answerDTO);
            out.add(questionDTO);
        }

        return out;
    }
}
