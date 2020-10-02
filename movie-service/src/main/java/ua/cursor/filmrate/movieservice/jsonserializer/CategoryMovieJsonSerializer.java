package ua.cursor.filmrate.movieservice.jsonserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import ua.cursor.filmrate.movieservice.dto.Views;
import ua.cursor.filmrate.movieservice.entity.Movie;

import java.io.IOException;
import java.util.Set;

public class CategoryMovieJsonSerializer extends JsonSerializer<Set<Movie>> {

    @Override
    public void serialize(Set<Movie> movies, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithView(Views.Movie.Full.class).writeValue(jsonGenerator, movies);
    }
}