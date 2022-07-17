package com.solusione.day2.service;

import com.hyvercode.common.base.BasePaginationRequest;
import com.hyvercode.common.base.BaseResponse;
import com.hyvercode.common.base.BaseResponseBuilder;
import com.hyvercode.common.exception.BusinessException;
import com.hyvercode.common.service.BaseCrudService;
import com.hyvercode.common.util.Constant;
import com.hyvercode.common.util.PageableUtil;
import com.solusione.day2.helpers.Constants;
import com.solusione.day2.model.entity.Book;
import com.solusione.day2.model.request.book.BookRequest;
import com.solusione.day2.model.response.book.BookResponse;
import com.solusione.day2.model.response.book.ListBookResponse;
import com.solusione.day2.repository.BookRepository;
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
public class BookService implements BaseCrudService<BookRequest, BaseResponse, BasePaginationRequest, String> {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BaseResponse create(BookRequest input) {
        Book book = Book.builder()
                .title(input.getTitle())
                .description(input.getDescription())
                .price(input.getPrice())
                .discount(input.getDiscount())
                .stock(input.getStock())
                .author(input.getAuthor())
                .active(input.getActive())
                .build();
        book.setCreatedBy(Constant.CREATOR);
        book.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        bookRepository.save(book);


        return new BaseResponseBuilder<>(Constant.CODE_OK,Constant.PROCESS_SUCCESSFULLY,
                BookResponse.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .description(book.getDescription())
                        .author(book.getAuthor())
                        .discount(book.getDiscount())
                        .price(book.getPrice())
                        .active(book.getActive())
                        .build());
    }

    @Override
    public BaseResponse read(String id) {

        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isEmpty()) {
            log.info(Constants.RESPONSE_MESSAGE_30020);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE_30020, Constants.RESPONSE_MESSAGE_30020);
        }

        Book book = optional.get();

        return new BaseResponseBuilder<>(Constant.CODE_OK,Constant.PROCESS_SUCCESSFULLY,
                BookResponse.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .description(book.getDescription())
                        .author(book.getAuthor())
                        .discount(book.getDiscount())
                        .price(book.getPrice())
                        .active(book.getActive())
                        .build());
    }

    @Override
    public BaseResponse update(String id, BookRequest input) {
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isEmpty()) {
            log.info(Constants.RESPONSE_MESSAGE_30020);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE_30020, Constants.RESPONSE_MESSAGE_30020);
        }

        Book book = optional.get();
        BeanUtils.copyProperties(input, book);
        book.setUpdatedBy(Constant.CREATOR);
        book.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        bookRepository.save(book);

        return new BaseResponseBuilder<>(Constant.CODE_OK,Constant.PROCESS_SUCCESSFULLY,
                BookResponse.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .description(book.getDescription())
                        .author(book.getAuthor())
                        .discount(book.getDiscount())
                        .price(book.getPrice())
                        .active(book.getActive())
                        .build());
    }

    @Override
    public BaseResponse delete(String id) {
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isEmpty()) {
            log.info(Constants.RESPONSE_MESSAGE_30020);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE_30020, Constants.RESPONSE_MESSAGE_30020);
        }
        bookRepository.deleteById(id);

        return new BaseResponseBuilder<>(Constant.CODE_OK,Constant.PROCESS_SUCCESSFULLY);
    }

    @Override
    public BaseResponse all(BookRequest input) {
        Iterable<Book> books = bookRepository.findAll();
        return new BaseResponseBuilder<>(Constant.CODE_OK,Constant.PROCESS_SUCCESSFULLY,
                books);
    }

    @Override
    public BaseResponse paginate(BasePaginationRequest input) {
        Page<Book> page = this.getPageResultByInput(input);

        Set<BookResponse> bookResponses = page.getContent().stream().map(book -> {
            BookResponse response = new BookResponse();
            BeanUtils.copyProperties(book, response);
            return response;
        }).collect(Collectors.toSet());

        return new BaseResponseBuilder<>(Constant.CODE_OK,Constant.PROCESS_SUCCESSFULLY,
                ListBookResponse.builder()
                        .content(bookResponses)
                        .pagination(PageableUtil.pageToPagination(page))
                        .build());

    }

    private Page<Book> getPageResultByInput(BasePaginationRequest pageRequest) {
        String sortBy = pageRequest.getSortBy() != null && !pageRequest.getSortBy().isEmpty() ? pageRequest.getSortBy() : "created_at";
        Pageable pageable = PageableUtil.createPageRequest(pageRequest, pageRequest.getPageSize(), pageRequest.getPageNumber(),
                sortBy, pageRequest.getSortType());
       return bookRepository.findAll(pageable);
    }
}
