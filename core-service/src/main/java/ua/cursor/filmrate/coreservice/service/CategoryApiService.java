package ua.cursor.filmrate.coreservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.cursor.filmrate.coreservice.client.CategoryApiClient;
import ua.cursor.filmrate.coreservice.dto.CategoryDTO;
import ua.cursor.filmrate.coreservice.mapper.CategoryMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryApiService {

    private final CategoryApiClient categoryApiClient;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> getAll() {
        return categoryMapper.toCategoryDTOSet(categoryApiClient.getAll());
    }

    public CategoryDTO getById(Long id) {
        return categoryMapper.toCategoryDTO(categoryApiClient.getById(id));
    }

    public CategoryDTO save(CategoryDTO categoryDTO) {
        return categoryMapper.toCategoryDTO(categoryApiClient.save(categoryMapper.toApiDTO(categoryDTO)));
    }

    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        return categoryMapper.toCategoryDTO(categoryApiClient.update(id, categoryMapper.toApiDTO(categoryDTO)));
    }

    public void delete(Long id) {
        categoryApiClient.delete(id);
    }
}
