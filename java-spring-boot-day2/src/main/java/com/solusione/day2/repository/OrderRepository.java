package com.solusione.day2.repository;

import com.solusione.day2.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order,String> {
    Page<Order> findByBookId(String bookId, Pageable pageable);
}
