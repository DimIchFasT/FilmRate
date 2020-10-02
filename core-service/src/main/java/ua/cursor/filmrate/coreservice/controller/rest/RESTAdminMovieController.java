package ua.cursor.filmrate.coreservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.coreservice.dto.MovieDTO;
import ua.cursor.filmrate.coreservice.service.MovieApiService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/movies")
public class RESTAdminMovieController {

    private final MovieApiService movieApiService;

    @PostMapping
    public MovieDTO save(@RequestBody MovieDTO movieDTO) {
        return movieApiService.save(movieDTO);
    }

    @PutMapping("/{id}")
    public MovieDTO update(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        return movieApiService.update(id, movieDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        movieApiService.delete(id);
    }

}
