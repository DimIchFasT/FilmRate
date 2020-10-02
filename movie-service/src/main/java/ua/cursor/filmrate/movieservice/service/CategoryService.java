package ua.cursor.filmrate.movieservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cursor.filmrate.movieservice.entity.Category;
import ua.cursor.filmrate.movieservice.entity.Movie;
import ua.cursor.filmrate.movieservice.exceptions.CategoryNotFoundException;
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
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MovieRepository movieRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id) {
        Category category = categoryRepository
                .getByIdWithMoviesAndCategories(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        log.info("IN getById - category: {} found by id: {}", category, id);
        return category;
    }

    public Category save(Category category) {
        List<Long> moviesId = category.getMovies()
                .stream()
                .map(Movie::getId)
                .collect(Collectors.toList());

        Set<Movie> movies = movieRepository.getAllByIdInWithCategories(moviesId);
        movies
                .forEach(movie -> movie.getCategories().add(category));
        category.setMovies(movies);

        Category savedCategory = categoryRepository.save(category);
        log.info("IN save - category: {} successfully created and add movies: {}", savedCategory, movies);
        return savedCategory;
    }

    @Transactional
    public Category update(Long id, Category newCategory) {
        Category categoryFromDB = categoryRepository
                .getByIdWithMoviesAndCategories(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        List<Long> newMoviesIDs = newCategory.getMovies()
                .stream()
                .map(Movie::getId)
                .collect(Collectors.toList());

        List<Long> moviesIdFromDB = categoryFromDB.getMovies()
                .stream()
                .map(Movie::getId)
                .collect(Collectors.toList());

        if (!categoryFromDB.getName().equals(newCategory.getName())) {
            categoryFromDB.setName(newCategory.getName());
        }

        if (!newMoviesIDs.containsAll(moviesIdFromDB)
                || !moviesIdFromDB.containsAll(newMoviesIDs)) {
            removeMovieFromCategory(categoryFromDB, newMoviesIDs);
            addNewMoviesToCategory(categoryFromDB, movieRepository.getAllByIdInWithCategories(newMoviesIDs), newMoviesIDs);
        }

        log.info("IN update - category: {} updated", categoryFromDB);

        return categoryFromDB;
    }

    @Transactional
    public boolean delete(long id) {
        Category categoryFromDB = categoryRepository
                .getByIdWithMoviesAndCategories(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        removeMovieFromCategory(categoryFromDB, new ArrayList<>());
        categoryRepository.deleteById(id);
        if (categoryRepository.existsById(id)) {
            log.warn("IN delete - category with id: {} not deleted", id);
            return false;
        } else {
            log.info("IN delete - category with id: {} successfully deleted", id);
            return true;
        }
    }

    private void addNewMoviesToCategory(Category categoryFromDB, Set<Movie> newMovies, List<Long> newMovieIds) {
        Predicate<Movie> containsInUpdate = movie -> newMovieIds.contains(movie.getId());
        Set<Movie> movies = Set.copyOf(newMovies);

        movies.forEach(movie -> movie.removeCategory(categoryFromDB));

        List<Movie> updatedMovies =
                movies.stream()
                        .filter(containsInUpdate)
                        .collect(Collectors.toList());
        updatedMovies
                .forEach(movie -> movie.addCategory(categoryFromDB));
    }

    private void removeMovieFromCategory(Category categoryFromDB, List<Long> newMovieIds) {
        Predicate<Movie> containsInUpdate = movie -> newMovieIds.contains(movie.getId());
        List<Movie> removeMovies = categoryFromDB.getMovies()
                .stream()
                .filter(containsInUpdate.negate())
                .collect(Collectors.toList());
        removeMovies
                .forEach(movie -> movie.removeCategory(categoryFromDB));
    }
}