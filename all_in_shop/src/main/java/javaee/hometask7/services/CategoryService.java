package javaee.hometask7.services;

import javaee.hometask7.entities.Categories;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    List<Categories> getAllCategories();
    Categories addCategory(Categories category);
    Categories getCategory(Long id);
    Categories saveCategory(Categories categories);
    void deleteCategory(Categories categories);
}
