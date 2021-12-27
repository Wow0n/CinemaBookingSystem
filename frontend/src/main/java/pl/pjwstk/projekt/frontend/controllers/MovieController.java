package pl.pjwstk.projekt.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import pl.pjwstk.projekt.frontend.model.Movie;

import java.util.Arrays;
import java.util.List;

@Controller
public class MovieController {
    WebClient webClient = WebClient.create("http://localhost:8081");

    @GetMapping
    public String displayMovies(Model model) {
        Movie[] movies = webClient
                .get()
                .uri("/movies")
                .retrieve()
                .bodyToMono(Movie[].class)
                .block();

        List<Movie> movieList = Arrays.asList(movies);
        model.addAttribute("movies", movieList);

        return "index";
    }
}
