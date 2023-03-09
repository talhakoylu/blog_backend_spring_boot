package backend.model;

import backend.common.PostStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "slug")
    private String slug;

    @Column(name = "postStatus")
    @Enumerated(EnumType.STRING)
    private PostStatusEnum postStatus;
    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne()
    @JoinColumn(name = "coverImage", referencedColumnName = "id")
    private Image coverImage;

    @Column(name = "seoTitle")
    private String seoTitle;

    @Column(name = "seoDescription")
    private String seoDescription;

    @Column(name = "seoTags")
    private String seoTags;
}

