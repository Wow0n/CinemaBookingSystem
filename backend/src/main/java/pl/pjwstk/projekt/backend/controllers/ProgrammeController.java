package pl.pjwstk.projekt.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.pjwstk.projekt.backend.model.Programme;
import pl.pjwstk.projekt.backend.services.ProgrammeService;

import java.util.List;

@RestController
public class ProgrammeController {
    private ProgrammeService service;

    @Autowired
    public ProgrammeController(ProgrammeService service) {
        this.service = service;
    }

    @GetMapping("/programme/{id}")
    public ResponseEntity<List<Programme>> getProgrammeOfMovie(@PathVariable long id) {
        List<Programme> programme = service.programmeOfMovie(id);
        return new ResponseEntity<>(programme, HttpStatus.OK);
    }
}
