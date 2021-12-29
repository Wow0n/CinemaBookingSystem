package pl.pjwstk.projekt.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;
import pl.pjwstk.projekt.frontend.model.Movie;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Controller
public class MovieController {
    WebClient webClient = WebClient.create("http://localhost:8081");

    @GetMapping("/movies")
    public String displayMovies(Model model) {
        Movie[] movies = webClient.get()
                .uri("/movies")
                .retrieve()
                .bodyToMono(Movie[].class)
                .block();

        List<Movie> movieList = Arrays.asList(movies);
        model.addAttribute("movies", movieList);

        return "index";
    }

    @GetMapping("/movies/edit/{id}")
    public String displayEditMovieForm(Model model, @PathVariable long id) {
        Movie movie = webClient.get()
                .uri("/movies/" + id)
                .retrieve()
                .bodyToMono(Movie.class)
                .block();

        model.addAttribute("movie", movie);

        return "editForm";
    }

    @PostMapping("/movie/edit")
    public String submitEditMovie(@ModelAttribute Movie movie) {
        Movie movieEdit = webClient.put()
                .uri("/movies/edit")
                .body(Mono.just(movie), Movie.class)
                .retrieve()
                .bodyToMono(Movie.class)
                .block();

        return "redirect:/movies";
    }
}