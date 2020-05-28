package com.eventview.batch;

import com.eventview.service.EmailService;
import com.eventview.service.EventService;
import com.eventview.service.EventTypeService;
import com.eventview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleConfig {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventTypeService eventTypeService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @Scheduled(cron = "${cron.schedule}")
    public void executes(){
        Integer[] month = eventService.getAllEventMonths().toArray(new Integer[0]);
        List<String> fNamesToday = userService.getFNameToday();
        List<String> eventTypesToday = eventTypeService.getEventTypeToday();
        List<String> fNamesWeek = userService.getFNameWeek();
        List<String> eventTypesWeek = eventTypeService.getEventTypeWeek();
        if (month.length > 0) {
            emailService.sendTextEmail(fNamesToday,eventTypesToday,fNamesWeek, eventTypesWeek);
        }
    }
}
