package ru.link.experimental.Services.PurchaseServices.Implements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ru.link.experimental.DTO.*;
import ru.link.experimental.Entities.PurchaseQuestionEntity;
import ru.link.experimental.Exceptions.PageExceptions.*;
import ru.link.experimental.Repositories.*;
import ru.link.experimental.Services.PurchaseServices.PurchaseQuestionServiceInterface;

import java.util.*;

@Service
public class PurchaseQuestionService implements PurchaseQuestionServiceInterface {

    private final PurchaseQuestionRepository questionRepository;

    private final PurchaseAnswerRepository answerRepository;

    @Autowired
    public PurchaseQuestionService(PurchaseQuestionRepository questionRepository, PurchaseAnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public void create(UUID purchaseId, String name, String content) {
        PurchaseQuestionEntity questionEntity = new PurchaseQuestionEntity(purchaseId, name, content);

        questionRepository.save(questionEntity);
    }

    @Override
    public void update(UUID id, String name, String content) {
        PurchaseQuestionEntity questionEntity = questionRepository.getOne(id);
        questionEntity.setName(name);
        questionEntity.setContent(content);

        questionRepository.saveAndFlush(questionEntity);
    }

    @Override
    public void delete(UUID id) {
        questionRepository.deleteById(id);
    }

    @Override
    public PurchaseQuestionDTO get(UUID id) {
        PurchaseQuestionEntity question = questionRepository.getOne(id);

        PurchaseAnswerDTO answerDTO = new PurchaseAnswerDTO();
        //FIXME
        if (question.getAnswer() != null) answerDTO.setContent(question.getAnswer().getContent());

        PurchaseQuestionDTO questionDTO = new PurchaseQuestionDTO();
        questionDTO.setName(question.getName());
        questionDTO.setContent(question.getContent());
        questionDTO.setAnswer(answerDTO);

        return questionDTO;
    }

    @Override
    public List<PurchaseQuestionDTO> getPage(int pageIndex, int pageSize) throws PageIndexException, PageSizeException {
        if (pageSize > 100) {
            pageSize = 10;
        }

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<PurchaseQuestionEntity> page = questionRepository.findAll(pageable);

        List<PurchaseQuestionDTO> out = new ArrayList<>();
        for (PurchaseQuestionEntity questionEntity : page){
            PurchaseQuestionDTO questionDTO = new PurchaseQuestionDTO(questionEntity.getName(), questionEntity.getContent());
            PurchaseAnswerDTO answerDTO = new PurchaseAnswerDTO(questionEntity.getAnswer().getContent(), questionEntity.getAnswer().isPublicity());

            questionDTO.setAnswer(answerDTO);
            out.add(questionDTO);
        }

        return out;
    }
}
