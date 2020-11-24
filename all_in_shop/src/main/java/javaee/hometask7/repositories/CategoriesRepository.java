package javaee.hometask7.repositories;

import javaee.hometask7.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CategoriesRepository extends JpaRepository<Categories, Long> {


}
