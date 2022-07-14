package com.solusione.day2.repository;

import com.solusione.day2.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends PagingAndSortingRepository<Book,String> {
    @Override
    List<Book> findAll();
    Page<Book> findByTitleAndActive(String title, boolean active, Pageable pageable);
    Page<Book> findByActive(boolean active, Pageable pageable);
}