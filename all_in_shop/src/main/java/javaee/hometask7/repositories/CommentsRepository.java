package javaee.hometask7.repositories;

import javaee.hometask7.entities.Brands;
import javaee.hometask7.entities.Categories;
import javaee.hometask7.entities.Comment;
import javaee.hometask7.entities.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional

public interface CommentsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByItemEquals(Items items);
}
