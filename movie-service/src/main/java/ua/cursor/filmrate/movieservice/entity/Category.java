package ua.cursor.filmrate.movieservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(of = {"id", "name"})
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Exclude
    private String name;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "categories", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<Movie> movies = new HashSet<>();

    public boolean addMovie(Movie movie) {
        this.movies.add(movie);
        return movie.addCategory(this);
    }

    public boolean removeMovie(Movie movie) {
        this.movies.remove(movie);
        return movie.removeCategory(this);
    }
}
