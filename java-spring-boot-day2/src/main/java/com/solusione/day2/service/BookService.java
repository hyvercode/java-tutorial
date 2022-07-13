package com.solusione.day2.service;

import com.hyvercode.solusione.helpers.base.BaseResponse;
import com.hyvercode.solusione.helpers.base.ResponseBuilder;
import com.hyvercode.solusione.helpers.utils.CommonUtil;
import com.hyvercode.solusione.helpers.utils.Constant;
import com.hyvercode.solusione.model.PageRequest;
import com.hyvercode.solusione.service.CrudService;
import com.solusione.day2.model.entity.Book;
import com.solusione.day2.model.entity.User;
import com.solusione.day2.model.request.book.BookRequest;
import com.solusione.day2.model.response.user.UserResponse;
import com.solusione.day2.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class BookService implements CrudService<BookRequest, BaseResponse, PageRequest, String> {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BaseResponse create(BookRequest input) {
        final long start = CommonUtil.tok();
        Book book = User.builder()
                .email(input.getEmail())
                .password(input.getPassword())
                .active(input.getActive())
                .build();
        book.setCreatedBy(Constant.CREATOR);
        book.setCreatedAt(new Timestamp(System.currentTimeMillis()));
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
    public BaseResponse read(String id) {
        return null;
    }

    @Override
    public BaseResponse update(String id, BookRequest input) {
        return null;
    }

    @Override
    public BaseResponse delete(String id) {
        return null;
    }

    @Override
    public BaseResponse all(BookRequest input) {
        return null;
    }

    @Override
    public BaseResponse paginate(PageRequest input) {
        return null;
    }
}
