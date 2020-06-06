package com.eventview.batch;

import com.eventview.service.EmailService;
import com.eventview.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleConfig {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private EventService eventService;
    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "${cron.schedule}")
    public void executes() throws JsonProcessingException {
        emailService.sendTextEmail(objectMapper.writeValueAsString(eventService.getUpcomingFNameEventType()));

    }
}
