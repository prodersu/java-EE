package javaee.hometask7.services;

import javaee.hometask7.entities.Brands;
import javaee.hometask7.entities.Countries;
import javaee.hometask7.entities.Items;

import java.util.List;

public interface ItemService {
    Items addItem(Items item);
    List<Items> getAllItems();
    List<Items> getInTopPageItems();
    Items getItem(Long id);

    List<Items> getItemsByQueryOrderByPriceDesc(String query);

    void deleteItem(Items items);
    Items saveItem(Items items);
    List<Items> getItemsByQueryAsc(String query);
    List<Items> getItemsByNameContainsAndBrandsIsAndPriceBetweenOrderByPriceAsc(String name, Brands brand, double price1, double price2);
    List<Items> getItemsByNameContainsAndBrandsIsAndPriceBetweenOrderByPriceDesc(String name, Brands brand, double price1, double price2);
    List<Brands> getAllByCountriesIs(Countries countries);
    List<Countries> getAllCountries();
    Countries addCountry(Countries country);
    Countries getCountry(Long id);

    List<Brands> getAllBrands();
    Brands getBrand(Long id);
    Brands addBrand(Brands brand);
}
