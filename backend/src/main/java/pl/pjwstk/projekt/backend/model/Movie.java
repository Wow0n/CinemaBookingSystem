package pl.pjwstk.projekt.backend.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Lob
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "length")
    private Integer length;

    @Column(name = "age")
    private Integer age;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Lob
    @Column(name = "description")
    private String description;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "movie", orphanRemoval = true)
    private List<Reservation> reservations;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "movie", orphanRemoval = true)
    private List<Programme> repertoires;

    public Movie(Long id, String title, String category, String imageUrl, Integer length, Integer age, LocalDate releaseDate, String description) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.imageUrl = imageUrl;
        this.length = length;
        this.age = age;
        this.releaseDate = releaseDate;
        this.description = description;
    }

    public Movie(Long id) {
        this.id = id;
    }
}