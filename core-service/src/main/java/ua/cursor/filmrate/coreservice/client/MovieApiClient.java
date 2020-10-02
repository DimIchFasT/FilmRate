package ua.cursor.filmrate.coreservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.coreservice.dto.api.CategoryApiDTO;
import ua.cursor.filmrate.coreservice.dto.api.MovieApiDTO;

import java.util.List;

@FeignClient(contextId = "movie-api", value = "movie-service", path = "/movies")
public interface MovieApiClient {

    @GetMapping
    List<MovieApiDTO> getAll();

    @PostMapping("/categories")
    List<MovieApiDTO> getAllByCategoriesIn(List<CategoryApiDTO> categories);

    @GetMapping("/{id}")
    MovieApiDTO getById(@PathVariable Long id);

    @PostMapping("/")
    MovieApiDTO save(@RequestBody MovieApiDTO movieDTO);

    @PutMapping("/{id}")
    MovieApiDTO update(@PathVariable Long id, @RequestBody MovieApiDTO movieDTO);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}
