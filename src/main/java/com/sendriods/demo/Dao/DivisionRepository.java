package com.sendriods.demo.Dao;

import com.sendriods.demo.Domain.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {
    Division findByDivisionName(String name);

    Division findById(long id);
}
