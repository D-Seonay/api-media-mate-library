package api_media_mate_library.controller;


import org.springframework.web.bind.annotation.*;

import api_media_mate_library.model.Category;
import api_media_mate_library.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // ➡️ GET ALL CATEGORY
    @GetMapping
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

    // ➡️ GET CATEGORY BY ID
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    // ➡️ CREATE CATEGORY
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    // ➡️ UPDATE CATEGORY
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    // ➡️ DELETE CATEGORY
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
