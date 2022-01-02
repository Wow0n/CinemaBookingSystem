package pl.pjwstk.projekt.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pjwstk.projekt.backend.model.Movie;
import pl.pjwstk.projekt.backend.model.Programme;
import pl.pjwstk.projekt.backend.repositories.ProgrammeRepository;
import pl.pjwstk.projekt.backend.repositories.ReservationRepository;
import pl.pjwstk.projekt.backend.repositories.projections.ReservationProjection;

import java.util.List;

@Service
public class ReservationService {
    ReservationRepository reservationRepo;
    ProgrammeRepository programmeRepo;

    @Autowired
    public ReservationService(ReservationRepository reservationRepo, ProgrammeRepository programmeRepo) {
        this.reservationRepo = reservationRepo;
        this.programmeRepo = programmeRepo;
    }

    public List<ReservationProjection> getReservations(Movie movie, Programme programme) {
        return reservationRepo.getReservationData(movie, programme);
    }
}
