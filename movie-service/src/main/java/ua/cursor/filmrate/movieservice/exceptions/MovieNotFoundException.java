package ua.cursor.filmrate.movieservice.exceptions;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(Long id) {
        super("Could not find movie with id = " + id);
    }
}
