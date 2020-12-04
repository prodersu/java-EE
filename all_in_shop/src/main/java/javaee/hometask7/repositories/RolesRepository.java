package javaee.hometask7.repositories;

import javaee.hometask7.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RolesRepository extends JpaRepository<Roles, Long> {

    Roles findByRole(String name);

    Roles findByIdEquals(Long id);


}
