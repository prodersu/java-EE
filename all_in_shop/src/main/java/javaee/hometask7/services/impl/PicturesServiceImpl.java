package javaee.hometask7.services.impl;

import javaee.hometask7.entities.Countries;
import javaee.hometask7.entities.Items;
import javaee.hometask7.entities.Pictures;
import javaee.hometask7.repositories.CountriesRepository;
import javaee.hometask7.repositories.PicturesRepository;
import javaee.hometask7.services.CountryService;
import javaee.hometask7.services.PicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicturesServiceImpl implements PicturesService {
    @Autowired
    private PicturesRepository picturesRepository;


    @Override
    public List<Pictures> getPicturesByItem(Items items) {
        return picturesRepository.findAllByItemEquals(items);
    }

    @Override
    public void addPictures(Pictures pictures) {
        picturesRepository.save(pictures);
    }

    @Override
    public void deletePicture(Pictures pictures) {
        picturesRepository.delete(pictures);
    }

    @Override
    public Pictures getPictureById(Long id) {
        return picturesRepository.findByIdEquals(id);
    }

}
