package ru.link.experimental.Services.PurchaseServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.link.experimental.DTO.*;
import ru.link.experimental.Entities.PurchaseAnswerEntity;
import ru.link.experimental.Repositories.PurchaseAnswerRepository;
import ru.link.experimental.Services.PurchaseServices.Interfaces.*;

import java.util.UUID;

@Service
public class PurchaseAnswerService implements PurchaseAnswerServiceInterface {

    @Autowired
    private PurchaseAnswerRepository answerRepository;

    @Override
    public void create(UUID questionId, String content) {
        PurchaseAnswerEntity answerEntity = new PurchaseAnswerEntity();
        answerEntity.setId(UUID.randomUUID());
        answerEntity.setQuestionId(questionId);
        answerEntity.setContent(content);

        answerRepository.save(answerEntity);
    }

    @Override
    public void create(UUID id, UUID questionId, String content) {
        PurchaseAnswerEntity answerEntity = new PurchaseAnswerEntity();
        answerEntity.setId(id);
        answerEntity.setQuestionId(questionId);
        answerEntity.setContent(content);

        answerRepository.save(answerEntity);
    }

    @Override
    public PurchaseAnswerDTO get(UUID id) {
        PurchaseAnswerDTO answerDTO = new PurchaseAnswerDTO();
        answerDTO.setContent(answerRepository.findById(id).get().getContent());

        return answerDTO;
    }
}
