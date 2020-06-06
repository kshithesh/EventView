package com.eventview.test;

import com.eventview.batch.ScheduleConfig;
import com.eventview.controller.EventTypesRestController;
import com.eventview.exceptions.*;
import com.eventview.model.EvenTypes;
import com.eventview.service.EventTypeService;
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
                .setControllerAdvice(new EventViewExceptionController())
                .build();
    }

    @Test
    public void getAllEventTypesTest() throws Exception {
        List<EvenTypes> evenTypes = Arrays.asList(new EvenTypes(1,
                        "birthday"),
                new EvenTypes(2, "anniversary"));

        when(eventTypeService.getAllEvenTypes()).thenReturn(evenTypes);

        mvc.perform(get("/event/types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].eventTypeId", is(1)))
                .andExpect(jsonPath("$[0].eventType", is("birthday")))
                .andExpect(jsonPath("$[1].eventType", is("anniversary")));

        verify(eventTypeService, times(1)).getAllEvenTypes();
        verifyNoMoreInteractions(eventTypeService);
    }

    @Test
    public void getAllEventTypes_No_EventType_Test() throws Exception {
        when(eventTypeService.getAllEvenTypes()).thenThrow(EventTypeNotFoundException.class);
        mvc.perform(get("/event/types"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findByEventTypeIdTest() throws Exception {
        EvenTypes evenTypes = new EvenTypes(1,
                "birthday");

        when(eventTypeService.findByEventTypeId(1)).thenReturn(evenTypes);

        mvc.perform(get("/event/type/{eventTypeId}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.eventTypeId").value(1))
                .andExpect(jsonPath("$.eventType").value("birthday"));

        verify(eventTypeService, times(1)).findByEventTypeId(1);
        verifyNoMoreInteractions(eventTypeService);
    }

    @Test
    public void findByEventTypeId_No_EventType_Test() throws Exception {
        when(eventTypeService.findByEventTypeId(1)).thenThrow(EventTypeNotFoundException.class);
        mvc.perform(get("/event/type/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createEventTypeTest() throws Exception {
        EvenTypes evenTypes = new EvenTypes(1, "birthday");
        String JSON = "{\n" +
                "  \"eventTypeId\": 1,\n" +
                "  \"eventType\":\"birthday\"\n" +
                "}";
        when(eventTypeService.exists(evenTypes)).thenReturn(false);
        doNothing().when(eventTypeService).createEventType(evenTypes);

        mvc.perform(
                post("/event/type")
                        .content(JSON).contentType("application/json;charset=UTF-8"))

                .andExpect(status().isCreated());
    }


    @Test
    public void createEventType_EventType_Exists_Test() throws Exception {
        EvenTypes evenTypes = new EvenTypes(1, "birthday");

        when(eventTypeService.exists(evenTypes)).thenReturn(true);
        doThrow(EventTypeExistsException.class).when(eventTypeService).createEventType(evenTypes);
        mvc.perform(post("/event/type"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updateEventTypeTest() throws Exception {
        EvenTypes evenTypes = new EvenTypes(1,
                "birthday");

        when(eventTypeService.findByEventTypeId(evenTypes.getEventTypeId())).thenReturn(evenTypes);
        doNothing().when(eventTypeService).updateEventType(evenTypes);

        mvc.perform(
                put("/event/type/{eventtypeid}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(evenTypes)))
                .andExpect(status().isOk());

    }

    @Test
    public void updateEventType_EventType_Not_Found_Test() throws Exception {
        when(eventTypeService.findByEventTypeId(1)).thenThrow(EventTypeNotFoundException.class);
        mvc.perform(delete("/event/type/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteEventTypeTest() throws Exception {
        EvenTypes evenTypes = new EvenTypes(1,
                "birthday");

        when(eventTypeService.findByEventTypeId(1)).thenReturn(evenTypes);
        doNothing().when(eventTypeService).deleteEventType(1);

        mvc.perform(
                delete("/event/type/{eventtypeid}", evenTypes.getEventTypeId()))
                .andExpect(status().isNoContent());

        verify(eventTypeService, times(1)).findByEventTypeId(evenTypes.getEventTypeId());
        verify(eventTypeService, times(1)).deleteEventType(evenTypes.getEventTypeId());
        verifyNoMoreInteractions(eventTypeService);
    }

    @Test
    public void deleteEventType_Not_Found_EventType_Test() throws Exception {
        when(eventTypeService.findByEventTypeId(1)).thenThrow(EventTypeNotFoundException.class);
        mvc.perform(delete("/event/type/1"))
                .andExpect(status().isNotFound());
    }
}
