package com.eventview.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.address}")
    private String emailAddress;

    public void sendTextEmail(List<String> Today, List<String> NextSevenDays) {

        SimpleMailMessage msg = new SimpleMailMessage();
        String strTodayEvents = String.join(",", Today);
        String strUpcomingEvents = String.join(",", NextSevenDays);
        log.debug("today{}",strTodayEvents);
        try {
            if (emailAddress.contains(",")) {
                String[] emails = emailAddress.split(",");
                for (String email : emails) {
                    msg.setTo(email);
                    msg.setSubject("${email.subject}");
                    msg.setText("Today's events:" + "\n" + strTodayEvents + "\n" +
                            "Upcoming events:" + "\n" + strUpcomingEvents);
                    javaMailSender.send(msg);
                }
            } else {
                msg.setTo(emailAddress);
                msg.setSubject("${email.subject}");
                msg.setText("Today's events:" + "\n" + strTodayEvents + "\n" +
                        "Upcoming events:" + "\n" + strUpcomingEvents);
                javaMailSender.send(msg);
                log.info("mail sent");
            }
        }
        catch(Exception e){
                e.printStackTrace();
            }

        }
    }
