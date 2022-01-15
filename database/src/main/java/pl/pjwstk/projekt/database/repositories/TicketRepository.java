package pl.pjwstk.projekt.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjwstk.projekt.database.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
