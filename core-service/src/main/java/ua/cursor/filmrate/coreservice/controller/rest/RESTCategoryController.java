package ua.cursor.filmrate.coreservice.controller.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.cursor.filmrate.coreservice.dto.CategoryDTO;
import ua.cursor.filmrate.coreservice.service.CategoryApiService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class RESTCategoryController {

    private final CategoryApiService categoryApiService;

    @GetMapping
    public List<CategoryDTO> getAll() {
        return categoryApiService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryDTO getById(@PathVariable Long id) {
        return categoryApiService.getById(id);
    }
}
