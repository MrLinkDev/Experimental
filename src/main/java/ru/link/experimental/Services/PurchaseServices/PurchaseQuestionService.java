package ru.link.experimental.Services.PurchaseServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.link.experimental.DTO.*;
import ru.link.experimental.Entities.PurchaseQuestionEntity;
import ru.link.experimental.Repositories.*;
import ru.link.experimental.Services.PurchaseServices.Interfaces.PurchaseQuestionServiceInterface;

import java.util.*;

@Service
public class PurchaseQuestionService implements PurchaseQuestionServiceInterface {

    @Autowired
    private PurchaseQuestionRepository questionRepository;

    @Autowired
    private PurchaseAnswerRepository answerRepository;

    @Override
    public void create(UUID purchaseId, String name, String content) {
        PurchaseQuestionEntity questionEntity = new PurchaseQuestionEntity();
        questionEntity.setId(UUID.randomUUID());
        questionEntity.setPurchaseId(purchaseId);
        questionEntity.setName(name);
        questionEntity.setContent(content);

        questionRepository.save(questionEntity);
    }

    @Override
    public void create(UUID id, UUID purchaseId, String name, String content) {
        PurchaseQuestionEntity questionEntity = new PurchaseQuestionEntity();
        questionEntity.setId(id);
        questionEntity.setPurchaseId(purchaseId);
        questionEntity.setName(name);
        questionEntity.setContent(content);

        questionRepository.save(questionEntity);
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
}
