package com.solusione.day2.repository;

import com.solusione.day2.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,String> {

    Page<User> findByEmailAndActive(String email, boolean active, Pageable pageable);
    Page<User> findByActive(boolean active, Pageable pageable);
    Optional<User>findByEmail(String email);
}
