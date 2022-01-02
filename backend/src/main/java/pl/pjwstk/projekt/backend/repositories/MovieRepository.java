package pl.pjwstk.projekt.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.pjwstk.projekt.backend.model.Movie;
import pl.pjwstk.projekt.backend.repositories.projections.MovieProjection;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "select m from Movie m")
    List<MovieProjection> findAllMovies();

    Movie findById(long id);
}
