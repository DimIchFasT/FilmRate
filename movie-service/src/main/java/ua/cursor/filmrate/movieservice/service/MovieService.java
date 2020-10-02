package ua.cursor.filmrate.movieservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.cursor.filmrate.movieservice.entity.Category;
import ua.cursor.filmrate.movieservice.entity.Movie;
import ua.cursor.filmrate.movieservice.exceptions.MovieNotFoundException;
import ua.cursor.filmrate.movieservice.repository.CategoryRepository;
import ua.cursor.filmrate.movieservice.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    public List<Movie> getAll() {
        return movieRepository.getAllWithCategories();
    }

    public Movie getById(Long id) {
        Movie movie = movieRepository.getByIdWithCategories(id).orElseThrow(() -> new MovieNotFoundException(id));
        log.info("IN getById - movie: {} found by id: {}", movie, id);
        return movie;
    }

    public Movie save(Movie movie) {
        List<Long> categoriesId = movie.getCategories()
                .stream()
                .map(Category::getId)
                .collect(Collectors.toList());

        Set<Category> categories = categoryRepository.getAllByIdInWithMoviesAndCategories(categoriesId);
        categories
                .forEach(movie::addCategory);
        movie.setCategories(categories);

        Movie savedMovie = movieRepository.save(movie);
        log.info("IN save - movie: {} successfully created", savedMovie);
        return savedMovie;
    }

    public Movie update(Long id, Movie movie) {
        Movie movieFromDB = movieRepository.getByIdWithCategories(id).orElseThrow(() -> new MovieNotFoundException(id));
        List<Long> newCategoriesIDs =
                movie.getCategories()
                        .stream()
                        .map(Category::getId)
                        .collect(Collectors.toList());
        List<Long> categoriesIdFromDB =
                movieFromDB.getCategories()
                        .stream()
                        .map(Category::getId)
                        .collect(Collectors.toList());
        if (!newCategoriesIDs.containsAll(categoriesIdFromDB)
                || !categoriesIdFromDB.containsAll(newCategoriesIDs)) {
            removeCategoriesFromMovie(movieFromDB, newCategoriesIDs);
            addCategoriesToMovie(movieFromDB, categoryRepository.getAllByIdInWithMovies(newCategoriesIDs), newCategoriesIDs);
        }
        if (!movieFromDB.getName().equals(movie.getName())) {
            movieFromDB.setName(movie.getName());
        }
        if (!movieFromDB.getDirector().equals(movie.getDirector())) {
            movieFromDB.setDirector(movie.getDirector());
        }
        if (!movieFromDB.getDescription().equals(movie.getDescription())) {
            movieFromDB.setDescription(movie.getDescription());
        }
        log.info("IN update - movie: {} updated", movieFromDB);

        return movieFromDB;
    }

    public void delete(long id) {
        Movie movie = movieRepository
                .getByIdWithCategoriesAndMovies(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
        removeCategoriesFromMovie(movie, new ArrayList<>());
        movieRepository.deleteById(id);
        if (movieRepository.existsById(id)) {
            log.warn("IN delete - movie with id: {} not deleted", id);
        } else {
            log.info("IN delete - movie with id: {} successfully deleted", id);
        }
    }

    private void addCategoriesToMovie(Movie movie, Set<Category> newCategories, List<Long> newCategoriesIds) {
        Predicate<Category> containsInUpdate = category -> newCategoriesIds.contains(category.getId());
        Set<Category> categories = Set.copyOf(newCategories);
        categories
                .forEach(movie::removeCategory);
        List<Category> updatedCategories =
                categories
                        .stream()
                        .filter(containsInUpdate)
                        .collect(Collectors.toList());
        updatedCategories
                .forEach(movie::addCategory);
    }

    private void removeCategoriesFromMovie(Movie movie, List<Long> newCategoriesIds) {
        Predicate<Category> containsInUpdate = category -> newCategoriesIds.contains(category.getId());
        List<Category> removedCategories =
                movie.getCategories()
                        .stream()
                        .filter(containsInUpdate.negate())
                        .collect(Collectors.toList());
        removedCategories
                .forEach(movie::removeCategory);
    }
}
