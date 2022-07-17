package com.solusione.day2.controller;

import com.hyvercode.common.base.BasePaginationRequest;
import com.hyvercode.common.base.BaseResponse;
import com.solusione.day2.model.request.book.BookRequest;
import com.solusione.day2.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse getAllBooks(BookRequest request){
        return bookService.all(request);
    }

    @GetMapping(value = "/paginate", produces = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse getPaginateBooks(BasePaginationRequest request){
        return bookService.paginate(request);
    }

    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse createBooks(@RequestBody BookRequest bookRequest){
        return bookService.create(bookRequest);
    }

    @PutMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse updateBooks(@PathVariable String id,@RequestBody BookRequest bookRequest){
        return bookService.update(id,bookRequest);
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse readBooks(@PathVariable String id){
        return bookService.read(id);
    }

    @DeleteMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public  BaseResponse deleteBooks(@PathVariable String id){
        return bookService.delete(id);
    }
}
