package org.zeep.library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.zeep.library.DTO.EmailService;
import org.zeep.library.DTO.NotifyEntity;

@Component
public class NotificationHandler implements EmailService {

    @Autowired
    JavaMailSender emailSender;
    public void send(NotifyEntity entity) {
        System.out.println("Hello. iinna"+ emailSender);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kingsleyog95@gmail.com");
        message.setTo(entity.getEmail());
        message.setSubject(entity.getBookName()+ " is now available");
        message.setText(entity.getBookName()+ " is now available and can be borrowed within the next 2 days.");
        emailSender.send(message);
    }

}
