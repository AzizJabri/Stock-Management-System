package controllers;

import models.Category;
import services.CategoryService;

import java.util.ArrayList;
import java.util.Scanner;

public class CategoryController {
    private static CategoryController instance;
    private final CategoryService categoryService;

    private final Scanner scanner = new Scanner(System.in);

    private CategoryController() {

        this.categoryService = CategoryService.getInstance();
    }

    public static CategoryController getInstance() {
        if (instance == null) {
            instance = new CategoryController();
        }
        return instance;
    }

    public void addCategory() {
        try{
            System.out.print("Enter category name: ");
            String name = scanner.nextLine();
            System.out.print("Enter category description: ");
            String description = scanner.nextLine();
            categoryService.addCategory(name, description);
            System.out.println("Category added successfully.");
        }catch (Exception e){
            System.out.println("Error while adding category: " + e.getMessage());
        }
    }

    public void updateCategory() {
        Category category = getCategoryById();
        if (category == null) {
            System.out.println("Category not found.");
            return;
        }
        System.out.print("Enter new category name (leave blank to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            category.setName(name);
        }
        System.out.print("Enter new category description (leave blank to keep current): ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) {
            category.setDescription(description);
        }
        if (name.isEmpty() && description.isEmpty()) {
            System.out.println("No changes made.");
            return;
        }
        categoryService.updateCategory(category);
        System.out.println("Category updated successfully.");
    }

    public void deleteCategory() {
        Category category = getCategoryById();
        if (category == null) {
            System.out.println("Category not found.");
            return;
        }
        categoryService.deleteCategory(category.getId());
        System.out.println("Category deleted successfully.");
    }

    public Category getCategoryById() {
        System.out.print("Enter category ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            System.out.println("Category not found.");
            return null;
        }
        return category;
    }

    public void listCategories() {
        ArrayList<Category> categories = categoryService.listCategories();
        if (categories.isEmpty()) {
            System.out.println("No categories found.");
            return;
        }
        System.out.println("Categories:");
        for (Category category : categories) {
            System.out.println("ID: " + category.getId() + ", Name: " + category.getName() + ", Description: " + category.getDescription());
        }
    }
}
