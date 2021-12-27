package pl.pjwstk.projekt.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pjwstk.projekt.backend.model.Movie;
import pl.pjwstk.projekt.backend.repositories.MovieRepository;

import java.util.List;

@Service
public class MovieService {
    private MovieRepository repository;

    @Autowired
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> getMovies() {
        return repository.findAll();
    }
}
