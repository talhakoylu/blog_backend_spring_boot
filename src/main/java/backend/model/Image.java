package backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "original_image_path")
    private String originalImagePath;

    @Column(name = "image_unique_name")
    private String imageUniqueName;

    @Column(name = "image_original_name")
    private String imageOriginalName;

    @Column(name = "image_size")
    private long imageSize;

    @Column(name = "alt_text")
    private String altText;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "content_type")
    private String contentType;

    @OneToMany(mappedBy = "coverImage")
    private List<Post> posts;

    @OneToMany(mappedBy = "image")
    private List<Category> categories;

}
