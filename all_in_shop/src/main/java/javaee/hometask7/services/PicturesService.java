package javaee.hometask7.services;

import javaee.hometask7.entities.Countries;
import javaee.hometask7.entities.Items;
import javaee.hometask7.entities.Pictures;

import java.util.List;

public interface PicturesService {
    List<Pictures> getPicturesByItem(Items items);
    void addPictures(Pictures pictures);
    void deletePicture(Pictures pictures);
    Pictures getPictureById(Long id);
}
