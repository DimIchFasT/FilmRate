package ua.cursor.filmrate.coreservice.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ua.cursor.filmrate.coreservice.dto.ReviewDTO;
import ua.cursor.filmrate.coreservice.dto.api.ReviewApiDTO;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ReviewMapper {

    ReviewDTO toReviewDTO(ReviewApiDTO apiDTO);

    List<ReviewDTO> toReviewDTOSet(List<ReviewApiDTO> apiDTOs);

    ReviewApiDTO toReviewApiDTO(ReviewDTO reviewDTO);

    List<ReviewApiDTO> toReviewApiDTOSet(List<ReviewDTO> reviewDTOs);
}
