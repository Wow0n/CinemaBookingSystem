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

    public List<Movie> getAllMoviesFromRepo() {
        return repository.findAll();
    }

    public Movie getMovie(long id) {
        return repository.findById(id);
    }

    public long updateMovie(Movie movie) {
        Movie repoMovie = repository.findById(movie.getId());

        repoMovie.setTitle(movie.getTitle());
        repoMovie.setCategory(movie.getCategory());
        repoMovie.setImageUrl(movie.getImageUrl());
        repoMovie.setLength(movie.getLength());
        repoMovie.setAge(movie.getAge());
        repoMovie.setReleaseDate(movie.getReleaseDate());
        repoMovie.setDescription(movie.getDescription());

        return repository.save(repoMovie).getId();
    }
}
