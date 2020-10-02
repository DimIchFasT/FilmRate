package ua.cursor.filmrate.movieservice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.movieservice.dto.MovieDTO;
import ua.cursor.filmrate.movieservice.dto.Views;
import ua.cursor.filmrate.movieservice.entity.Movie;
import ua.cursor.filmrate.movieservice.mapper.MovieMapper;
import ua.cursor.filmrate.movieservice.service.MovieService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @GetMapping
    @JsonView(Views.Movie.Full.class)
    public List<MovieDTO> getAll() {
        return movieService.getAll()
                .stream()
                .map(movieMapper::toFullMovieDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @JsonView(Views.Movie.Full.class)
    public MovieDTO getById(@PathVariable(value = "id") Long id) {
        Movie movie = movieService.getById(id);
        System.out.println(movie.toString());
        return movieMapper
                .toFullMovieDTO(
                        movie);
    }

    @PostMapping
    @JsonView(Views.Movie.Full.class)
    public MovieDTO save(@JsonView(Views.Movie.Default.class) @RequestBody MovieDTO movieDTO) {
        return movieMapper
                .toFullMovieDTO(
                        movieService
                                .save(movieMapper.toMovie(movieDTO)));
    }

    @PutMapping("/{id}")
    @JsonView(Views.Movie.Full.class)
    public MovieDTO update(@JsonView(Views.Movie.Default.class) @RequestBody MovieDTO movieDTO,
                           @PathVariable Long id) {
        return movieMapper
                .toFullMovieDTO(
                        movieService
                                .update(id, movieMapper.toMovie(movieDTO)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        movieService.delete(id);
    }
}
