package api_media_mate_library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import api_media_mate_library.model.Category;
import api_media_mate_library.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // ➡️ GET ALL CATEGORIES
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    // ➡️ GET CATEGORY BY ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    // ➡️ CREATE CATEGORY
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // ➡️ UPDATE CATEGORY
    public Category updateCategory(Long id, Category updatedCategory) {
        Category existingCategory = getCategoryById(id);
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription());
        return categoryRepository.save(existingCategory);
    }

    // ➡️ DELETE CATEGORY
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
