package ua.cursor.filmrate.movieservice.jsonserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.cursor.filmrate.movieservice.dto.CategoryDTO;
import ua.cursor.filmrate.movieservice.dto.Views;

import java.io.IOException;
import java.util.Set;

public class MovieCategoryJsonDeserializer extends JsonDeserializer<Set<CategoryDTO>> {
    @Override
    public Set<CategoryDTO> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readerWithView(Views.All.Id.class).readValue(jsonParser, new TypeReference<>() {
        });
    }
}
