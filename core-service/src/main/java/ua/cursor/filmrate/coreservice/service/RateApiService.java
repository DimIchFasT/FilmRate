package ua.cursor.filmrate.coreservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.cursor.filmrate.coreservice.client.RatingApiClient;
import ua.cursor.filmrate.coreservice.dto.api.RateApiDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateApiService {

    private final RatingApiClient ratingApiClient;

    public List<RateApiDTO> getAll() {
        return ratingApiClient.getAll();
    }

    public RateApiDTO getById(Long id) {
        return ratingApiClient.getById(id);
    }

    public RateApiDTO getByMovieId(Long id) {
        return ratingApiClient.getByMovieId(id);
    }

    public RateApiDTO save(RateApiDTO rateApiDTO) {
        return ratingApiClient.save(rateApiDTO);
    }

    public RateApiDTO update(Long id, RateApiDTO rateApiDTO) {
        return ratingApiClient.update(id, rateApiDTO);
    }

    public void delete(Long id) {
        ratingApiClient.delete(id);
    }

    public void deleteByMovieId(Long movieId) {
        ratingApiClient.delete(movieId);
    }
}
