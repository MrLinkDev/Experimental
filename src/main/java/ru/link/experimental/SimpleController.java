package ru.link.experimental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.link.experimental.DTO.PurchaseDTO;
import ru.link.experimental.Payloads.PurchasePayload;
import ru.link.experimental.Services.MailService;

import javax.mail.MessagingException;
import javax.validation.Payload;
import java.io.IOException;
import java.util.List;

@RestController
public class SimpleController {
    private MailService mailService = new MailService();

    @Autowired
    private PurchasePayload purchasePayload;

    @GetMapping("/sendMail")
    public void sendTestMail(@RequestParam String mailPassword) throws IOException, MessagingException {
        mailService.sendMail(mailPassword);
    }

    @GetMapping("/purchaseTest")
    public List<PurchaseDTO> purchaseTest(){
        purchasePayload.create();

        return purchasePayload.getAll();
    }

}
