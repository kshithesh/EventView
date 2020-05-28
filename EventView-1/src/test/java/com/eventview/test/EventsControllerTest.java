package com.eventview.test;

import com.eventview.controller.EventsRestController;
import com.eventview.model.Events;
import com.eventview.model.EventsPayload;
import com.eventview.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EventsControllerTest {

    private final static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private MockMvc mvc;

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventsRestController eventsController;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders
                .standaloneSetup(eventsController)
                .build();
    }


    @Test
    public void getAllEvens() throws Exception {
        List<EventsPayload> payloads = Arrays.asList(new EventsPayload(101, "kshithesh", "routhu", "25-08-1996", "anniversary"),
                new EventsPayload(102, "hrishikesh", "routhu", "10-09-2000", "birthday"));

        when(eventService.getAllEvens()).thenReturn(payloads);

        mvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].eventId", is(101)))
                .andExpect(jsonPath("$[0].fullName", is("kshithesh routhu")))
                .andExpect(jsonPath("$[1].eventId", is(102)))
                .andExpect(jsonPath("$[1].fullName", is("hrishikesh routhu")));

        verify(eventService, times(1)).getAllEvens();
        verifyNoMoreInteractions(eventService);
    }

    @Test
    public void findByEventId() throws Exception {
        Events events = new Events(102, 2, 2, dateFormat.parse("10-09-2000"));

        when(eventService.findByEventsId(102)).thenReturn(events);

        mvc.perform(get("/event/{eventId}", 102)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eventId").value(102))
                .andExpect(jsonPath("$.eventDate").value("09-09-2000"));

        verify(eventService, times(1)).findByEventsId(102);
        verifyNoMoreInteractions(eventService);
    }


    @Test
    public void createEvent() throws Exception {
        Events events = new Events(102, 2, 2, dateFormat.parse("10-09-2000"));

        when(eventService.exists(events)).thenReturn(false);
        doNothing().when(eventService).createEvent(events);

        mvc.perform(
                post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(events)))
                .andExpect(status().isCreated());

        //verify(eventService, times(1)).exists(events);
        //verify(eventService, times(1)).createEvent(events);
        //verifyNoMoreInteractions(eventService);
    }


    @Test
    public void updateEvent() throws Exception {
        Events events = new Events(102, 2, 2, dateFormat.parse("10-09-2000"));

        when(eventService.findByEventsId(events.getEventId())).thenReturn(events);
        doNothing().when(eventService).updateEvent(events);

        mvc.perform(
                put("/event/{eventId}", events.getEventId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(events)));
        //.andExpect(status().isOk());

        //verify(eventService, times(1)).findByEventsId(events.getEventId());
        //verify(eventService, times(1)).updateEvent(events);
        //verifyNoMoreInteractions(eventService);

    }

    @Test
    public void deleteEvent() throws Exception {
        Events events = new Events(102, 2, 2, dateFormat.parse("10-09-2000"));

        when(eventService.findByEventsId(events.getEventId())).thenReturn(events);
        doNothing().when(eventService).deleteEvent(events.getEventId());

        mvc.perform(
                delete("/event/{eventid}", events.getEventId()))
                .andExpect(status().isOk());

        verify(eventService, times(1)).findByEventsId(events.getEventId());
        verify(eventService, times(1)).deleteEvent(events.getEventId());
        verifyNoMoreInteractions(eventService);
    }
}
