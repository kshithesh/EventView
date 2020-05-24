package com.eventview.batch;

import com.eventview.service.EmailService;
import com.eventview.service.EventService;
import com.eventview.service.EventTypeService;
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
    private EventTypeService eventTypeService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @Scheduled(cron = "0 0 1 * * *")
    public void executes() throws ParseException {
        List<Date> eventsList = eventService.getAllEventDates();
        log.info("number of events{}", eventsList.size());
        Calendar c = Calendar.getInstance();
        Calendar d = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        d.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Iterator iterator = eventsList.iterator();
        int count = 1;
        while (iterator.hasNext()) {
            Date da = df.parse(iterator.next().toString());
            cal.setTime(da);
            log.info("list month{} month{}", cal.get(Calendar.MONTH), c.get(Calendar.MONTH));
            if ((cal.get(Calendar.MONTH)) == (c.get(Calendar.MONTH))) {
                if ((cal.get(Calendar.DAY_OF_MONTH)) == (c.get(Calendar.DAY_OF_MONTH))) {
                    emailService.sendTextEmail(userService.getEmailByEvent(count),eventTypeService.getEventType(count),userService.getFNameByEvent(count),eventTypeService.getEventType(count));
                    log.info("forwarded to email service c{} count{}", c.getTime(),count);
                }
            }
            count++;
        }
    }
}

