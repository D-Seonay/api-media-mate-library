package api_media_mate_library.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import api_media_mate_library.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long>{

    Category findByName(String name);
}
