package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.ERole;
import com.nnk.springboot.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
