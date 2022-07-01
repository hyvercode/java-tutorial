package com.solusione.day2.controller;

import com.solusione.day2.model.request.UserRequest;
import com.solusione.day2.model.response.UserResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    private ArrayList<UserResponse> userResponses = new ArrayList<>();

    @GetMapping("/hello")
    public String getHello() {
        return "Hello World";
    }

    @GetMapping("/user")
    public UserResponse getUser() {
        return new UserResponse("aldy@gmail", "Aldy");
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers() {

        ArrayList<UserResponse> userResponses = new ArrayList<>();

        userResponses.add(new UserResponse("aldy@gmail", "Aldy"));
        userResponses.add(new UserResponse("wahyu@gmail", "Wahyu"));

        return userResponses;
    }

    @PostMapping("/user")
    public UserResponse postUser(@RequestBody UserRequest userRequest) {
        return new UserResponse(userRequest.getEmail(), userRequest.getName());
    }

    @PostMapping("/users")
    public List<UserResponse> postUsers(@RequestBody UserRequest userRequest) {
        userResponses.add(new UserResponse(userRequest.getEmail(), userRequest.getName()));
        return userResponses;
    }
}
