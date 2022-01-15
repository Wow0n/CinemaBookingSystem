package pl.pjwstk.projekt.backend.services;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pjwstk.projekt.database.data.ReservationInfo;
import pl.pjwstk.projekt.database.data.Reserve;
import pl.pjwstk.projekt.database.data.SeatReservation;
import pl.pjwstk.projekt.database.model.Movie;
import pl.pjwstk.projekt.database.model.Programme;
import pl.pjwstk.projekt.database.model.Reservation;
import pl.pjwstk.projekt.database.projections.ReservationProjection;
import pl.pjwstk.projekt.database.repositories.MovieRepository;
import pl.pjwstk.projekt.database.repositories.ProgrammeRepository;
import pl.pjwstk.projekt.database.repositories.ReservationRepository;
import pl.pjwstk.projekt.database.repositories.TicketRepository;

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

    public ReservationProjection getReservation(Movie movie, Programme programme) {
        return reservationRepo.getReservationData(movie, programme);
    }

    public Reserve getReservedSeatsForProgramme(Programme programme) {
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

        logger.log(Level.INFO, "New reservation has been added for movie: " + movieRepo.getById(info.getMovieId()).getTitle());

        return reservationRepo.save(newReservation).getId();
    }
}
