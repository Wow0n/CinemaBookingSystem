package pl.pjwstk.projekt.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import pl.pjwstk.projekt.frontend.model.Reservation;

import java.util.Arrays;
import java.util.List;

@Controller
public class ReservationController {
    WebClient webClient = WebClient.create("http://localhost:8081");

    @GetMapping("movies/programme/reserve/{m_id}/{p_id}")
    public String displayReservationForm(Model model, @PathVariable long m_id, @PathVariable long p_id) {
        Reservation[] reservations = webClient.get()
                .uri("reservation/" + m_id + "/" + p_id)
                .retrieve()
                .bodyToMono(Reservation[].class)
                .block();
        List<Reservation> reservationList = Arrays.asList(reservations);

//        reserve(model, p_id);

//        model.addAttribute("rows", rows);
        model.addAttribute("reservation", reservationList);

        return "reserveSeat";
    }
}
