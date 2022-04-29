package com.sendriods.demo.Dao;

import com.sendriods.demo.Domain.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {
// TODO 对于返回单个查询结果的api 我们都会默认包个 Optional
//    Optional<Division> findByDivisionName(String name);
    Division findByDivisionName(String name);

    Division findById(long id);
}
