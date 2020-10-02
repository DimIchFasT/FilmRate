package ua.cursor.filmrate.coreservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.cursor.filmrate.coreservice.client.ReviewApiClient;
import ua.cursor.filmrate.coreservice.dto.api.ReviewApiDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewApiService {

    private final ReviewApiClient reviewApiClient;

    public List<ReviewApiDTO> getAll() {
        return reviewApiClient.getAll();
    }

    public ReviewApiDTO getById(Long id) {
        return reviewApiClient.getById(id);
    }

    public List<ReviewApiDTO> getByMovieId(Long id) {
        return reviewApiClient.getByMovieId(id);
    }

    public ReviewApiDTO save(ReviewApiDTO reviewApiDTO) {
        return reviewApiClient.save(reviewApiDTO);
    }

    public ReviewApiDTO update(Long id, ReviewApiDTO reviewApiDTO) {
        return reviewApiClient.update(id, reviewApiDTO);
    }

    public void delete(Long id) {
        reviewApiClient.delete(id);
    }

    public void deleteByMovieId(Long movieId) {
        reviewApiClient.deleteByMovieId(movieId);
    }
}
