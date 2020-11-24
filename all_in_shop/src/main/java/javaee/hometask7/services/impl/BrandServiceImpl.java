package javaee.hometask7.services.impl;

import javaee.hometask7.entities.Brands;
import javaee.hometask7.entities.Countries;
import javaee.hometask7.repositories.BrandsRepository;
import javaee.hometask7.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandsRepository brandsRepository;

    @Override
    public List<Brands> getAllByCountriesIs(Countries countries) {
        return brandsRepository.findAllByCountriesIs(countries);
    }

    @Override
    public List<Brands> getAllBrands() {
        return brandsRepository.findAll();
    }

    @Override
    public Brands getBrand(Long id) {
        return brandsRepository.getOne(id);
    }

    @Override
    public Brands addBrand(Brands brand) {
        return brandsRepository.save(brand);
    }

    @Override
    public Brands saveBrand(Brands brand) {
        return brandsRepository.save(brand);
    }

    @Override
    public void deleteBrand(Brands brands) {
        brandsRepository.delete(brands);
    }

}
