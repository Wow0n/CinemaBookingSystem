package pl.pjwstk.projekt.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjwstk.projekt.backend.model.Programme;

import java.util.List;

public interface ProgrammeRepository extends JpaRepository<Programme, Long> {
    List<Programme> findByMovieId(long id);
}
