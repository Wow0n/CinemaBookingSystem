package pl.pjwstk.projekt.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pjwstk.projekt.backend.model.Movie;
import pl.pjwstk.projekt.backend.repositories.MovieRepository;
import pl.pjwstk.projekt.backend.repositories.projections.MovieProjection;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository repository;

    @Autowired
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<MovieProjection> getAllMoviesFromRepo() {
        return repository.findAllMovies();
    }

    public Movie getMovie(long id) {
        return repository.findById(id);
    }

    public long updateMovie(Movie movie) {
        Movie repoMovie = repository.findById((long) movie.getId());

        repoMovie.setTitle(movie.getTitle());
        repoMovie.setCategory(movie.getCategory());
        repoMovie.setImageUrl(movie.getImageUrl());
        repoMovie.setLength(movie.getLength());
        repoMovie.setAge(movie.getAge());
        repoMovie.setReleaseDate(movie.getReleaseDate());
        repoMovie.setDescription(movie.getDescription());

        return repository.save(repoMovie).getId();
    }

    public long addMovie(Movie movie) {
        return repository.save(movie).getId();
    }

    public long deleteMovie(long id) {
        repository.deleteById(id);
        return id;
    }
}
