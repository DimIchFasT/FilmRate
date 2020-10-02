package ua.cursor.filmrate.coreservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ua.cursor.filmrate.coreservice.dto.CategoryDTO;
import ua.cursor.filmrate.coreservice.dto.api.CategoryApiDTO;

import java.util.List;

@Mapper(uses = {MovieMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CategoryMapper {

    CategoryDTO toCategoryDTO(CategoryApiDTO apiDTO);

    List<CategoryDTO> toCategoryDTOSet(List<CategoryApiDTO> apiDTOs);

    CategoryApiDTO toApiDTO(CategoryDTO categoryDTO);

    List<CategoryApiDTO> toCategoryApiDTOSet(List<CategoryDTO> categoryDTOs);

}
