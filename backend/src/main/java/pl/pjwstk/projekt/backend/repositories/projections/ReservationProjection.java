package pl.pjwstk.projekt.backend.repositories.projections;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ReservationProjection {
    Long getProgrammeId();

    String getTitle();

    LocalDate getDate();

    LocalTime getTime();
}
