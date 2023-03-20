package backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "optimized_images")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OptimizedImage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "unique_name", unique = true)
    private String uniqueName;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "original_path")
    private String originalPath;

    @Column(name = "width")
    private int width;

    @Column(name = "size")
    private long fileSize;

    @ManyToOne()
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;
}
