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

    @Column(name = "imagePath")
    private String imagePath;

    @Column(name = "originalImagePath")
    private String originalImagePath;

    @Column(name = "imageUniqueName")
    private String imageUniqueName;

    @Column(name = "imageOriginalName")
    private String imageOriginalName;

    @Column(name = "imageSize")
    private long imageSize;

    @Column(name = "altText")
    private String altText;

    @Column(name = "isActive")
    private Boolean isActive;

    @OneToMany(mappedBy = "coverImage")
    private List<Post> posts;

    @OneToMany(mappedBy = "image")
    private List<Category> categories;

}
