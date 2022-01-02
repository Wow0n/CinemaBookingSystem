package pl.pjwstk.projekt.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pjwstk.projekt.backend.model.Programme;
import pl.pjwstk.projekt.backend.repositories.ProgrammeRepository;
import pl.pjwstk.projekt.backend.repositories.projections.ProgrammeProjection;

import java.util.List;

@Service
public class ProgrammeService {
    private final ProgrammeRepository repository;

    @Autowired
    public ProgrammeService(ProgrammeRepository repository) {
        this.repository = repository;
    }

    public List<ProgrammeProjection> programmeOfMovie(long id) {
        return repository.findByMovieId(id);
    }

    public long addNewProgramme(Programme programme) {
        return repository.save(programme).getId();
    }
}
