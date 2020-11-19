package ru.link.experimental.Controllers;

import org.springframework.web.bind.annotation.*;
import ru.link.experimental.DTO.*;
import ru.link.experimental.Exceptions.*;
import ru.link.experimental.Exceptions.PageExceptions.*;
import ru.link.experimental.Services.PurchaseServices.Implements.*;

import java.util.*;

@RestController
public class PurchaseQA {

    private final PurchaseQuestionService questionService;

    private final PurchaseAnswerService answerService;

    public PurchaseQA(PurchaseQuestionService questionService, PurchaseAnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @PostMapping("/purchaseQuestion")
    public void createQuestion(@RequestParam UUID purchaseId, @RequestParam String name, @RequestParam String content) throws QuestionException {
        questionService.create(purchaseId, name, content);
    }

    @PostMapping("/purchaseAnswer")
    public void createAnswer(@RequestParam UUID questionId, @RequestParam String content, @RequestParam boolean publicity) throws AnswerException {
        answerService.create(questionId, content, publicity);
    }

    @PutMapping("/purchaseQuestion")
    public void updateQuestion(@RequestParam UUID id, @RequestParam String name, @RequestParam String content) throws QuestionException {
        questionService.update(id, name, content);
    }

    @PutMapping("/purchaseAnswer")
    public void updateAnswer(@RequestParam UUID id, @RequestParam String content, @RequestParam boolean publicity) throws AnswerException {
        answerService.update(id, content, publicity);
    }

    @DeleteMapping("/purchaseQuestion/{id}")
    public void deleteQuestion(@PathVariable("id") UUID id) throws QuestionException {
        questionService.delete(id);
    }

    @DeleteMapping("/purchaseAnswer/{id}")
    public void deleteAnswer(@PathVariable("id") UUID id) throws AnswerException {
        answerService.delete(id);
    }

    @GetMapping("/purchaseQuestion/{id}")
    public PurchaseQuestionDTO getQuestion(@PathVariable("id") UUID id) throws QuestionException {
        return questionService.get(id);
    }

    @GetMapping("/purchaseQuestions")
    public List<PurchaseQuestionDTO> getPageOfQuestions(@RequestParam int pageIndex, @RequestParam int pageSize) throws PageSizeException, PageIndexException {
        return questionService.getPage(pageIndex, pageSize);
    }
}
