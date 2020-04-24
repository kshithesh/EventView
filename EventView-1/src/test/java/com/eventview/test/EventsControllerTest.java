package com.eventview.test;

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

    private MockMvc mvc;

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventsControllerTest eventsControllerTest;

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
                .standaloneSetup(eventsControllerTest)
                .build();
    }


    @Test
    public void getAllEvens() throws Exception {
        List<EventsPayload> payloads = Arrays.asList(new EventsPayload(101,"kshithesh routhu","25-08-1996", "anniversary"),
                new EventsPayload(102,"hrishikesh routhu","10-09-2000", "birthday"));

        when(eventService.getAllEvens()).thenReturn(payloads);

        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].event_id", is(101)))
                .andExpect(jsonPath("$[0].full_name", is("kshithesh routhu")))
                .andExpect(jsonPath("$[1].event_id", is(102)))
                .andExpect(jsonPath("$[1].full_name", is("hrishikesh routhu")));

        verify(eventService, times(1)).getAllEvens();
        verifyNoMoreInteractions(eventService);
    }

    @Test
    public void findByEventId() throws Exception {
        Events events = new Events(102,2,2, "10-09-2000");

        when(eventService.findByEventsId(102)).thenReturn(events);

        mvc.perform(get("/{event_id}", 102)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.event_id").value(1))
                .andExpect(jsonPath("$.event_date").value("10-09-2000"));

        verify(eventService, times(1)).findByEventsId(102);
        verifyNoMoreInteractions(eventService);
    }


    @Test
    public void createEvent() throws Exception {
        Events events = new Events(102,2,2, "10-09-2000");

        when(eventService.exists(events)).thenReturn(false);
        doNothing().when(eventService).createEvent(events);

        mvc.perform(
                post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(events)))
                .andExpect(status().isCreated());
        //       .andExpect(jsonPath("$.user_id").value(1));

        verify(eventService, times(1)).exists(events);
        verify(eventService, times(1)).createEvent(events);
        verifyNoMoreInteractions(eventService);
    }


    @Test
    public void updateEvent() throws Exception {
        Events events = new Events(102,2,2, "10-09-2000");

        when(eventService.findByEventsId(events.getEvent_type_id())).thenReturn(events);
        doNothing().when(eventService).updateEvent(events);

        mvc.perform(
                put("/{event_id}", events.getEvent_id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(events)))
                .andExpect(status().isOk());

        verify(eventService, times(1)).findByEventsId(events.getEvent_id());
        verify(eventService, times(1)).updateEvent(events);
        verifyNoMoreInteractions(eventService);

    }

    @Test
    public void deleteEvent() throws Exception {
        Events events = new Events(102,2,2, "10-09-2000");

        when(eventService.findByEventsId(events.getEvent_id())).thenReturn(events);
        doNothing().when(eventService).deleteEvent(events.getEvent_id());

        mvc.perform(
                delete("/{event_id}", events.getEvent_id()))
                .andExpect(status().isOk());

        verify(eventService, times(1)).findByEventsId(events.getEvent_id());
        verify(eventService, times(1)).deleteEvent(events.getEvent_id());
        verifyNoMoreInteractions(eventService);
    }
}
