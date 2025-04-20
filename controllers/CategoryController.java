package controllers;

import models.Category;
import services.CategoryService;

import java.util.ArrayList;

public class CategoryController {
    private static CategoryController instance;
    private final CategoryService categoryService;

    private CategoryController() {

        this.categoryService = CategoryService.getInstance();
    }

    public static CategoryController getInstance() {
        if (instance == null) {
            instance = new CategoryController();
        }
        return instance;
    }

    public void addCategory(String name, String description) {
        categoryService.addCategory(name, description);
    }

    public void updateCategory(Category category) {
        categoryService.updateCategory(category);
    }

    public void deleteCategory(int id) {
        categoryService.deleteCategory(id);
    }

    public Category getCategoryById(int id) {
        return categoryService.getCategoryById(id);
    }

    public ArrayList<Category> listCategories() {
        return categoryService.listCategories();
    }
}
