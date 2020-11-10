package javaee.hometask7.repositories;

import javaee.hometask7.entities.Brands;
import javaee.hometask7.entities.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface BrandsRepository extends JpaRepository<Brands, Long> {
    List<Brands> findAllByCountriesIs(Countries countries);
}
