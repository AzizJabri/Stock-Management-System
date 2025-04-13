package interfaces;

import models.Category;

import java.util.ArrayList;

public interface ICategoryService {
    void addCategory(String name, String description);

    void updateCategory(Category category);

    void deleteCategory(int id);

    Category getCategoryById(int id);

    ArrayList<Category> listCategories();
}
