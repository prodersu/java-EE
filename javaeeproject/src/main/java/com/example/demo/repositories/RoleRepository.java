package com.example.demo.repositories;

import com.example.demo.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Roles, Long> {

    Roles findByRole(String name);

    Roles findByIdEquals(Long id);
}
