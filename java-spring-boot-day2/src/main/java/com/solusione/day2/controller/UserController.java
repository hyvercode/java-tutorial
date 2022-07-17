package com.solusione.day2.controller;

import com.hyvercode.common.base.BasePaginationRequest;
import com.hyvercode.common.base.BaseResponse;
import com.solusione.day2.model.request.user.UserRequest;
import com.solusione.day2.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@Log4j2
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @ResponseBody
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllUsers(UserRequest request){
        return userService.all(request);
    }
    @ResponseBody
    @GetMapping(value = "/paginate", produces = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse getPaginateUsers(BasePaginationRequest request){
        return userService.paginate(request);
    }
    @ResponseBody
    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse createUsers(@RequestBody UserRequest userRequest){
        return userService.create(userRequest);
    }
    @ResponseBody
    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse updateUsers(@PathVariable String id,@RequestBody UserRequest userRequest){
        return userService.update(id,userRequest);
    }
    @ResponseBody
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse readUsers(@PathVariable String id){
        return userService.read(id);
    }
    @ResponseBody
    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse deleteUsers(@PathVariable String id){
        return userService.delete(id);
    }
}

