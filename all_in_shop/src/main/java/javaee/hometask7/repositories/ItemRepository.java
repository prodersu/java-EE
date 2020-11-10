package javaee.hometask7.repositories;

import javaee.hometask7.entities.Brands;
import javaee.hometask7.entities.Items;
import javaee.hometask7.services.ItemService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional

public interface ItemRepository extends JpaRepository<Items, Long> {
    List<Items> findAllByInTopPageIsTrue();
    List<Items> findAllByNameContainsOrderByPriceAsc(String query);
    List<Items> findAllByNameContainsOrderByPriceDesc(String query);

    List<Items> findAllByNameContainsAndBrandsIsAndPriceBetweenOrderByPriceAsc(String name, Brands brands, double price1, double price2);
    List<Items> findAllByNameContainsAndBrandsIsAndPriceBetweenOrderByPriceDesc(String name, Brands brands, double price1, double price2);

}
