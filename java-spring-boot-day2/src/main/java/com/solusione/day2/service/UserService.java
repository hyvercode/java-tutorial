package com.solusione.day2.service;

import com.hyvercode.solusione.helpers.exception.BusinessException;
import com.hyvercode.solusione.helpers.utils.CommonUtil;
import com.hyvercode.solusione.helpers.utils.Constant;
import com.hyvercode.solusione.service.CrudService;
import com.solusione.day2.helpers.Constants;
import com.solusione.day2.model.entity.User;
import com.solusione.day2.model.request.user.ListUserRequest;
import com.solusione.day2.model.request.user.UserRequest;
import com.solusione.day2.model.response.user.ListUserResponse;
import com.solusione.day2.model.response.user.UserResponse;
import com.solusione.day2.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserService implements CrudService<UserRequest, UserResponse, ListUserRequest, ListUserResponse, String> {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserResponse create(UserRequest input) {
        User user = User.builder()
                .id(CommonUtil.generateUUIDString())
                .email(input.getEmail())
                .password(bcryptEncoder.encode(input.getPassword()))
                .active(input.getActive())
                .build();
        user.setCreatedBy(Constant.CREATOR);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .active(user.getActive())
                .build();
    }

    @Override
    public UserResponse read(String id) {
        Optional<User> optional = userRepository.findById(id);
        if(optional.isEmpty()){
            logger.info(Constants.RESPONSE_CODE);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE,Constants.RESPONSE_MESSAGE);
        }

        User user = optional.get();
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .active(user.getActive())
                .build();
    }

    @Override
    public UserResponse update(String id,UserRequest input) {

        Optional<User> optional = userRepository.findById(id);
        if(optional.isEmpty()){
            throw new BusinessException(HttpStatus.CONFLICT,Constants.RESPONSE_CODE,Constants.RESPONSE_MESSAGE);
        }

        User user = optional.get();
        BeanUtils.copyProperties(input,user);
        user.setUpdatedBy(Constant.CREATOR);
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .active(user.getActive())
                .build();
    }

    @Override
    public ListUserResponse paginate(ListUserRequest input) {
        return null;
    }

    @Override
    public UserResponse delete(String id) {
        return null;
    }
}