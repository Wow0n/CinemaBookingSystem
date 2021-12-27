package pl.pjwstk.projekt.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pjwstk.projekt.backend.model.Movie;
import pl.pjwstk.projekt.backend.services.MovieService;

import java.util.List;

@RestController
public class MovieController {
    private MovieService service;

    @Autowired
    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovies() {
        List<Movie> movies = service.getMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}
