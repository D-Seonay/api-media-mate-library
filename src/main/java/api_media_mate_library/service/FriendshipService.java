package api_media_mate_library.service;

import api_media_mate_library.model.Friendship;
import api_media_mate_library.model.FriendshipStatus;
import api_media_mate_library.model.User;
import api_media_mate_library.repository.FriendshipRepository;
import api_media_mate_library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    // Envoyer une demande d'ami (follow)
    public Friendship sendFriendRequest(Long followerId, Long followingId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new IllegalArgumentException("Follower not found"));
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new IllegalArgumentException("Following not found"));

        // Vérifier si la demande existe déjà
        Optional<Friendship> existingFriendship = friendshipRepository
                .findByFollowerAndFollowing(follower, following);

        if (existingFriendship.isPresent()) {
            throw new IllegalStateException("Friend request already sent");
        }

        Friendship friendship = new Friendship(null, follower, following, FriendshipStatus.PENDING);
        return friendshipRepository.save(friendship);
    }

    // Accepter une demande d'ami
    public Friendship acceptFriendRequest(Long id) {
        Friendship friendship = friendshipRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Friend request not found"));

        friendship.setStatus(FriendshipStatus.ACCEPTED);
        return friendshipRepository.save(friendship);
    }

    // Rejeter une demande d'ami
    public void rejectFriendRequest(Long id) {
        friendshipRepository.deleteById(id);
    }

    // Unfollow (supprimer une relation)
    public void unfollow(Long followerId, Long followingId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new IllegalArgumentException("Follower not found"));
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new IllegalArgumentException("Following not found"));

        Friendship friendship = friendshipRepository.findByFollowerAndFollowing(follower, following)
                .orElseThrow(() -> new IllegalArgumentException("Friendship not found"));

        friendshipRepository.delete(friendship);
    }

    // Lister les amis (relations acceptées)
    public List<Friendship> getFriends(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return friendshipRepository.findByFollower(user);
    }

    // Compter le nombre de followers
public long countFollowers(Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    return friendshipRepository.findByFollowing(user).size();
}

// Compter le nombre de following
public long countFollowing(Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    return friendshipRepository.findByFollower(user).size();
}

// Récupérer la liste des followers
public List<User> getFollowers(Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    return friendshipRepository.findByFollowing(user).stream()
            .filter(f -> f.getStatus() == FriendshipStatus.ACCEPTED) // Filtrer les amis confirmés
            .map(Friendship::getFollower)
            .toList();
}

// Récupérer la liste des following
public List<User> getFollowing(Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    return friendshipRepository.findByFollower(user).stream()
            .filter(f -> f.getStatus() == FriendshipStatus.ACCEPTED)
            .map(Friendship::getFollowing)
            .toList();
}



}
