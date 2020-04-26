package com.eventview.test;

import com.eventview.controller.EventsRestController;
import com.eventview.controller.UsersRestController;
import com.eventview.model.Users;
import com.eventview.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UsersControllerTest {

    private final Logger log = LoggerFactory.getLogger(EventsRestController.class);

    private MockMvc mvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UsersRestController usersRestController;

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
                .standaloneSetup(usersRestController)
                .build();
    }

    @Test
    public void getAllUser() throws Exception {
        List<Users> users = Arrays.asList(
                new Users(1, "kshithesh", "routhu", "9533916174",
                        "kshithesh.r@gmail.com"),
                new Users(2, "uma", "routhu", "9652976509", "uma@gmail.com"));

        when(userService.getAllUsers()).thenReturn(users);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userid", is(1)))
                .andExpect(jsonPath("$[0].lname", is("routhu")))
                .andExpect(jsonPath("$[1].userid", is(2)))
                .andExpect(jsonPath("$[1].fname", is("uma")));

        verify(userService, times(1)).getAllUsers();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void findByUserId() throws Exception {
        Users users = new Users(1, "kshithesh", " routhu", "9533916174",
                "kshithesh.r@gmail.com");

        when(userService.findByUserId(1)).thenReturn(users);

        mvc.perform(get("/user/{userid}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userid").value(1))
                .andExpect(jsonPath("$.fname").value("kshithesh"));

        verify(userService, times(1)).findByUserId(1);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testCreateUser() throws Exception {
        Users user1 = new Users(1, "kshithesh", " routhu", "9533916174",
                "kshithesh.r@gmail.com");

        when(userService.exists(user1)).thenReturn(false);
        doNothing().when(userService).createUser(user1);
        log.info("print user{}", user1);

        mvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user1)))
                .andExpect(status().isCreated());
                 //.andExpect(jsonPath("$.userid").value(1));

        //verify(userService, times(1)).exists(user1);
        //verify(userService, times(1)).createUser(user1);
        //verifyNoMoreInteractions(userService);
    }

    @Test
    public void updateUser() throws Exception {
        Users user = new Users(1, "kshithesh", " routhu", "9533916174",
                "kshithesh.r@gmail.com");
        when(userService.findByUserId(user.getUserid())).thenReturn(user);
        doNothing().when(userService).updateUser(user);
        log.info("success");

        mvc.perform(
                post("/user/{userid}", user.getUserid())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk());

        //verify(userService, times(1)).findByUserId(user.getUserid());
        //verify(userService, times(1)).updateUser(user);
        //verifyNoMoreInteractions(userService);

    }

    @Test
    public void deleteUser() throws Exception {
        Users users = new Users(1, "kshithesh", " routhu", "9533916174",
                "kshithesh.r@gmail.com");

        when(userService.findByUserId(users.getUserid())).thenReturn(users);
        doNothing().when(userService).deleteUser(users.getUserid());

        mvc.perform(
                delete("/user/{userid}", users.getUserid()))
                .andExpect(status().isOk());

        verify(userService, times(1)).findByUserId(users.getUserid());
        verify(userService, times(1)).deleteUser(users.getUserid());
        verifyNoMoreInteractions(userService);
    }
}
