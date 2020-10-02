package ua.cursor.filmrate.rateservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ua.cursor.filmrate.rateservice.dto.ReviewDTO;
import ua.cursor.filmrate.rateservice.entity.Review;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ReviewMapper {
    ReviewDTO toReviewDTO(Review review);

    List<ReviewDTO> toReviewDTOs(List<Review> reviews);

    Review toReview(ReviewDTO reviewDTO);
}
