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
import com.solusione.day2.model.entity.Book;
import com.solusione.day2.model.request.book.BookRequest;
import com.solusione.day2.model.response.book.BookResponse;
import com.solusione.day2.model.response.book.ListBookResponse;
import com.solusione.day2.repository.BookRepository;
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
public class BookService implements CrudService<BookRequest, BaseResponse, PageRequest, String> {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BaseResponse create(BookRequest input) {
        final long start = CommonUtil.tok();
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

        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, CommonUtil.calculateTok(start, end), Constant.PROCESS_SUCCESSFULLY,
                BookResponse.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .description(book.getDescription())
                        .price(book.getPrice())
                        .discount(book.getDiscount())
                        .stock(book.getStock())
                        .author(book.getAuthor())
                        .active(book.getActive())
                        .build());
    }

    @Override
    public BaseResponse read(String id) {
        final long start = CommonUtil.tok();

        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isEmpty()) {
            logger.info(Constants.RESPONSE_MESSAGE);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE, Constants.RESPONSE_MESSAGE);
        }

        Book book = optional.get();

        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, CommonUtil.calculateTok(start, end), Constant.PROCESS_SUCCESSFULLY,
                BookResponse.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .description(book.getDescription())
                        .price(book.getPrice())
                        .discount(book.getDiscount())
                        .stock(book.getStock())
                        .author(book.getAuthor())
                        .active(book.getActive())
                        .build());
    }

    @Override
    public BaseResponse update(String id, BookRequest input) {
        final long start = CommonUtil.tok();

        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isEmpty()) {
            logger.info(Constants.RESPONSE_MESSAGE);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE, Constants.RESPONSE_MESSAGE);
        }

        Book book = optional.get();
        BeanUtils.copyProperties(input, book);
        book.setUpdatedBy(Constant.CREATOR);
        book.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        bookRepository.save(book);

        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, CommonUtil.calculateTok(start, end), Constant.PROCESS_SUCCESSFULLY,
                BookResponse.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .description(book.getDescription())
                        .price(book.getPrice())
                        .discount(book.getDiscount())
                        .stock(book.getStock())
                        .author(book.getAuthor())
                        .active(book.getActive())
                        .build());
    }

    @Override
    public BaseResponse delete(String id) {
        final long start = CommonUtil.tok();
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isEmpty()) {
            logger.info(Constants.RESPONSE_MESSAGE);
            throw new BusinessException(HttpStatus.CONFLICT, Constants.RESPONSE_CODE, Constants.RESPONSE_MESSAGE);
        }
        bookRepository.deleteById(id);
        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, CommonUtil.calculateTok(start,end), Constant.PROCESS_SUCCESSFULLY);
    }

    @Override
    public BaseResponse all(BookRequest input) {
        final long start = CommonUtil.tok();
        Iterable<Book> books = bookRepository.findAll();
        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK, CommonUtil.calculateTok(start,end), Constant.PROCESS_SUCCESSFULLY,
                books);
    }

    @Override
    public BaseResponse paginate(PageRequest input) {
        final long start = CommonUtil.tok();

        Page<Book> page = this.getPageResultByInput(input);

        Set<BookResponse> bookResponses = page.getContent().stream().map(outlet -> {
            BookResponse response = new BookResponse();
            BeanUtils.copyProperties(outlet, response);
            return response;
        }).collect(Collectors.toSet());

        final long end = CommonUtil.tok();

        return ResponseBuilder.buildResponse(HttpStatus.OK,CommonUtil.calculateTok(start,end), Constant.PROCESS_SUCCESSFULLY,
                ListBookResponse.builder()
                        .content(bookResponses)
                        .pagination(PageableUtil.pageToPagination(page))
                        .build());
    }

    private Page<Book> getPageResultByInput(PageRequest pageRequest) {
        String sortBy = pageRequest.getSortBy() != null && !pageRequest.getSortBy().isEmpty() ? pageRequest.getSortBy() : "created_at";
        Pageable pageable = PageableUtil.createPageRequest(pageRequest, pageRequest.getPageSize(), pageRequest.getPageNumber(),
                sortBy, pageRequest.getSortType());
        Page<Book> page = null;
        if (pageRequest.getSearchBy() != null && pageRequest.getSortBy().equals("id")) {
            page = bookRepository.findByEmailAndActive(pageRequest.getSearchBy(), true, pageable);
        }else{
            page = bookRepository.findByActive(true, pageable);
        }
        return page;
    }
}
