package ru.link.experimental.Services;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.*;

public class MailService {

    public void sendMail(String password) throws AddressException, MessagingException, IOException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("linkstuff.help@gmail.com", password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("linkstuff.help@gmail.com", false));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sasha2000.gorbunov@gmail.com"));
        message.setSubject("Test mail");
        message.setContent("Content of test mail\n" + new Date(), "text/html");
        message.setSentDate(new Date());

        Transport.send(message);
    }

}
