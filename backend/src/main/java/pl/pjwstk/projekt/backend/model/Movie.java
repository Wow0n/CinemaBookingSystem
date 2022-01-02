package pl.pjwstk.projekt.backend.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
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
}