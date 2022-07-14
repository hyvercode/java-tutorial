package com.solusione.day2.controller.service;

import com.hyvercode.solusione.helpers.base.BaseResponse;
import com.hyvercode.solusione.helpers.base.ResponseBuilder;
import com.hyvercode.solusione.helpers.utils.Constant;
import com.solusione.day2.model.entity.Book;
import com.solusione.day2.model.request.book.BookRequest;
import com.solusione.day2.model.response.book.BookResponse;
import com.solusione.day2.model.response.user.UserResponse;
import com.solusione.day2.repository.BookRepository;
import com.solusione.day2.service.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BooksServiceTest {

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


//    @Test
//    void createTest() throws Exception {
//        when(bookRepository.save(any(Book.class))).thenReturn(book);
//
//        BookResponse actual =bookService.create(bookRequest);
//        assertEquals(actual.getActive(),book.getActive());
//        verify(bookRepository).save(any(Book.class));
//    }

}
