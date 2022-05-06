package com.sendriods.demo.Dao;

import com.sendriods.demo.Domain.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {
    //  对于返回单个查询结果的api 我们都会默认包个 Optional
    Optional<Division> findByDivisionName(String name);

    Optional<Division> findById(long id);
}
