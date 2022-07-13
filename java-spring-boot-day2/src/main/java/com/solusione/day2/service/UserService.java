package com.solusione.day2.service;

import com.hyvercode.solusione.helpers.base.BaseResponse;
import com.hyvercode.solusione.helpers.base.ResponseBuilder;
import com.hyvercode.solusione.helpers.exception.BusinessException;
import com.hyvercode.solusione.helpers.utils.CommonUtil;
import com.hyvercode.solusione.helpers.utils.Constant;
import com.hyvercode.solusione.helpers.utils.PageableUtil;
import com.hyvercode.solusione.model.PageRequest;
import com.hyvercode.solusione.service.CrudService;
import com.solusione.day2.helpers.Constants;
import com.solusione.day2.model.entity.User;
import com.solusione.day2.model.request.user.UserRequest;
import com.solusione.day2.model.response.user.ListUserResponse;
import com.solusione.day2.model.response.user.UserResponse;
import com.solusione.day2.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements CrudService<UserRequest, BaseResponse, PageRequest, String> {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public BaseResponse create(UserRequest input) {
        final long start = CommonUtil.tok();
        User user = User.builder()
                .email(input.getEmail())
                .password(input.getPassword())
                .active(input.getActive())
                .build();
        user.setCreatedBy(Constant.CREATOR);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedBy(Constant.CREATOR);
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, ((end - start) / 1000000), Constant.PROCESS_SUCCESSFULLY,
                UserResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .active(user.getActive())
                        .build());
    }


    @Override
    public  BaseResponse read(String id) {
        final long start = CommonUtil.tok();

        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            logger.info(Constants.RESPONSE_CODE);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE, Constants.RESPONSE_MESSAGE);
        }

        User user = optional.get();

        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, ((end - start) / 1000000), Constant.PROCESS_SUCCESSFULLY,
                UserResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .active(user.getActive())
                        .build());
    }

    @Override
    public  BaseResponse update(String id, UserRequest input) {
        final long start = CommonUtil.tok();

        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE, Constants.RESPONSE_MESSAGE);
        }

        User user = optional.get();
        BeanUtils.copyProperties(input, user);
        user.setUpdatedBy(Constant.CREATOR);
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, ((end - start) / 1000000), Constant.PROCESS_SUCCESSFULLY,
                UserResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .active(user.getActive())
                        .build());
    }


    @Override
    public  BaseResponse delete(String id) {
        final long start = CommonUtil.tok();
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            logger.info(Constants.RESPONSE_CODE);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE, Constants.RESPONSE_MESSAGE);
        }
        userRepository.deleteById(id);

        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, ((end - start) / 1000000), Constant.PROCESS_SUCCESSFULLY);
    }

    @Override
    public  BaseResponse all(UserRequest input) {
        final long start = CommonUtil.tok();
        Iterable<User> users = userRepository.findAll();
        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, ((end - start) / 1000000), Constant.PROCESS_SUCCESSFULLY,
                users);
    }

    @Override
    public  BaseResponse paginate(PageRequest input) {
        final long start = CommonUtil.tok();

        Page<User> page = this.getPageResultByInput(input);
        Set<UserResponse> outletResponses = page.getContent().stream().map(outlet -> {
            UserResponse response = new UserResponse();
            BeanUtils.copyProperties(outlet, response);
            return response;
        }).collect(Collectors.toSet());

        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, ((end - start) / 1000000), Constant.PROCESS_SUCCESSFULLY,
                ListUserResponse.builder()
                        .content(outletResponses)
                        .pagination(PageableUtil.pageToPagination(page))
                        .build());
    }

    private Page<User> getPageResultByInput(PageRequest pageRequest) {
        String sortBy = pageRequest.getSortBy() != null && !pageRequest.getSortBy().isEmpty() ? pageRequest.getSortBy() : "created_at";
        Pageable pageable = PageableUtil.createPageRequest(pageRequest, pageRequest.getPageSize(), pageRequest.getPageNumber(),
                sortBy, pageRequest.getSortType());
        Page<User> page = null;
        if (pageRequest.getSearchBy() != null && pageRequest.getSortBy().equals("id")) {
            page = userRepository.findByEmailAndActive(pageRequest.getSearchBy(), true, pageable);
        }else{
            page = userRepository.findByActive(true, pageable);
        }
        return page;
    }
}
