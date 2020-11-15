package ru.link.experimental.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.link.experimental.DTO.*;
import ru.link.experimental.Payloads.PurchasePayload;
import ru.link.experimental.Repositories.*;
import ru.link.experimental.Services.MailService;
import ru.link.experimental.Services.PurchaseServices.Implements.*;
import ru.link.experimental.Validate.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

@RestController
public class SimpleController {
    private MailService mailService = new MailService();

    private final PurchasePayload purchasePayload;

    private final PurchaseService purchaseService;

    private final PurchaseQuestionService questionService;

    private final PurchaseRepository purchaseRepository;

    private final PurchaseQuestionRepository questionRepository;

    @Autowired
    public SimpleController(
            PurchasePayload purchasePayload, PurchaseService purchaseService, PurchaseRepository purchaseRepository, PurchaseQuestionService questionService,
            PurchaseQuestionRepository questionRepository
    ) {
        this.purchasePayload = purchasePayload;
        this.purchaseService = purchaseService;
        this.purchaseRepository = purchaseRepository;
        this.questionService = questionService;
        this.questionRepository = questionRepository;
    }

    @GetMapping("/sendMail")
    public void sendTestMail(@RequestParam String mailPassword) throws IOException, MessagingException {
        mailService.sendMail(mailPassword);
    }

    @PostMapping("/purchaseTest")
    public void purchaseTestCreate(){
        purchasePayload.create();
    }

    @GetMapping("/purchaseTest")
    public List<PurchaseDTO> purchaseTestGet(){
        return purchasePayload.getAll();
    }

    @GetMapping("/purchaseTest/{id}")
    public PurchaseDTO purchaseTestGetById(@PathVariable("id") final UUID id){
        //return purchaseRepository.getOne(id);
        return purchaseService.get(id);
    }

    @GetMapping("/purchaseQuestionTest/{id}")
    public PurchaseQuestionDTO purchaseQuestionTestGetById(@PathVariable("id") final UUID id){
        //return questionRepository.getOne(id);
        return questionService.get(id);
    }

    @GetMapping("/loginTest")
    public ValidatorResponse testLoginValidator(@RequestParam String login){
        return null;
        //return UserValidator.validateLogin(login);
    }

}
