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

    public void sendTextEmail(List<String> fNamesNext, List<String> eventTypesNext, List<String> fNamesToday, List<String> eventTypesToday) {

        SimpleMailMessage msg = new SimpleMailMessage();
        String strFNamesNext = String.join(",", fNamesNext);
        String strEventTypesNext = String.join("  ", eventTypesNext);
        String strFNamesToday = String.join(",", fNamesToday);
        String strEventTypesToday = String.join(" ", eventTypesToday);
        try {
            if (emailAddress.contains(",")) {
                String[] emails = emailAddress.split(",");
                for (String email : emails) {
                    msg.setTo(email);
                    msg.setSubject("${email.subject}");
                    msg.setText("Today's events:" + "\n" + strFNamesToday + " " + strEventTypesToday + "\n" +
                            "Upcoming events:" + "\n" + strFNamesNext + " " + strEventTypesNext);
                    javaMailSender.send(msg);
                }
            } else {
                msg.setTo(emailAddress);
                msg.setSubject("${email.subject}");
                msg.setText("Today's events:" + "\n" + strFNamesToday + "\n" + strEventTypesToday + "\n" +
                        "Upcoming events:" + "\n" + strFNamesNext + "\n" + strEventTypesNext);
                javaMailSender.send(msg);
                log.info("mail sent");
            }
        }
        catch(Exception e){
                e.printStackTrace();
            }

        }
    }
