package api_media_mate_library.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Activity {
    private String type;  // Type d'activité (movie, series, etc.)
    private String title; // Titre de l'élément
    private String action; // Action effectuée (e.g., "Completed", "Watched S3:E5", etc.)
    private String date;  // Date de l'activité
}
