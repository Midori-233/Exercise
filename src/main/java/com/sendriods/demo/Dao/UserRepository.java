package com.sendriods.demo.Dao;

import com.sendriods.demo.Domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    Optional<User> findById(long id);

    Page<User> findByAgeLessThan(int age, Pageable pageable);
}
