package ru.link.experimental.Controllers;

import org.springframework.web.bind.annotation.*;
import ru.link.experimental.DTO.*;
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

    @PostMapping("/question")
    public void createQuestion(@RequestParam UUID purchaseId, @RequestParam String name, @RequestParam String content){
        questionService.create(purchaseId, name, content);
    }

    @PostMapping("/answer")
    public void createAnswer(@RequestParam UUID questionId, @RequestParam String content, @RequestParam boolean publicity){
        answerService.create(questionId, content, publicity);
    }

    @PutMapping("/question/{id}")
    public void updateQuestion(@PathVariable("id") UUID id, @RequestParam String name, @RequestParam String content){
        questionService.update(id, name, content);
    }

    @PutMapping("/answer/{id}")
    public void updateAnswer(@PathVariable("id") UUID id, @RequestParam String content, @RequestParam boolean publicity){
        answerService.update(id, content, publicity);
    }

    @DeleteMapping("/question/{id}")
    public void deleteQuestion(@PathVariable("id") UUID id){
        questionService.delete(id);
    }

    @DeleteMapping("/answer/{id}")
    public void deleteAnswer(@PathVariable("id") UUID id){
        answerService.delete(id);
    }

    @GetMapping("/question/{id}")
    public PurchaseQuestionDTO getQuestion(@PathVariable("id") UUID id){
        return questionService.get(id);
    }

    @GetMapping("/answer/{id}")
    public PurchaseAnswerDTO getAnswer(@PathVariable("id") UUID id){
        return answerService.get(id);
    }

    @GetMapping("/question")
    public List<PurchaseQuestionDTO> getPageOfQuestions(@RequestParam int pageIndex, @RequestParam int pageSize) throws PageSizeException, PageIndexException {
        return questionService.getPage(pageIndex, pageSize);
    }
}
