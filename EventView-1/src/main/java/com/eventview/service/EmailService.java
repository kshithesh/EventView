package com.eventview.service;

import com.eventview.model.EmailTemplate;
import com.eventview.model.EvenTypes;
import com.eventview.model.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private EventTypeService eventTypeService;
    private UserService userService;

    public void sendTextEmail(int count) {

        SimpleMailMessage msg = new SimpleMailMessage();

        try {
                msg.setTo(userService.findByUserId(count).getEmail());
                msg.setSubject("Event:" + eventTypeService.findByEventTypeId(count).getEventType());
                msg.setText("Today is " + userService.findByUserId(count).getFName() + "'s " + eventTypeService.findByEventTypeId(count).getEventType());
                javaMailSender.send(msg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
