package api_media_mate_library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFriendDTO {
    private String id;
    private String name;
    private String username;
    private String avatarUrl;
    private boolean isFollowing;
}
