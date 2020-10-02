package ua.cursor.filmrate.movieservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.cursor.filmrate.movieservice.entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("from Category as c left join fetch c.movies as m left join fetch m.categories where c.id in :ids")
    Set<Category> getAllByIdInWithMoviesAndCategories(List<Long> ids);

    @Query("from Category as c left join fetch c.movies as m left join fetch m.categories where c.id =:id")
    Optional<Category> getByIdWithMoviesAndCategories(long id);

    @Query("from Category as c left join fetch c.movies where c.id =:id")
    Optional<Category> getByIdWithMovies(long id);

    @Query("from Category as c left join fetch c.movies where c.id in :ids")
    Set<Category> getAllByIdInWithMovies(List<Long> ids);

    Set<Category> getAllByIdIn(List<Long> ids);

}