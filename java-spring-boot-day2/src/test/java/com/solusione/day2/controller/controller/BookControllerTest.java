package com.solusione.day2.controller.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyvercode.common.util.Constant;
import com.solusione.day2.controller.BookController;
import com.solusione.day2.model.entity.Book;
import com.solusione.day2.model.request.book.BookRequest;
import com.solusione.day2.model.response.book.BookResponse;
import com.solusione.day2.service.BookService;;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @InjectMocks
    private BookController bookController;
    @Mock
    BookService bookService;


    private MockMvc mockMvc;

    private BookRequest bookRequest;
    private BookResponse bookResponse;

    private Book book;

    List<Book>books = new ArrayList<>();

    Iterable<Book>bookIterable;

    @BeforeEach
    public void setup() {
        bookController = new BookController(bookService);
        mockMvc = standaloneSetup(bookController).build();

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

    @Test
    void getListBookTest() throws Exception {
        mockMvc.perform(get("/api/v1/books")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void getPaginateBookTest() throws Exception {
        mockMvc.perform(get("/api/v1/books/paginate?pageSize=10&pageNumber=1&sortBy=id&sortType=asc&searchBy=")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void postCreateBooksTest() throws Exception {
        //WHEN
        when(bookService.create(bookRequest)).thenReturn(bookResponse);

        mockMvc.perform(post("/api/v1/books")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(bookRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void putUpdateBooksTest() throws Exception {
        //WHEN
        when(bookService.update("1",bookRequest)).thenReturn(bookResponse);

        mockMvc.perform(put("/api/v1/books/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(bookRequest))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void getReadBooksTest() throws Exception {
        //WHEN
        when(bookService.read("1")).thenReturn(bookResponse);

        mockMvc.perform(get("/api/v1/books/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void deleteBooksTest() throws Exception {
        //WHEN
        when(bookService.delete("1")).thenReturn(bookResponse);

        mockMvc.perform(delete("/api/v1/books/1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

}
