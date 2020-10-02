package ua.cursor.filmrate.movieservice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.movieservice.dto.CategoryDTO;
import ua.cursor.filmrate.movieservice.dto.Views;
import ua.cursor.filmrate.movieservice.mapper.CategoryMapper;
import ua.cursor.filmrate.movieservice.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    @JsonView(Views.All.Full.class)
    public List<CategoryDTO> getAll() {
        return categoryService.getAll()
                .stream()
                .map(categoryMapper::toDefaultCategoryDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @JsonView(Views.Category.Full.class)
    public CategoryDTO getById(@PathVariable(value = "id") Long id) {
        return categoryMapper
                .toFullCategoryDTO(
                        categoryService
                                .getById(id));
    }

    @PostMapping
    @JsonView(Views.Category.Full.class)
    public CategoryDTO save(@JsonView(Views.Category.Default.class) @RequestBody CategoryDTO categoryDTO) {
        return categoryMapper
                .toFullCategoryDTO(
                        categoryService
                                .save(categoryMapper
                                        .toCategory(categoryDTO)));
    }

    @PutMapping("/{id}")
    @JsonView(Views.Category.Full.class)
    public CategoryDTO update(
            @JsonView(Views.Category.Default.class) @RequestBody CategoryDTO categoryDTO,
            @PathVariable(value = "id") Long id) {
        return categoryMapper
                .toFullCategoryDTO(
                        categoryService
                                .update(id, categoryMapper
                                        .toCategory(categoryDTO)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
