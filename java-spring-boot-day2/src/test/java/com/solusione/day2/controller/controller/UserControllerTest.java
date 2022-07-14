package com.solusione.day2.controller.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solusione.day2.controller.UserController;
import com.solusione.day2.model.request.user.UserRequest;
import com.solusione.day2.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    private UserRequest userRequest;

    @BeforeEach
    public void setup() {
        userController =new UserController(userService);
        mockMvc = standaloneSetup(userController).build();
        userRequest = UserRequest.builder()
                .email("solusione@gmail.com")
                .password("1234@123")
                .active(true)
                .build();

    }

    @Test
    void getAllTest() throws Exception {
        mockMvc.perform(get("/api/v1/users")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void getPaginateTest() throws Exception {
        mockMvc.perform(get("/api/v1/users/paginate?pageSize=10&pageNumber=1&sortBy=id&sortType=asc&searchBy=")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void postCreateTest() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void postUpdateTest() throws Exception {
        mockMvc.perform(put("/api/v1/users/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(userRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void getReadTest() throws Exception {
        mockMvc.perform(get("/api/v1/users/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/v1/users/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }
}
