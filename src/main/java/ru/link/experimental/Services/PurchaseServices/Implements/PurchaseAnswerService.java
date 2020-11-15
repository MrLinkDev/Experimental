package ru.link.experimental.Services.PurchaseServices.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.link.experimental.DTO.*;
import ru.link.experimental.Entities.*;
import ru.link.experimental.Repositories.PurchaseAnswerRepository;
import ru.link.experimental.Services.PurchaseServices.PurchaseAnswerServiceInterface;

import java.util.*;

@Service
public class PurchaseAnswerService implements PurchaseAnswerServiceInterface {

    private final PurchaseAnswerRepository answerRepository;

    @Autowired
    public PurchaseAnswerService(PurchaseAnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public void create(UUID questionId, String content, boolean publicity) {
        //PurchaseAnswerEntity answerEntity = new PurchaseAnswerEntity(questionId, content, publicity);

        //answerRepository.save(answerEntity);
    }

    @Override
    public void create(UUID id, UUID questionId, String content, boolean publicity) {
        //PurchaseAnswerEntity answerEntity = new PurchaseAnswerEntity(questionId, content, publicity);
        //answerEntity.setId(id);

        //answerRepository.save(answerEntity);
    }

    @Override
    public void update(UUID id, Optional<String> content, Optional<Boolean> publicity) {
        Optional<PurchaseAnswerEntity> answerEntity = answerRepository.findById(id);
        if (content.isPresent()) answerEntity.get().setContent(content.get());
        if (publicity.isPresent()) answerEntity.get().setPublicity(publicity.get());

        answerRepository.saveAndFlush(answerEntity.get());
    }

    @Override
    public void delete(UUID id) {
        answerRepository.deleteById(id);
    }

    @Override
    public PurchaseAnswerDTO get(UUID id) {
        PurchaseAnswerDTO answerDTO = new PurchaseAnswerDTO();
        answerDTO.setContent(answerRepository.findById(id).get().getContent());

        return answerDTO;
    }
}
