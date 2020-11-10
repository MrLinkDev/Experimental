package ru.link.experimental.Services.PurchaseServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.link.experimental.DTO.*;
import ru.link.experimental.Entities.*;
import ru.link.experimental.Repositories.*;
import ru.link.experimental.Services.PurchaseServices.Interfaces.PurchaseServiceInterface;

import java.util.*;

@Service
public class PurchaseService implements PurchaseServiceInterface {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseQuestionRepository questionRepository;

    @Autowired
    private PurchaseAnswerRepository answerRepository;

    @Override
    public void create(String name) {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(UUID.randomUUID());
        purchaseEntity.setName(name);

        purchaseRepository.save(purchaseEntity);
    }

    @Override
    public void create(String name, UUID id) {
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(id);
        purchaseEntity.setName(name);

        purchaseRepository.save(purchaseEntity);
    }

    @Override
    public PurchaseDTO get(UUID id) {
        Optional<PurchaseEntity> purchaseEntity = purchaseRepository.findById(id);
        List<PurchaseQuestionEntity> questionList = questionRepository.findAllByPurchaseId(id);

        List<PurchaseQuestionDTO> questionDTOList = new ArrayList<>();

        for (PurchaseQuestionEntity question : questionList) {
            PurchaseAnswerDTO answerDTO = new PurchaseAnswerDTO();
            answerDTO.setContent(answerRepository.findByQuestionId(question.getId()).getContent());

            PurchaseQuestionDTO questionDTO = new PurchaseQuestionDTO();
            questionDTO.setName(question.getName());
            questionDTO.setContent(question.getContent());
            questionDTO.setAnswer(answerDTO);

            questionDTOList.add(questionDTO);
        }

        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setName(purchaseEntity.get().getName());
        purchaseDTO.setQuestions(questionDTOList);
        return purchaseDTO;
    }
}
