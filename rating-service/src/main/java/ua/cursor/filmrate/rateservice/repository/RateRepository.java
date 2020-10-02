package ua.cursor.filmrate.rateservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.cursor.filmrate.rateservice.entity.Rate;

import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Long> {
    Optional<Rate> findByMovieId(Long movieId);

    Optional<Rate> getById(Long movieId);

    void deleteByMovieId(Long movieId);
}
