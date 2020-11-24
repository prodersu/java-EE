package javaee.hometask7.services.impl;

import javaee.hometask7.entities.Countries;
import javaee.hometask7.repositories.CountriesRepository;
import javaee.hometask7.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountriesRepository countriesRepository;


    @Override
    public List<Countries> getAllCountries() {
        return countriesRepository.findAll();
    }

    @Override
    public Countries addCountry(Countries country) {
        return countriesRepository.save(country);
    }

    @Override
    public Countries getCountry(Long id) {
        return countriesRepository.getOne(id);
    }

    @Override
    public Countries saveCountry(Countries countries) {
        return countriesRepository.save(countries);
    }

    @Override
    public void deleteCountry(Countries countries) {
        countriesRepository.delete(countries);
    }


}
