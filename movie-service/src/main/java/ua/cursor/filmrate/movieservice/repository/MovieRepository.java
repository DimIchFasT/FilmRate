package ua.cursor.filmrate.movieservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.cursor.filmrate.movieservice.entity.Category;
import ua.cursor.filmrate.movieservice.entity.Movie;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("from Movie as m left join fetch m.categories where m.id =:id")
    Optional<Movie> getByIdWithCategories(long id);

    @Query("from Movie as m left join fetch m.categories")
    List<Movie> getAllWithCategories();

    @Query("from Movie as m left join fetch m.categories as c left join fetch c.movies where m.id =:id")
    Optional<Movie> getByIdWithCategoriesAndMovies(long id);

    @Query("from Movie as m left join fetch m.categories as c where c in :categories group by m.id")
    List<Movie> getAllByCategoriesContains(List<Category> categories);

    @Query("from Movie as m left join fetch m.categories as c where c.id = :categoryId group by m.id")
    List<Movie> getAllByCategoryId(Long categoryId);

    @Query("from Movie as m left join fetch m.categories as c where m.id in :ids")
    Set<Movie> getAllByIdInWithCategories(List<Long> ids);

    Set<Movie> getAllByIdIn(List<Long> ids);
}
