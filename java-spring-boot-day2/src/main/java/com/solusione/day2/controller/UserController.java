package com.solusione.day2.controller;

import com.hyvercode.solusione.helpers.base.BaseResponse;
import com.hyvercode.solusione.helpers.base.ResponseBuilder;
import com.hyvercode.solusione.model.PageRequest;
import com.solusione.day2.model.request.user.UserRequest;
import com.solusione.day2.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse getAllUsers(UserRequest request){
        return userService.all(request);
    }

    @GetMapping(value = "/paginate", produces = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse getPaginateUsers(PageRequest request){
        return userService.paginate(request);
    }

    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse createUsers(@RequestBody UserRequest userRequest){
        return userService.create(userRequest);
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse updateUsers(@PathVariable String id,@RequestBody UserRequest userRequest){
        return userService.update(id,userRequest);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse readUsers(@PathVariable String id){
        return userService.read(id);
    }

    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse deleteUsers(@PathVariable String id){
        return userService.delete(id);
    }
}
