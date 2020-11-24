package javaee.hometask7.services.impl;

import javaee.hometask7.entities.Categories;
import javaee.hometask7.repositories.CategoriesRepository;
import javaee.hometask7.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public Categories addCategory(Categories category) {
        return categoriesRepository.save(category);
    }

    @Override
    public Categories getCategory(Long id) {
        return categoriesRepository.getOne(id);
    }

    @Override
    public Categories saveCategory(Categories categories) {
        return categoriesRepository.save(categories);
    }

    @Override
    public void deleteCategory(Categories categories) {
        categoriesRepository.delete(categories);
    }
}
