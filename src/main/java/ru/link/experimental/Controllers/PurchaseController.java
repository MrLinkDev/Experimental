package ru.link.experimental.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.link.experimental.DTO.PurchaseQuestionDTO;
import ru.link.experimental.Exceptions.*;
import ru.link.experimental.Exceptions.PageExceptions.*;
import ru.link.experimental.Services.PurchaseServices.Implements.*;

import java.util.*;

@RestController
public class PurchaseController {

    private final PurchaseQuestionService questionService;
    private final PurchaseAnswerService answerService;

    public PurchaseController(PurchaseQuestionService questionService, PurchaseAnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @PostMapping("/purchaseQuestion")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPurchaseQuestion(@RequestParam UUID purchaseId, @RequestParam String name, @RequestParam String content) throws QuestionException {
        questionService.create(purchaseId, name, content);
    }

    @GetMapping("/purchaseQuestion")
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseQuestionDTO> getListOfPurchaseQuestions(int pageIndex, int pageSize) throws PageSizeException, PageIndexException {
        return questionService.getPage(pageIndex, pageSize);
    }

    @PostMapping("/purchaseAnswer")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPurchaseAnswer(@RequestParam UUID questionId, @RequestParam String content, @RequestParam boolean publicity) throws AnswerException {
        answerService.create(questionId, content, publicity);
    }
}
