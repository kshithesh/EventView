package com.eventview.test;

import com.eventview.batch.ScheduleConfig;
import com.eventview.controller.EventsRestController;
import com.eventview.exceptions.EventNotFoundException;
import com.eventview.exceptions.UserExistsException;
import com.eventview.exceptions.UserNotFoundException;
import com.eventview.model.Events;
import com.eventview.model.EventsPayload;
import com.eventview.model.Users;
import com.eventview.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean(ScheduleConfig.class)
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
    public void getAllEventsCustomTest() throws Exception {
        List<EventsPayload> payloads = Arrays.asList(new EventsPayload(1, "kshithesh", "routhu", "25-08-1996", "anniversary"),
                new EventsPayload(2, "hrishikesh", "routhu", "10-09-2000", "birthday"));

        when(eventService.getAllEventsCustom()).thenReturn(payloads);

        mvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].eventId", is(1)))
                .andExpect(jsonPath("$[0].fullName", is("kshithesh routhu")))
                .andExpect(jsonPath("$[1].eventId", is(2)))
                .andExpect(jsonPath("$[1].fullName", is("hrishikesh routhu")));

        verify(eventService, times(1)).getAllEventsCustom();
        verifyNoMoreInteractions(eventService);
    }
/*
    @Test
    public void getAllEventsCustom_No_Event_Test() throws Exception {
        when(eventService.getAllEventsCustom()).thenThrow(EventNotFoundException.class);
        mvc.perform(get("/events"))
                .andExpect(status().isNotFound());
    }


 */
    @Test
    public void findByEventIdTest() throws Exception {
        EventsPayload payload = new EventsPayload(1, "kshithesh", "routhu", "25-08-1996", "anniversary");

        when(eventService.findByEventsIdCustom(1)).thenReturn(payload);

        mvc.perform(get("/event/{eventId}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eventId").value(1))
                .andExpect(jsonPath("$.fullName").value("kshithesh routhu"));

        verify(eventService, times(1)).findByEventsIdCustom(1);
        verifyNoMoreInteractions(eventService);
    }

    /*
    @Test
    public void findByEventId_No_Event_Test() throws Exception {
        when(eventService.findByEventsId(any())).thenThrow(EventNotFoundException.class);
        mvc.perform(get("/event/1"))
                .andExpect(status().isNotFound());
    }
*/
    @Test
    public void createEventTest() throws Exception {
        Events events = new Events(102, 2, 2, dateFormat.parse("10-09-2000"));

        when(eventService.exists(events)).thenReturn(false);
        doNothing().when(eventService).createEvent(events);

        mvc.perform(
                post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(events)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createEvent_Event_Exists_Test() throws Exception {
        Events events = new Events(102, 2, 2, dateFormat.parse("10-09-2000"));

        when(eventService.exists(events)).thenReturn(true);
        doThrow(UserExistsException.class).when(eventService).createEvent(events);
        mvc.perform(post("/event"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updateEventTest() throws Exception {
        Events events = new Events(102, 2, 2, dateFormat.parse("10-09-2000"));

        when(eventService.findByEventsId(events.getEventId())).thenReturn(events);
        doNothing().when(eventService).updateEvent(events);

        mvc.perform(
                put("/event/{eventId}", events.getEventId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(events)))
                .andExpect(status().isOk());

    }
/*
    @Test
    public void updateEvent_Event_Not_Found_Test() throws Exception {
        when(eventService.findByEventsIdCustom(1)).thenThrow(EventNotFoundException.class);
        mvc.perform(delete("/event/1"))
                .andExpect(status().isNotFound());
    }


 */

    @Test
    public void deleteEventTest() throws Exception {
        Events events = new Events(102, 2, 2, dateFormat.parse("10-09-2000"));

        when(eventService.findByEventsId(events.getEventId())).thenReturn(events);
        doNothing().when(eventService).deleteEvent(events.getEventId());

        mvc.perform(
                delete("/event/{eventid}", events.getEventId()))
                .andExpect(status().isNoContent());

        verify(eventService, times(1)).findByEventsId(events.getEventId());
        verify(eventService, times(1)).deleteEvent(events.getEventId());
        verifyNoMoreInteractions(eventService);
    }

    /*
    @Test
    public void deleteEvent_Not_Found_Event_Test() throws Exception {
        when(eventService.findByEventsIdCustom(1)).thenThrow(EventNotFoundException.class);
        mvc.perform(delete("/event/1"))
                .andExpect(status().isNotFound());
    }

     */
}
