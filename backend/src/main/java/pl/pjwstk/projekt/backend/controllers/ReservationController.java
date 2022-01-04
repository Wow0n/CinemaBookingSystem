package pl.pjwstk.projekt.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjwstk.projekt.backend.model.Movie;
import pl.pjwstk.projekt.backend.model.Programme;
import pl.pjwstk.projekt.backend.repositories.projections.ReservationProjection;
import pl.pjwstk.projekt.backend.services.ReservationService;
import pl.pjwstk.projekt.backend.services.data.ReservationInfo;
import pl.pjwstk.projekt.backend.services.data.Reserve;

@RestController
public class ReservationController {
    ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping("reservation/{movie}/{programme}")
    public ResponseEntity<ReservationProjection> getAllReservationsOfProgramme(@PathVariable Movie movie, @PathVariable Programme programme) {
        ReservationProjection reservations = service.getReservations(movie, programme);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("reservation/seats/{programme}")
    public ResponseEntity<Reserve> getReservedSeats(@PathVariable Programme programme) {
        Reserve reserve = service.reserve(programme);
        return new ResponseEntity<>(reserve, HttpStatus.OK);
    }

    @PostMapping("/reservation/save/{newTicketId}")
    public ResponseEntity<Long> addNewReservation(@RequestBody ReservationInfo reservationInfo, @PathVariable long newTicketId) {
        long createdId = service.addReservation(reservationInfo, newTicketId);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }
}
