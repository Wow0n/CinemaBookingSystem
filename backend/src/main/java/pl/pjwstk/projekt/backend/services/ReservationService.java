package pl.pjwstk.projekt.backend.services;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pjwstk.projekt.backend.model.Movie;
import pl.pjwstk.projekt.backend.model.Programme;
import pl.pjwstk.projekt.backend.model.Reservation;
import pl.pjwstk.projekt.backend.repositories.MovieRepository;
import pl.pjwstk.projekt.backend.repositories.ProgrammeRepository;
import pl.pjwstk.projekt.backend.repositories.ReservationRepository;
import pl.pjwstk.projekt.backend.repositories.TicketRepository;
import pl.pjwstk.projekt.backend.repositories.projections.ReservationProjection;
import pl.pjwstk.projekt.backend.services.data.ReservationInfo;
import pl.pjwstk.projekt.backend.services.data.Reserve;
import pl.pjwstk.projekt.backend.services.data.SeatReservation;

import java.util.*;

@Service
public class ReservationService {
    private final Logger logger = Logger.getLogger(ReservationService.class);
    ReservationRepository reservationRepo;
    ProgrammeRepository programmeRepo;
    TicketRepository ticketRepo;
    MovieRepository movieRepo;

    @Autowired
    public ReservationService(ReservationRepository reservationRepo, ProgrammeRepository programmeRepo, TicketRepository ticketRepo, MovieRepository movieRepo) {
        this.reservationRepo = reservationRepo;
        this.programmeRepo = programmeRepo;
        this.ticketRepo = ticketRepo;
        this.movieRepo = movieRepo;
    }

    public ReservationProjection getReservations(Movie movie, Programme programme) {
        return reservationRepo.getReservationData(movie, programme);
    }

    public Reserve reserve(Programme programme) {
        Reserve reserve = new Reserve();
        SeatReservation seatReservation = new SeatReservation();

        Map<String, Boolean> mapMovie = new HashMap<>();

        getReservedSeats(programme.getId()).forEach(seat -> mapMovie.put(seat, true));
        reserve.setMap(mapMovie);
        reserve.setSeatReservation(seatReservation);

        return reserve;
    }

    private Set<String> getReservedSeats(long id) {
        Programme programme = programmeRepo.getById(id);
        Set<String> reservedSeats = new HashSet<>();

        for (Reservation reservation : programme.getReservations()) {
            reservedSeats.addAll(Arrays.asList(reservation.getTicket().getSeat().split(",")));
        }
        return reservedSeats;
    }

    public long addReservation(ReservationInfo info, long newTicketId) {
        Reservation newReservation = new Reservation();

        newReservation.setMovie(movieRepo.getById(info.getMovieId()));
        newReservation.setTicket(ticketRepo.getById(newTicketId));
        newReservation.setProgramme(programmeRepo.getById(info.getId()));
        newReservation.setName(info.getName());
        newReservation.setSurname(info.getSurname());
        newReservation.setEmail(info.getEmail());
        newReservation.setPhone(info.getPhone());

        logger.log(Level.INFO, "New reservation has been added for movieId: " + info.getMovieId());

        return reservationRepo.save(newReservation).getId();
    }
}
