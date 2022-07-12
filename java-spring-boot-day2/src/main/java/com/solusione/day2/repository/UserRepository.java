package com.solusione.day2.repository;

import com.solusione.day2.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,String> {

    Page<User> findByEmailAndActive(String currencyName, boolean active, Pageable pageable);
}
