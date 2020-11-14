package ru.link.experimental.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.link.experimental.DTO.PurchaseDTO;
import ru.link.experimental.Payloads.PurchasePayload;
import ru.link.experimental.Services.MailService;
import ru.link.experimental.Validate.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
public class SimpleController {
    private MailService mailService = new MailService();

    private final PurchasePayload purchasePayload;

    @Autowired
    public SimpleController(PurchasePayload purchasePayload) {
        this.purchasePayload = purchasePayload;
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

    @GetMapping("/loginTest")
    public ValidatorResponse testLoginValidator(@RequestParam String login){
        return Validator.validateLogin(login);
    }

}
