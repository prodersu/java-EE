package javaee.hometask7.services;

import javaee.hometask7.entities.Countries;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CountryService {
    List<Countries> getAllCountries();
    Countries addCountry(Countries country);
    Countries getCountry(Long id);
    Countries saveCountry(Countries countries);
    void deleteCountry(Countries countries);
}
