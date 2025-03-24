package api_media_mate_library.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import api_media_mate_library.model.Item;
import api_media_mate_library.model.Status;
import api_media_mate_library.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // Obtenir tous les items
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    // Obtenir un item par ID
    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    // Créer un nouvel item
    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    // Mettre à jour un item
    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }

    // Supprimer un item
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

    // Obtenir les items par catégorie
    @GetMapping("/category/{categoryId}")
    public List<Item> getItemsByCategory(@PathVariable Long categoryId) {
        return itemService.getItemsByCategory(categoryId);
    }

    // ✅ Modifier le statut d'un item
    @PatchMapping("/{id}/status")
    public ResponseEntity<Item> updateStatus(@PathVariable Long id, @RequestParam Status status) {
        Item updatedItem = itemService.updateStatus(id, status);
        return ResponseEntity.ok(updatedItem);
    }
}
