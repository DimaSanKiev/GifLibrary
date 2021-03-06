package org.education.giflib.service;

import org.education.giflib.dao.CategoryDao;
import org.education.giflib.exception.CategoryNotEmptyException;
import org.education.giflib.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void delete(Category category) {
        if (category.getGifs().size() > 0) {
            throw new CategoryNotEmptyException();
        }
        categoryDao.delete(category);
    }
}
