package pl.pjwstk.projekt.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pjwstk.projekt.backend.model.Ticket;
import pl.pjwstk.projekt.backend.repositories.TicketRepository;
import pl.pjwstk.projekt.backend.services.data.Reserve;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.TRUE;

@Service
public class TicketService {
    TicketRepository repository;

    @Autowired
    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public long addTicket(Reserve reserve) {
        List<String> seats = getSeats(reserve);
        Ticket ticket = new Ticket();

        ticket.setSeat(String.join(",", seats));

        return repository.save(ticket).getId();
    }

    private List<String> getSeats(Reserve reserve) {
        List<String> seats = new ArrayList<>();

        for (Map.Entry<String, Boolean> entry : reserve.getMap().entrySet()) {
            if (TRUE.equals(entry.getValue()))
                seats.add(entry.getKey());
        }

        return seats;
    }
}
