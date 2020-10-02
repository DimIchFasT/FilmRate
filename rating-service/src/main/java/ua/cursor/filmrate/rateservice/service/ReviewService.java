package ua.cursor.filmrate.rateservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.cursor.filmrate.rateservice.entity.Review;
import ua.cursor.filmrate.rateservice.repository.ReviewRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    public Review getById(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        log.info("IN getById - review: {} found by id: {}", review, id);
        return review;
    }

    public List<Review> getByMovieId(Long id) {
        List<Review> reviews = reviewRepository.findByMovieId(id);
        log.info("IN getById - reviews: {} found by movieId: {}", reviews, id);
        return reviews;
    }

    public Review save(Review review) {
        Review savedReview = reviewRepository.save(review);
        log.info("IN save - review: {} successfully created", savedReview);
        return savedReview;
    }

    public boolean delete(Long id) {
        reviewRepository.deleteById(id);
        if (reviewRepository.existsById(id)) {
            log.warn("IN delete - review with id: {} not deleted", id);
            return false;
        } else {
            log.info("IN delete - review with id: {} successfully deleted", id);
            return true;
        }
    }

    public boolean deleteByMovieId(Long id) {
        reviewRepository.deleteByMovieId(id);
        if (reviewRepository.existsById(id)) {
            log.warn("IN deleteByMovieId - review with movieId: {} not deleted", id);
            return false;
        } else {
            log.info("IN deleteByMovieId - review with movieId: {} successfully deleted", id);
            return true;
        }
    }
}
