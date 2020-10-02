package ua.cursor.filmrate.coreservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.coreservice.dto.api.RateApiDTO;

import java.util.List;

@FeignClient(contextId = "rate-api", value = "rate-service", path = "/ratings")
public interface RatingApiClient {

    @GetMapping("/")
    List<RateApiDTO> getAll();

    @GetMapping("/{id}")
    RateApiDTO getById(@PathVariable Long id);

    @GetMapping("/movies/{id}")
    RateApiDTO getByMovieId(@PathVariable Long id);

    @PostMapping("/")
    RateApiDTO save(@RequestBody RateApiDTO rateApiDTO);

    @PutMapping("/{id}")
    RateApiDTO update(@PathVariable Long id, @RequestBody RateApiDTO rateApiDTO);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);

    @DeleteMapping("/movie/{id}")
    void deleteByMovieId(@PathVariable Long id);
}
