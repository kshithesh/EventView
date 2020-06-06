package com.eventview.test;

import com.eventview.batch.ScheduleConfig;
import com.eventview.controller.UsersRestController;
import com.eventview.exceptions.EventTypeNotFoundException;
import com.eventview.exceptions.EventViewExceptionController;
import com.eventview.exceptions.UserExistsException;
import com.eventview.exceptions.UserNotFoundException;
import com.eventview.model.Users;
import com.eventview.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UsersControllerTest {

    private final Logger log = LoggerFactory.getLogger(UsersControllerTest.class);

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
                .setControllerAdvice(new EventViewExceptionController()).build();
    }

    @Test
    public void getAllUserTest() throws Exception {
        List<Users> users = Arrays.asList(
                new Users(1, "kshithesh", "routhu", "9533916174",
                        "kshithesh.r@gmail.com"),
                new Users(2, "uma", "routhu", "9652976509", "uma@gmail.com"));

        when(userService.getAllUsers()).thenReturn(users);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[1].userId", is(2)))
                .andExpect(jsonPath("$[1].phone", is("9652976509")));
        verify(userService, times(1)).getAllUsers();
        verifyNoMoreInteractions(userService);
    }


    @Test
    public void getAllUsers_No_User_Test() throws Exception {
        when(userService.getAllUsers()).thenThrow(UserNotFoundException.class);
        mvc.perform(get("/users"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void findByUserIdTest() throws Exception {
        Users users = new Users(1, "kshithesh", "routhu", "9533916174",
                "kshithesh.r@gmail.com");

        when(userService.findByUserId(1)).thenReturn(users);

        mvc.perform(get("/user/{userid}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.phone").value("9533916174"));

        verify(userService, times(1)).findByUserId(1);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void findByUserId_No_User_Test() throws Exception {
        when(userService.findByUserId(any())).thenThrow(UserNotFoundException.class);
        mvc.perform(get("/user/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createUserTest() throws Exception {
        Users user1 = new Users(1, "kshithesh", "routhu", "9533916174",
                "kshithesh.r@gmail.com");

        when(userService.exists(user1)).thenReturn(false);
        doNothing().when(userService).createUser(user1);

        mvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user1)))
                .andExpect(status().isCreated());
    }

    @Test
    public void createUser_User_Exists_Test() throws Exception {
        Users user1 = new Users(1, "kshithesh", "routhu", "9533916174",
                "kshithesh.r@gmail.com");

        when(userService.exists(user1)).thenReturn(true);
        doThrow(UserExistsException.class).when(userService).createUser(user1);
        mvc.perform(post("/user"))
                    .andExpect(status().isBadRequest());

    }

    @Test
    public void updateUserTest() throws Exception {
        Users user = new Users(1, "kshithesh", "routhu", "9533916174",
                "kshithesh.r@gmail.com");
        when(userService.findByUserId(user.getUserId())).thenReturn(user);
        doNothing().when(userService).updateUser(user);
        log.info("success");

        mvc.perform(
                put("/user/{userid}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk());

    }

    @Test
    public void updateUser_User_Not_Found_Test() throws Exception {
        when(userService.findByUserId(1)).thenThrow(UserNotFoundException.class);
        mvc.perform(delete("/user/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteUserTest() throws Exception {
        Users users = new Users(1, "kshithesh", "routhu", "9533916174",
                "kshithesh.r@gmail.com");

        when(userService.findByUserId(users.getUserId())).thenReturn(users);
        doNothing().when(userService).deleteUser(users.getUserId());

        mvc.perform(
                delete("/user/{userid}", users.getUserId()))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).findByUserId(users.getUserId());
        verify(userService, times(1)).deleteUser(users.getUserId());
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void deleteUser_Not_Found_User_Test() throws Exception {
        when(userService.findByUserId(1)).thenThrow(UserNotFoundException.class);
        mvc.perform(delete("/user/1"))
                .andExpect(status().isNotFound());
    }
}
