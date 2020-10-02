package ua.cursor.filmrate.rateservice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.rateservice.dto.ReviewDTO;
import ua.cursor.filmrate.rateservice.dto.Views;
import ua.cursor.filmrate.rateservice.entity.Review;
import ua.cursor.filmrate.rateservice.mapper.ReviewMapper;
import ua.cursor.filmrate.rateservice.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;

    @GetMapping
    @JsonView(Views.Review.Full.class)
    public List<ReviewDTO> getAll() {
        return reviewMapper.toReviewDTOs(reviewService.getAll());
    }

    @GetMapping("/{id}")
    @JsonView(Views.Review.Full.class)
    public ReviewDTO getById(@PathVariable Long id) {
        return reviewMapper.toReviewDTO(reviewService.getById(id));
    }

    @GetMapping("/movies/{id}")
    @JsonView(Views.Review.Full.class)
    public List<ReviewDTO> getByMovieId(@PathVariable Long id) {
        List<Review> reviews = reviewService.getByMovieId(id);
        reviews.forEach(System.out::println);
        return reviewMapper.toReviewDTOs(reviews);
    }

    @PostMapping
    @JsonView(Views.Review.Full.class)
    public ReviewDTO save(@JsonView(Views.Review.Create.class) @RequestBody ReviewDTO reviewDTO) {
        return reviewMapper.toReviewDTO(reviewService.save(reviewMapper.toReview(reviewDTO)));
    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
        reviewService.delete(id);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteByMovieId(Long id) {
        reviewService.deleteByMovieId(id);
    }
}
