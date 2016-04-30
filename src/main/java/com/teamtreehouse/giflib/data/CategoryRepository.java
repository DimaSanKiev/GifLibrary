package com.teamtreehouse.giflib.data;

import com.teamtreehouse.giflib.model.Category;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CategoryRepository {
    private static final List<Category> ALL_CATEGORIES = Arrays.asList(
        new Category(1, "Abstract"),
        new Category(2, "Animals"),
        new Category(3, "Cars"),
        new Category(4, "IT"),
        new Category(5, "People")
    );

    private Category findById(int id) {
        for (Category category : ALL_CATEGORIES) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    private List<Category> getAllCategories() {
        return ALL_CATEGORIES;
    }
}
