package pl.pjwstk.projekt.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pjwstk.projekt.backend.model.Movie;
import pl.pjwstk.projekt.backend.model.Programme;
import pl.pjwstk.projekt.backend.model.Reservation;
import pl.pjwstk.projekt.backend.repositories.projections.ReservationProjection;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "select r.programme.id as programmeId, r.movie.title as title, r.programme.date as date, " +
            "r.programme.time as time from Reservation r where r.movie = ?1 and r.programme = ?2")
    List<ReservationProjection> getReservationData(Movie movie, Programme programme);
}
