package com.eventview.batch;

import com.eventview.service.EmailService;
import com.eventview.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleConfig {

    @Autowired
    private EventService eventService;
    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "${cron.schedule}")
    public void executes(){

        Integer[] month = eventService.getAllEventMonths().toArray(new Integer[0]);
        List<String> Today = eventService.getTodayFNameEventType();
        List<String> NextSevenDays = eventService.getUpcomingFNameEventType();

        if (month.length > 0) {
            emailService.sendTextEmail(Today,NextSevenDays);
        }
    }
}
