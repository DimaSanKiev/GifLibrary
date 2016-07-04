package org.education.giflib.service;

import org.education.giflib.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void delete(Category category);
}