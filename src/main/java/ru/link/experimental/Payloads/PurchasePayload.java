package ru.link.experimental.Payloads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.link.experimental.DTO.PurchaseDTO;
import ru.link.experimental.Services.PurchaseServices.Implements.*;

import java.util.*;

@Component
public class PurchasePayload {

    private final PurchaseService purchaseService;

    private final PurchaseQuestionService questionService;

    private final PurchaseAnswerService answerService;

    List<UUID> purchaseUUIDs = new ArrayList<>();
    List<UUID> questionUUIDs = new ArrayList<>();

    @Autowired
    public PurchasePayload(PurchaseService purchaseService, PurchaseQuestionService questionService, PurchaseAnswerService answerService) {
        this.purchaseService = purchaseService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    public void create(){
        for (int i = 0; i < 10; ++i){
            UUID pId = UUID.randomUUID();

            purchaseUUIDs.add(pId);

            purchaseService.create("purchase №" + i, pId);
            for (int j = 0; j < 5; ++j){
                UUID qId = UUID.randomUUID();
                questionUUIDs.add(qId);
                questionService.create(qId, pId, "question №" + j, "content of question №" + j);
                answerService.create(qId, "content of answer №" + j);
            }
        }
    }

    public List<PurchaseDTO> getAll(){
        List<PurchaseDTO> out = new ArrayList<>();
        for (int i = 0; i < 10; ++i){
            out.add(purchaseService.get(purchaseUUIDs.get(i)));
        }

        return out;
    }
}
