package ru.link.experimental.Services.PurchaseServices.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.link.experimental.DTO.*;
import ru.link.experimental.Entities.*;
import ru.link.experimental.Repositories.*;
import ru.link.experimental.Services.PurchaseServices.PurchaseAnswerServiceInterface;

import java.util.*;

@Service
public class PurchaseAnswerService implements PurchaseAnswerServiceInterface {

    private final PurchaseAnswerRepository answerRepository;

    private final PurchaseQuestionRepository questionRepository;

    @Autowired
    public PurchaseAnswerService(PurchaseAnswerRepository answerRepository, PurchaseQuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public void create(UUID questionId, String content, boolean publicity) {
        PurchaseAnswerEntity answerEntity = new PurchaseAnswerEntity(
                questionRepository.getOne(questionId),
                content,
                publicity
        );

        answerRepository.save(answerEntity);
    }

    @Override
    public void update(UUID id, String content, Boolean publicity) {
        PurchaseAnswerEntity answerEntity = answerRepository.getOne(id);
        answerEntity.setContent(content);
        answerEntity.setPublicity(publicity);

        answerRepository.saveAndFlush(answerEntity);
    }

    @Override
    public void delete(UUID id) {
        answerRepository.deleteById(id);
    }

    @Override
    public PurchaseAnswerDTO get(UUID id) {
        PurchaseAnswerDTO answerDTO = new PurchaseAnswerDTO();
        answerDTO.setContent(answerRepository.getOne(id).getContent());

        return answerDTO;
    }
}
