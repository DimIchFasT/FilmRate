package ua.cursor.filmrate.coreservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.coreservice.dto.api.ReviewApiDTO;

import java.util.List;

@FeignClient(contextId = "review-api", value = "rate-service", path = "/reviews")
public interface ReviewApiClient {
    @GetMapping("/")
    List<ReviewApiDTO> getAll();

    @GetMapping("/{id}")
    ReviewApiDTO getById(@PathVariable Long id);

    @GetMapping("/movies/{id}")
    List<ReviewApiDTO> getByMovieId(@PathVariable Long id);

    @PostMapping("/")
    ReviewApiDTO save(@RequestBody ReviewApiDTO reviewApiDTO);

    @PutMapping("/{id}")
    ReviewApiDTO update(@PathVariable Long id, @RequestBody ReviewApiDTO reviewApiDTO);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);

    @DeleteMapping("/movies/{id}")
    void deleteByMovieId(@PathVariable Long id);
}
