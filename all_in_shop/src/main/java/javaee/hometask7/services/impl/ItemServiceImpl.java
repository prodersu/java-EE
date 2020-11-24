package javaee.hometask7.services.impl;

import javaee.hometask7.entities.Brands;
import javaee.hometask7.entities.Categories;
import javaee.hometask7.entities.Countries;
import javaee.hometask7.entities.Items;
import javaee.hometask7.repositories.BrandsRepository;
import javaee.hometask7.repositories.CountriesRepository;
import javaee.hometask7.repositories.ItemRepository;
import javaee.hometask7.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    private ItemRepository itemRepository;


    @Override
    public Items addItem(Items item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Items> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Items> getInTopPageItems() {
        return itemRepository.findAllByInTopPageIsTrue();
    }

    @Override
    public Items getItem(Long id) {
        return itemRepository.getOne(id);
    }

    @Override
    public List<Items> getItemsByQueryAsc(String query) {
        return itemRepository.findAllByNameContainsOrderByPriceAsc(query);
    }

    @Override
    public List<Items> getItemsByNameContainsAndBrandsIsAndPriceBetweenOrderByPriceAsc(String name, Brands brands, double price1, double price2) {
        return itemRepository.findAllByNameContainsAndBrandsIsAndPriceBetweenOrderByPriceAsc(name, brands, price1, price2);
    }

    @Override
    public List<Items> getItemsByNameContainsAndBrandsIsAndPriceBetweenOrderByPriceDesc(String name, Brands brands, double price1, double price2) {
        return itemRepository.findAllByNameContainsAndBrandsIsAndPriceBetweenOrderByPriceDesc(name, brands, price1, price2);
    }

    @Override
    public List<Items> getItemsByCategoriesContains(Categories categories) {
        return itemRepository.findAllByCategoriesContains(categories);
    }

    @Override
    public List<Items> getItemsByQueryOrderByPriceDesc(String query) {
        return itemRepository.findAllByNameContainsOrderByPriceDesc(query);
    }

    @Override
    public void deleteItem(Items items) {
        itemRepository.delete(items);
    }

    @Override
    public Items saveItem(Items items) {
        return itemRepository.save(items);
    }

}
