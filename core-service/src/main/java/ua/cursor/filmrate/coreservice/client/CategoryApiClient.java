package ua.cursor.filmrate.coreservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.coreservice.dto.api.CategoryApiDTO;

import java.util.List;

@FeignClient(contextId = "category-api", value = "movie-service", path = "/categories")
public interface CategoryApiClient {

    @GetMapping
    List<CategoryApiDTO> getAll();

    @GetMapping("/{id}")
    CategoryApiDTO getById(@PathVariable Long id);

    @PostMapping("/")
    CategoryApiDTO save(@RequestBody CategoryApiDTO categoryApiDTO);

    @PutMapping("/{id}")
    CategoryApiDTO update(@PathVariable Long id, @RequestBody CategoryApiDTO categoryApiDTO);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);

}
