package com.sendriods.demo.Dao;

import com.sendriods.demo.Domain.ERole;
import com.sendriods.demo.Domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
