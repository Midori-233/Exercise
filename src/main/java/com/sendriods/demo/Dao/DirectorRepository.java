package com.sendriods.demo.Dao;

import com.sendriods.demo.Domain.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
    Optional<Director> getDirectorByName(String name);

    Optional<Director> getDirectorById(Long id);
}
