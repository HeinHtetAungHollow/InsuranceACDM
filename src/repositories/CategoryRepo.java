package repositories;

import models.Category;

import java.util.List;

public interface CategoryRepo {
    void saveCategory(Category category);

    void updateCategory(String id, Category category);

    List<Category> findAllCategorys();

    Category findById(String id);

    void deleteCategory(String id);
}
