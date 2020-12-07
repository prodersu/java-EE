package javaee.hometask7.repositories;

import javaee.hometask7.entities.Countries;
import javaee.hometask7.entities.Items;
import javaee.hometask7.entities.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PicturesRepository extends JpaRepository<Pictures, Long> {
    List<Pictures> findAllByItemEquals(Items items);
    Pictures findByIdEquals(Long id);
}
