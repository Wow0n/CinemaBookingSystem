package pl.pjwstk.projekt.backend.repositories.projections;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ProgrammeProjection {
    Long getId();

    LocalDate getDate();

    LocalTime getTime();
}


