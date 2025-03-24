package api_media_mate_library.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String username;
    private String email;
    private String password;
    private String avatar;

    // Date de l'adhésion (ajouté pour "joined")
    private String joined;

    // Bio (ajouté pour la description de l'utilisateur)
    private String bio;

    // Listes pour les statistiques (compte des éléments par type)
    private int movieCount;
    private int seriesCount;
    private int bookCount;
    private int mangaCount;
    private int favoriteCount;
    private int reviewCount;

    // Liste des activités (action effectuée sur un média)
    @ElementCollection
    private List<Activity> activity;

    // Liste des badges
    @ElementCollection
    private List<String> badges;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserHistory> history;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favorite> favorites;
}
