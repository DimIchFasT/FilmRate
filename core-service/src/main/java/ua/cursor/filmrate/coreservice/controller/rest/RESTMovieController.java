package ua.cursor.filmrate.coreservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.coreservice.dto.CategoryDTO;
import ua.cursor.filmrate.coreservice.dto.MovieDTO;
import ua.cursor.filmrate.coreservice.service.MovieApiService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class RESTMovieController {

    private final MovieApiService movieApiService;

    @GetMapping
    public List<MovieDTO> getAll() {
        return movieApiService.getAll();
    }

    @PostMapping
    public List<MovieDTO> getAllByCategoriesIn(@RequestBody List<CategoryDTO> categoryDTOs) {
        return movieApiService.getAllByCategoriesIn(categoryDTOs);
    }

    @GetMapping("/rating/{rateValue}")
    public List<MovieDTO> getAllByRatingGreaterThan(@PathVariable Double rateValue) {
        return null;
    }

    @GetMapping("/{id}")
    public MovieDTO getById(@PathVariable Long id) {
        return movieApiService.getById(id);
    }

}
