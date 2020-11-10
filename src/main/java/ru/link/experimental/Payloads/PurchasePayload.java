package ru.link.experimental.Payloads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.link.experimental.DTO.PurchaseDTO;
import ru.link.experimental.Services.PurchaseServices.*;

import java.util.*;

@Component
public class PurchasePayload {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PurchaseQuestionService questionService;

    @Autowired
    private PurchaseAnswerService answerService;

    List<UUID> purchaseUUIDs = new ArrayList<>();
    List<UUID> questionUUIDs = new ArrayList<>();

    public void create(){
        for (int i = 0; i < 10; ++i){
            UUID pId = UUID.randomUUID();
            UUID qId = UUID.randomUUID();

            purchaseUUIDs.add(pId);
            questionUUIDs.add(qId);

            purchaseService.create("purchase №" + i, pId);
            questionService.create(qId, pId, "question №" + i, "content of question №" + i);
            answerService.create(qId, "content of answer №" + i);
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
