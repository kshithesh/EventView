package com.eventview.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    private EventTypeService eventTypeService;
    private UserService userService;

    public void sendTextEmail(String sendTo, String subject, String body1, String body2) {

        SimpleMailMessage msg = new SimpleMailMessage();

        try {
                msg.setTo(sendTo);
                msg.setSubject("Event:" + subject);
                msg.setText("Today is " + body1 + "'s " + body2);
                javaMailSender.send(msg);
                log.info("mail sent");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
