package com.solusione.day2.service;

import com.hyvercode.common.base.BasePaginationRequest;
import com.hyvercode.common.base.BaseResponse;
import com.hyvercode.common.base.BaseResponseBuilder;
import com.hyvercode.common.exception.BusinessException;
import com.hyvercode.common.service.BaseCrudService;
import com.hyvercode.common.util.Constant;
import com.hyvercode.common.util.PageableUtil;
import com.solusione.day2.helpers.Constants;
import com.solusione.day2.model.entity.User;
import com.solusione.day2.model.request.user.UserRequest;
import com.solusione.day2.model.response.user.ListUserResponse;
import com.solusione.day2.model.response.user.UserResponse;
import com.solusione.day2.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class UserService implements BaseCrudService<UserRequest, BaseResponse, BasePaginationRequest, String> {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public BaseResponse create(UserRequest input) {
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

        return new BaseResponseBuilder<>(Constant.CODE_OK,Constant.PROCESS_SUCCESSFULLY,
                UserResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .active(user.getActive())
                        .build());
    }


    @Override
    public  BaseResponse read(String id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            log.info(Constants.RESPONSE_CODE_30020);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE_30020, Constants.RESPONSE_MESSAGE_30020);
        }

        User user = optional.get();

        return new BaseResponseBuilder<>(Constant.CODE_OK,Constant.PROCESS_SUCCESSFULLY,
                UserResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .active(user.getActive())
                        .build());
    }

    @Override
    public  BaseResponse update(String id, UserRequest input) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE_30020, Constants.RESPONSE_MESSAGE_30020);
        }

        User user = optional.get();
        BeanUtils.copyProperties(input, user);
        user.setUpdatedBy(Constant.CREATOR);
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

        return new BaseResponseBuilder<>(Constant.CODE_OK,Constant.PROCESS_SUCCESSFULLY,
                UserResponse.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .active(user.getActive())
                        .build());
    }


    @Override
    public  BaseResponse delete(String id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            log.info(Constants.RESPONSE_CODE_30020);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE_30020, Constants.RESPONSE_MESSAGE_30020);
        }
        userRepository.deleteById(id);
        return new BaseResponseBuilder<>(Constant.CODE_OK,Constant.PROCESS_SUCCESSFULLY);
    }

    @Override
    public  BaseResponse all(UserRequest input) {
        Iterable<User> users = userRepository.findAll();
        return new BaseResponseBuilder<>(Constant.CODE_OK,Constant.PROCESS_SUCCESSFULLY,
                users);
    }

    @Override
    public  BaseResponse paginate(BasePaginationRequest input) {
        Page<User> page = this.getPageResultByInput(input);

        Set<UserResponse> outletResponses = page.getContent().stream().map(outlet -> {
            UserResponse response = new UserResponse();
            BeanUtils.copyProperties(outlet, response);
            return response;
        }).collect(Collectors.toSet());

        return new BaseResponseBuilder<>(Constant.CODE_OK,Constant.PROCESS_SUCCESSFULLY,
                ListUserResponse.builder()
                        .content(outletResponses)
                        .pagination(PageableUtil.pageToPagination(page))
                        .build());
    }

    private Page<User> getPageResultByInput(BasePaginationRequest pageRequest) {
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
