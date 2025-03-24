package api_media_mate_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api_media_mate_library.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
