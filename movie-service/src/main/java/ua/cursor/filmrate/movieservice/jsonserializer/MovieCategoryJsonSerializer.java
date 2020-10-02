package ua.cursor.filmrate.movieservice.jsonserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import ua.cursor.filmrate.movieservice.dto.Views;
import ua.cursor.filmrate.movieservice.entity.Category;

import java.io.IOException;
import java.util.Set;

public class MovieCategoryJsonSerializer extends JsonSerializer<Set<Category>> {

    @Override
    public void serialize(Set<Category> categories, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithView(Views.All.Full.class).writeValue(jsonGenerator, categories);
    }
}
