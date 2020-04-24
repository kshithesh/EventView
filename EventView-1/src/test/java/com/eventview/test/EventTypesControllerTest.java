package com.eventview.test;

import com.eventview.controller.EventTypesRestController;
import com.eventview.controller.UsersRestController;
import com.eventview.model.EvenTypes;
import com.eventview.model.Users;
import com.eventview.service.EventTypeService;
import com.eventview.service.UserService;
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
public class EventTypesControllerTest {

    private MockMvc mvc;

    @Mock
    private EventTypeService eventTypeService;

    @InjectMocks
    private EventTypesRestController eventTypesRestController;

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
                .standaloneSetup(eventTypesRestController)
                .build();
    }

    @Test
    public void getAllEventTypes() throws Exception {
        List<EvenTypes> evenTypes = Arrays.asList(new EvenTypes(1,101,
                        "birthday"),
                new EvenTypes(2, 102, "anniversary"));

        when(eventTypeService.getAllEvenTypes()).thenReturn(evenTypes);

        mvc.perform(get("/types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].event_id", is(101)))
                .andExpect(jsonPath("$[0].event_type", is("birthday")))
                .andExpect(jsonPath("$[1].event_id", is(102)))
                .andExpect(jsonPath("$[1].event_type", is("anniversary")));

        verify(eventTypeService, times(1)).getAllEvenTypes();
        verifyNoMoreInteractions(eventTypeService);
    }


    @Test
    public void findByEventtypeId() throws Exception {
        EvenTypes evenTypes = new EvenTypes(1,101,
                "birthday");

        when(eventTypeService.findByEventtypeId(1)).thenReturn(evenTypes);

        mvc.perform(get("/type/{event_type_id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.event_type_id").value(1))
                .andExpect(jsonPath("$.event_type").value("birthday"));

        verify(eventTypeService, times(1)).findByEventtypeId(1);
        verifyNoMoreInteractions(eventTypeService);
    }

    @Test
    public void createEventType() throws Exception {
        EvenTypes evenTypes = new EvenTypes(1,101,
                "birthday");

        when(eventTypeService.exists(evenTypes)).thenReturn(false);
        doNothing().when(eventTypeService).createEventType(evenTypes);

        mvc.perform(
                post("/type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(evenTypes)))
                .andExpect(status().isCreated());
        //       .andExpect(jsonPath("$.user_id").value(1));

        verify(eventTypeService, times(1)).exists(evenTypes);
        verify(eventTypeService, times(1)).createEventType(evenTypes);
        verifyNoMoreInteractions(eventTypeService);
    }

    @Test
    public void updateEventType() throws Exception {
        EvenTypes evenTypes = new EvenTypes(1,101,
                "birthday");

        when(eventTypeService.findByEventtypeId(evenTypes.getEvent_type_id())).thenReturn(evenTypes);
        doNothing().when(eventTypeService).updateEventType(evenTypes);

        mvc.perform(
                put("/type/{event_type_id}", evenTypes.getEvent_type_id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(evenTypes)))
                .andExpect(status().isOk());

        verify(eventTypeService, times(1)).findByEventtypeId(evenTypes.getEvent_type_id());
        verify(eventTypeService, times(1)).updateEventType(evenTypes);
        verifyNoMoreInteractions(eventTypeService);

    }


    @Test
    public void deleteEventType() throws Exception {
        EvenTypes evenTypes = new EvenTypes(1,101,
                "birthday");

        when(eventTypeService.findByEventtypeId(evenTypes.getEvent_type_id())).thenReturn(evenTypes);
        doNothing().when(eventTypeService).deleteEventType(evenTypes.getEvent_type_id());

        mvc.perform(
                delete("/type/{event_type_id}", evenTypes.getEvent_type_id()))
                .andExpect(status().isOk());

        verify(eventTypeService, times(1)).findByEventtypeId(evenTypes.getEvent_type_id());
        verify(eventTypeService, times(1)).deleteEventType(evenTypes.getEvent_type_id());
        verifyNoMoreInteractions(eventTypeService);
    }
}
