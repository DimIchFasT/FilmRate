package ua.cursor.filmrate.coreservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.cursor.filmrate.coreservice.client.MovieApiClient;
import ua.cursor.filmrate.coreservice.dto.CategoryDTO;
import ua.cursor.filmrate.coreservice.dto.MovieDTO;
import ua.cursor.filmrate.coreservice.dto.api.RateApiDTO;
import ua.cursor.filmrate.coreservice.mapper.CategoryMapper;
import ua.cursor.filmrate.coreservice.mapper.MovieMapper;
import ua.cursor.filmrate.coreservice.mapper.ReviewMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieApiService {

    private final MovieApiClient movieApiClient;
    private final RateApiService rateApiService;
    private final ReviewApiService reviewApiService;

    private final MovieMapper movieMapper;
    private final ReviewMapper reviewMapper;
    private final CategoryMapper categoryMapper;

    public List<MovieDTO> getAll() {
        return movieMapper.toMovieDTOSet(
                movieApiClient.getAll());
    }

    public List<MovieDTO> getAllByCategoriesIn(List<CategoryDTO> categoryDTOs) {
        return movieMapper.toMovieDTOSet(movieApiClient.getAllByCategoriesIn(
                categoryMapper.toCategoryApiDTOSet(categoryDTOs)));
    }

    public MovieDTO getById(Long id) {
        MovieDTO movieDTO = movieMapper.toMovieDTO(movieApiClient.getById(id));
        RateApiDTO rateDTO = rateApiService.getByMovieId(movieDTO.getId());
        movieDTO.setReviews(reviewMapper.toReviewDTOSet(reviewApiService.getByMovieId(movieDTO.getId())));
        movieDTO.setRateValue(rateDTO.getRateValue());
        movieDTO.setVotesCount(rateDTO.getVotesCount());
        return movieDTO;
    }

    public MovieDTO save(MovieDTO movieDTO) {
        MovieDTO movieFromDB =
                movieMapper.toMovieDTO(
                        movieApiClient.save(
                                movieMapper.toMovieApiDTO(movieDTO)));
        RateApiDTO rateFromDB = rateApiService.save(new RateApiDTO(null, movieFromDB.getId(), null, null));
        movieDTO.setReviews(reviewMapper.toReviewDTOSet(reviewApiService.getByMovieId(movieFromDB.getId())));
        movieFromDB.setRateValue(rateFromDB.getRateValue());
        movieFromDB.setVotesCount(rateFromDB.getVotesCount());
        return movieFromDB;
    }

    public MovieDTO update(Long id, MovieDTO movieDTO) {
        MovieDTO movieFromDB =
                movieMapper.toMovieDTO(
                        movieApiClient.update(id, movieMapper.toMovieApiDTO(movieDTO)));
        RateApiDTO rateFromDB = rateApiService.getByMovieId(id);
        movieDTO.setReviews(reviewMapper.toReviewDTOSet(reviewApiService.getByMovieId(id)));
        movieFromDB.setRateValue(rateFromDB.getRateValue());
        movieFromDB.setVotesCount(rateFromDB.getVotesCount());
        return movieFromDB;
    }

    public void delete(Long id) {
        movieApiClient.delete(id);
        rateApiService.deleteByMovieId(id);
    }
}
