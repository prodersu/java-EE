package javaee.hometask7.services;

import javaee.hometask7.entities.Brands;
import javaee.hometask7.entities.Countries;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BrandService {
    List<Brands> getAllByCountriesIs(Countries countries);
    List<Brands> getAllBrands();
    Brands getBrand(Long id);
    Brands addBrand(Brands brand);
    Brands saveBrand(Brands brand);
    void deleteBrand(Brands brands);
}
