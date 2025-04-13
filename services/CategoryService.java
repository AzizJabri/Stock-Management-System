package services;

import interfaces.ICategoryService;
import models.Category;
import repositories.CategoryRepository;

import java.util.ArrayList;

public class CategoryService implements ICategoryService {

    private static CategoryService instance;
    private final CategoryRepository categoryRepository;


    private CategoryService() {
        this.categoryRepository = CategoryRepository.getInstance();
    }

    public static CategoryService getInstance() {
        if (instance == null) {
            instance = new CategoryService();
        }
        return instance;
    }

    public void addCategory(String name, String description) {
        try {
            categoryRepository.add(name, description);
        } catch (Exception e) {
            System.out.println("Error saving category: " + e.getMessage());
        }
    }

    public void updateCategory(Category category) {
        try {
            categoryRepository.update(category);
        } catch (Exception e) {
            System.out.println("Error updating category: " + e.getMessage());
        }
    }

    public void deleteCategory(int id) {
        try {
            categoryRepository.delete(id);
        } catch (Exception e) {
            System.out.println("Error deleting category: " + e.getMessage());
        }
    }

    public Category getCategoryById(int id) {
        try {
            return categoryRepository.getById(id);
        } catch (Exception e) {
            System.out.println("Error fetching category: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<Category> listCategories() {
        try {
            return categoryRepository.getAll();
        } catch (Exception e) {
            System.out.println("Error fetching categories: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
