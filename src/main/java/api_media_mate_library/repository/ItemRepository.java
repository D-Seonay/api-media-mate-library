package api_media_mate_library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import api_media_mate_library.model.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {    

    List<Item> findByCategoryId(Long categoryId);

}
