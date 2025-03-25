package api_media_mate_library.repository;

import api_media_mate_library.model.Friendship;
import api_media_mate_library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByFollower(User follower);
    List<Friendship> findByFollowing(User following);
    Optional<Friendship> findByFollowerAndFollowing(User follower, User following);
}
