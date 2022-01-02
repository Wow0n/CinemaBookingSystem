package pl.pjwstk.projekt.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.pjwstk.projekt.backend.model.Movie;
import pl.pjwstk.projekt.backend.model.Programme;
import pl.pjwstk.projekt.backend.repositories.projections.ReservationProjection;
import pl.pjwstk.projekt.backend.services.ReservationService;

import java.util.List;
import java.util.Map;

@RestController
public class ReservationController {
    ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping("reservation/{movie}/{programme}")
    public ResponseEntity<List<ReservationProjection>> getAllReservationsOfProgramme(@PathVariable Movie movie, @PathVariable Programme programme) {
        List<ReservationProjection> reservations = service.getReservations(movie, programme);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}
