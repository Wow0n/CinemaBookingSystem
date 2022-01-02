package pl.pjwstk.projekt.frontend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "movie", orphanRemoval = true)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "movie", orphanRemoval = true)
    private List<Programme> repertoires;

    public Movie(Long id) {
        this.id = id;
    }
}