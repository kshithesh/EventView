package com.eventview.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    private final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.address}")
    private String[] emailAddress;

    public void sendTextEmail(String NextSevenDays) {

        SimpleMailMessage msg = new SimpleMailMessage();
        try {
            msg.setTo(emailAddress);
            msg.setSubject("EventView: This week's events");
            msg.setText("Upcoming events:" + NextSevenDays);
            javaMailSender.send(msg);
            log.info("mail sent");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
