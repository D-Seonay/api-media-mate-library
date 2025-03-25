package api_media_mate_library.controller;

import api_media_mate_library.model.Friendship;
import api_media_mate_library.model.User;
import api_media_mate_library.service.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
public class FriendshipController {

    private final FriendshipService friendshipService;

    @PostMapping("/follow")
    public ResponseEntity<Friendship> follow(@RequestParam Long followerId, @RequestParam Long followingId) {
        Friendship friendship = friendshipService.sendFriendRequest(followerId, followingId);
        return ResponseEntity.ok(friendship);
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<Friendship> accept(@PathVariable Long id) {
        Friendship friendship = friendshipService.acceptFriendRequest(id);
        return ResponseEntity.ok(friendship);
    }

    @DeleteMapping("/reject/{id}")
    public ResponseEntity<Void> reject(@PathVariable Long id) {
        friendshipService.rejectFriendRequest(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<Void> unfollow(@RequestParam Long followerId, @RequestParam Long followingId) {
        friendshipService.unfollow(followerId, followingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Friendship>> getFriends(@PathVariable Long userId) {
        List<Friendship> friends = friendshipService.getFriends(userId);
        return ResponseEntity.ok(friends);
    }

    // Nombre de followers
@GetMapping("/{userId}/followers/count")
public ResponseEntity<Long> getFollowerCount(@PathVariable Long userId) {
    long count = friendshipService.countFollowers(userId);
    return ResponseEntity.ok(count);
}

// Nombre de following
@GetMapping("/{userId}/following/count")
public ResponseEntity<Long> getFollowingCount(@PathVariable Long userId) {
    long count = friendshipService.countFollowing(userId);
    return ResponseEntity.ok(count);
}

// Liste des followers
@GetMapping("/{userId}/followers")
public ResponseEntity<List<String>> getFollowers(@PathVariable Long userId) {
    List<User> followers = friendshipService.getFollowers(userId);
    List<String> followerNames = followers.stream()
            .map(User::getUsername)
            .toList();
    return ResponseEntity.ok(followerNames);
}

// Liste des following
@GetMapping("/{userId}/following")
public ResponseEntity<List<String>> getFollowing(@PathVariable Long userId) {
    List<User> following = friendshipService.getFollowing(userId);
    List<String> followingNames = following.stream()
            .map(User::getUsername)
            .toList();
    return ResponseEntity.ok(followingNames);
}

}
