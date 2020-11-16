package ru.link.experimental.Services.PurchaseServices.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.link.experimental.DTO.*;
import ru.link.experimental.Entities.*;
import ru.link.experimental.Repositories.*;
import ru.link.experimental.Services.PurchaseServices.PurchaseServiceInterface;

import java.util.*;

@Service
public class PurchaseService implements PurchaseServiceInterface {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public void create(String name) {
        PurchaseEntity purchaseEntity = new PurchaseEntity(name);

        purchaseRepository.save(purchaseEntity);
    }

    @Override
    public void create(String name, UUID id) {
        PurchaseEntity purchaseEntity = new PurchaseEntity(name);
        purchaseEntity.setId(id);

        purchaseRepository.save(purchaseEntity);
    }

    @Override
    public PurchaseDTO get(UUID id) {
        Optional<PurchaseEntity> purchaseEntity = purchaseRepository.findById(id);

        List<PurchaseQuestionEntity> questionList = purchaseEntity.get().getQuestions();

        List<PurchaseQuestionDTO> questionDTOList = new ArrayList<>();

        List<SupplyEntity> supplyList = purchaseEntity.get().getSupplies();

        List<SupplyDTO> supplyDTOList = new ArrayList<>();

        for (SupplyEntity supplyEntity : supplyList){
            supplyDTOList.add(new SupplyDTO(supplyEntity.getCost(), supplyEntity.getTStack(), supplyEntity.getStructure(), supplyEntity.getComment()));
        }

        for (PurchaseQuestionEntity question : questionList) {
            PurchaseAnswerDTO answerDTO = new PurchaseAnswerDTO();
            if (question.getAnswer() == null){
                answerDTO.setContent(null);
            } else {
                answerDTO.setContent(question.getAnswer().getContent());
            }

            PurchaseQuestionDTO questionDTO = new PurchaseQuestionDTO();
            questionDTO.setName(question.getName());
            questionDTO.setContent(question.getContent());
            questionDTO.setAnswer(answerDTO);

            questionDTOList.add(questionDTO);
        }

        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setName(purchaseEntity.get().getName());
        purchaseDTO.setQuestions(questionDTOList);
        purchaseDTO.setSupplies(supplyDTOList);
        return purchaseDTO;
    }
}
