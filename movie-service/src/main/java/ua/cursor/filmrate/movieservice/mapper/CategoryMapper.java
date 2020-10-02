package ua.cursor.filmrate.movieservice.mapper;

import org.mapstruct.*;
import ua.cursor.filmrate.movieservice.dto.CategoryDTO;
import ua.cursor.filmrate.movieservice.entity.Category;
import ua.cursor.filmrate.movieservice.mapper.annotations.DefaultMapper;
import ua.cursor.filmrate.movieservice.mapper.annotations.FullMapper;

import java.util.Set;

@Mapper(uses = {MovieMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CategoryMapper {

    @DefaultMapper
    @Mapping(target = "movies", ignore = true)
    CategoryDTO toDefaultCategoryDTO(Category category);

    @Mapping(target = "movies", qualifiedBy = FullMapper.class)
    CategoryDTO toFullCategoryDTO(Category category);

    @DefaultMapper
    @IterableMapping(qualifiedBy = DefaultMapper.class)
    Set<CategoryDTO> toDefaultCategoriesDtoSet(Set<Category> categories);

    Category toCategory(CategoryDTO categoryDTO);

}
