package com.eventview.test;

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
public class UsersControllerTest {

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
                .andExpect(jsonPath("$[0].user_id", is(1)))
                .andExpect(jsonPath("$[0].last_name", is("routhu")))
                .andExpect(jsonPath("$[1].user_id", is(2)))
                .andExpect(jsonPath("$[1].first_name", is("uma")));

        verify(userService, times(1)).getAllUsers();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void findByUserId() throws Exception {
        Users users = new Users(1, "kshithesh", " routhu", "9533916174",
                "kshithesh.r@gmail.com");

        when(userService.findByUserId(1)).thenReturn(users);

        mvc.perform(get("/user/{user_id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_id").value(1))
                .andExpect(jsonPath("$.first_name").value("kshithesh"));

        verify(userService, times(1)).findByUserId(1);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void createUser() throws Exception {
        Users users = new Users(1, "kshithesh", " routhu", "9533916174",
                "kshithesh.r@gmail.com");

        when(userService.exists(users)).thenReturn(false);
        doNothing().when(userService).createUser(users);

        mvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(users)))
                .andExpect(status().isCreated());
        //       .andExpect(jsonPath("$.user_id").value(1));

        verify(userService, times(1)).exists(users);
        verify(userService, times(1)).createUser(users);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void updateUser() throws Exception {
        Users users = new Users(1, "kshithesh", " routhu", "9533916174",
                "kshithesh.r@gmail.com");

        when(userService.findByUserId(users.getUser_id())).thenReturn(users);
        doNothing().when(userService).updateUser(users);

        mvc.perform(
                put("/user/{user_id}", users.getUser_id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(users)))
                .andExpect(status().isOk());

        verify(userService, times(1)).findByUserId(users.getUser_id());
        verify(userService, times(1)).updateUser(users);
        verifyNoMoreInteractions(userService);

    }

    @Test
    public void test_delete_user_success() throws Exception {
        Users users = new Users(1, "kshithesh", " routhu", "9533916174",
                "kshithesh.r@gmail.com");

        when(userService.findByUserId(users.getUser_id())).thenReturn(users);
        doNothing().when(userService).deleteUser(users.getUser_id());

        mvc.perform(
                delete("/user/{user_id}", users.getUser_id()))
                .andExpect(status().isOk());

        verify(userService, times(1)).findByUserId(users.getUser_id());
        verify(userService, times(1)).deleteUser(users.getUser_id());
        verifyNoMoreInteractions(userService);
    }
}
