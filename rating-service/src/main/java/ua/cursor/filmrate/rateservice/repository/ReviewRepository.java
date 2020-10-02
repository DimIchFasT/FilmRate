package ua.cursor.filmrate.rateservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.cursor.filmrate.rateservice.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovieId(Long movieId);

    void deleteByMovieId(Long movieId);
}
