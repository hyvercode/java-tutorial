package com.solusione.day2.controller;

import com.solusione.day2.model.request.UserRequest;
import com.solusione.day2.model.response.UserResponse;
import com.solusione.day2.service.RabbitMQSenderService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    RabbitMQSenderService  rabbitMQSenderService;

    private ArrayList<UserResponse> userResponses = new ArrayList<>();

    public HelloWorldController(RabbitMQSenderService rabbitMQSenderService) {
        this.rabbitMQSenderService = rabbitMQSenderService;
    }

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

        ArrayList<UserResponse> userResponseArrayLists = new ArrayList<>();

        userResponseArrayLists.add(new UserResponse("aldy@gmail", "Aldy"));
        userResponseArrayLists.add(new UserResponse("wahyu@gmail", "Wahyu"));

        return userResponseArrayLists;
    }

    @PostMapping("/user")
    public UserResponse postUser(@RequestBody UserRequest userRequest) {
        return new UserResponse(userRequest.getEmail(), userRequest.getName());
    }

    @PostMapping("/users")
    public List<UserResponse> postUsers(@RequestBody UserRequest userRequest) {
        rabbitMQSenderService.send(userRequest);
        userResponses.add(new UserResponse(userRequest.getEmail(), userRequest.getName()));
        return userResponses;
    }
}
