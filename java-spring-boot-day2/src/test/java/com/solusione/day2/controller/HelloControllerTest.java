package com.solusione.day2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solusione.day2.model.request.UserRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HelloWorldController.class)
public class HelloControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getHelloTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("Hello World", content);
    }

    @Test
    void getHelloFailTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertNotEquals("Hello", content);
    }

    @Test
    void getUserTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("{\"email\":\"aldy@gmail\",\"name\":\"Aldy\"}", content);
    }

    @Test
    void getUsersTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("[{\"email\":\"aldy@gmail\",\"name\":\"Aldy\"},{\"email\":\"wahyu@gmail\",\"name\":\"Wahyu\"}]", content);
    }

    @Test
    void postUserTest() throws Exception {
        UserRequest userRequest = new UserRequest("irwan@solusone.id", "irwan");

        MvcResult result = mockMvc.perform(post("/api/user")
                        .content(toJson(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertEquals("{\"email\":\"irwan@solusone.id\",\"name\":\"irwan\"}", content);
    }

    @Test
    void postUsersTest() throws Exception {
        UserRequest userRequest = new UserRequest("irwan@solusone.id", "irwan");

        MvcResult result = mockMvc.perform(post("/api/users")
                        .content(toJson(userRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    public static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
