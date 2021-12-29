package pl.pjwstk.projekt.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import pl.pjwstk.projekt.frontend.model.Programme;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProgrammeController {
    WebClient webClient = WebClient.create("http://localhost:8081");

    @GetMapping("movies/programme/{id}")
    public String displayProgramme(Model model, @PathVariable long id) {
        Programme[] programme = webClient.get()
                .uri("/programme/" + id)
                .retrieve()
                .bodyToMono(Programme[].class)
                .block();

        List<Programme> programmeList = Arrays.asList(programme);
        model.addAttribute("programmeList", programmeList);

        return "movieProgramme";
    }
}
