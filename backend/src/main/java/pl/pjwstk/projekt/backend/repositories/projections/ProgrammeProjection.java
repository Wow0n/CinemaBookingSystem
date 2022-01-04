package pl.pjwstk.projekt.backend.repositories.projections;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ProgrammeProjection {
    Long getId();

    MovieInfo getMovie();

    LocalDate getDate();

    LocalTime getTime();
}


