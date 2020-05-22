package com.eventview.batch;

import com.eventview.model.EmailTemplate;
import com.eventview.service.EmailService;
import com.eventview.service.EventService;
import com.eventview.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class ScheduleConfig {

    private final Logger log = LoggerFactory.getLogger(ScheduleConfig.class);

    @Autowired
    private EventService eventService;
    @Autowired
    private EmailService emailService;

    private EmailTemplate emailTemplate;

    private UserService userService;

    @Scheduled(cron = "0 0/57 20 * * *")
    public void executes() throws ParseException {
        List<Date> eventsList = eventService.getAllEventDates();
        log.warn("number of events[]",eventsList.size());
        Calendar c = Calendar.getInstance();
        Calendar d = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        d.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Iterator iterator = eventsList.iterator();
        int count = 1;
        while(iterator.hasNext()) {
            if (!(df.parse(iterator.next().toString())).before(c.getTime()) && !df.parse(iterator.next().toString()).after(d.getTime()))
                emailService.sendTextEmail(count);
                log.info("forwarded to email service");
        }
        count++;
    }
}

