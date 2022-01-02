package pl.pjwstk.projekt.backend.repositories.projections;

import java.time.LocalDate;

public interface MovieProjection {
    Long getId();

    String getTitle();

    String getCategory();

    String getImageUrl();

    int getLength();

    int getAge();

    LocalDate getReleaseDate();

    String getDescription();
}
