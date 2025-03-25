package api_media_mate_library.service;


import org.springframework.stereotype.Service;

import api_media_mate_library.model.User;
import api_media_mate_library.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ➡️ GET ALL USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ➡️ GET USER BY ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // ➡️ CREATE USER
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // ➡️ UPDATE USER
    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        existingUser.setName(updatedUser.getName());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setBio(updatedUser.getBio());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setAvatar(updatedUser.getAvatar());
        return userRepository.save(existingUser);
    }

    // ➡️ DELETE USER
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

