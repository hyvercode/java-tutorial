package com.solusione.day2.controller.service;

import com.hyvercode.common.base.BasePaginationRequest;
import com.hyvercode.common.base.BaseResponse;
import com.hyvercode.common.base.BaseResponseBuilder;
import com.hyvercode.common.util.Constant;
import com.hyvercode.common.util.PageableUtil;
import com.solusione.day2.model.entity.Book;
import com.solusione.day2.model.request.book.BookRequest;
import com.solusione.day2.model.response.book.BookResponse;
import com.solusione.day2.model.response.book.ListBookResponse;
import com.solusione.day2.repository.BookRepository;
import com.solusione.day2.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BooksServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper modelMapper;

    private BookRequest bookRequest;
    private BookResponse bookResponse;

    private Book book;

    List<Book> books = new ArrayList<>();

    Iterable<Book>bookIterable;


    @BeforeEach
    void setUp() {
        bookRequest = BookRequest.builder()
                .title("Java")
                .description("Learning Java")
                .discount(new BigDecimal("10000"))
                .price(new BigDecimal("10000"))
                .stock(100)
                .author("Spring")
                .active(true)
                .build();

        bookResponse = BookResponse.builder()
                .id("1")
                .title("Java")
                .description("Learning Java")
                .discount(new BigDecimal("10000"))
                .price(new BigDecimal("10000"))
                .stock(100)
                .author("Spring")
                .active(true)
                .build();

        book = Book.builder()
                .title("Java")
                .description("Learning Java")
                .discount(new BigDecimal("10000"))
                .price(new BigDecimal("10000"))
                .stock(100)
                .author("Spring")
                .active(true)
                .build();
        book.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        book.setCreatedBy(Constant.CREATOR);
        book.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        book.setUpdatedBy(Constant.CREATOR);

        books.add(book);
        bookIterable  =books;
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(modelMapper);
        verifyNoMoreInteractions(bookRepository);
    }


    @Test
    void createTest() throws Exception {
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BaseResponseBuilder<BookResponse> actual = (BaseResponseBuilder<BookResponse>) bookService.create(bookRequest);
        assertEquals(actual.getContent().getActive(),book.getActive());
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    void updateTest() throws Exception {
        when(bookRepository.findById(any(String.class))).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BaseResponseBuilder<BookResponse> actual = (BaseResponseBuilder<BookResponse>) bookService.update("1",bookRequest);
        assertEquals(actual.getContent().getActive(),book.getActive());
        verify(bookRepository).save(any(Book.class));
        verify(bookRepository).findById(any(String.class));
    }

    @Test
    void readTest() throws Exception {
        when(bookRepository.findById(any(String.class))).thenReturn(Optional.of(book));

        BaseResponseBuilder<BookResponse> actual = (BaseResponseBuilder<BookResponse>) bookService.read("1");
        assertEquals(actual.getContent().getActive(),book.getActive());
        verify(bookRepository).findById(any(String.class));
    }

    @Test
    void deleteTest() throws Exception {

        when(bookRepository.findById(any(String.class))).thenReturn(Optional.of(book));
        willDoNothing().given(bookRepository).deleteById(anyString());

        BaseResponseBuilder<BookResponse> actual = (BaseResponseBuilder<BookResponse>) bookService.delete("1");
        assertEquals(actual.getCode(),Constant.CODE_OK);

        verify(bookRepository).deleteById(anyString());
        verify(bookRepository).findById(any(String.class));
    }

    @Test
    void allTest() throws Exception {

        when(bookRepository.findAll()).thenReturn(books);

        BaseResponseBuilder<BookResponse> actual = (BaseResponseBuilder<BookResponse>) bookService.all(new BookRequest());
        Assertions.assertNotNull(actual);

        verify(bookRepository).findAll();
    }

//    @Test
//    void paginateTest() throws Exception {
//
//        Page<Book> bookPage= new PageImpl(Collections.singletonList(books));
//        BasePaginationRequest pageRequest = new BasePaginationRequest();
//        pageRequest.setPageNumber(1);
//        pageRequest.setPageSize(10);
//        pageRequest.setSortBy("title");
//        pageRequest.setSortType("ASC");
//
//        String sortBy = pageRequest.getSortBy() != null && !pageRequest.getSortBy().isEmpty() ? pageRequest.getSortBy() : "created_at";
//        Pageable pageableTest = PageableUtil.createPageRequest(pageRequest, pageRequest.getPageSize(), pageRequest.getPageNumber(),
//                sortBy, pageRequest.getSortType());
//        when(bookRepository.findAll(pageableTest)).thenReturn(bookPage);
//
//        BaseResponseBuilder<BookResponse> actual = (BaseResponseBuilder<BookResponse>) bookService.paginate(pageRequest);
//        assertEquals(actual.getContent(),books);
//
//        verify(bookRepository).findAll(pageableTest);
//    }

}
