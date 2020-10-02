package ua.cursor.filmrate.coreservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.coreservice.dto.CategoryDTO;
import ua.cursor.filmrate.coreservice.service.CategoryApiService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/categories")
public class RESTAdminCategoryController {

    private final CategoryApiService categoryApiService;

    @PostMapping
    public CategoryDTO save(@RequestBody CategoryDTO categoryDTO) {
        return categoryApiService.save(categoryDTO);
    }

    @PutMapping("/{id}")
    public CategoryDTO update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return categoryApiService.update(id, categoryDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryApiService.delete(id);
    }

}
