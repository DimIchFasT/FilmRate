package ua.cursor.filmrate.coreservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.coreservice.dto.ReviewDTO;
import ua.cursor.filmrate.coreservice.dto.api.ReviewApiDTO;
import ua.cursor.filmrate.coreservice.mapper.ReviewMapper;
import ua.cursor.filmrate.coreservice.service.ReviewApiService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class RESTReviewController {
    private final ReviewApiService reviewApiService;
    private final ReviewMapper reviewMapper;

    @PostMapping("/{movieId}")
    public ReviewDTO addReview(@PathVariable Long movieId, @RequestBody ReviewDTO reviewDTO) {
        reviewDTO.setMovieId(movieId);
        System.out.println(reviewDTO.toString());
        ReviewApiDTO reviewApiDTO = reviewMapper.toReviewApiDTO(reviewDTO);
        System.out.println(reviewApiDTO.toString());
        ReviewApiDTO reviewApi = reviewApiService.update(movieId, reviewApiDTO);
        System.out.println(reviewApi.toString());
        ReviewDTO review = reviewMapper.toReviewDTO(reviewApi);
        System.out.println(review.toString());
        return review;
    }
}
