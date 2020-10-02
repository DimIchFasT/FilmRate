package ua.cursor.filmrate.movieservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import ua.cursor.filmrate.movieservice.jsonserializer.MovieCategoryJsonDeserializer;
import ua.cursor.filmrate.movieservice.jsonserializer.MovieCategoryJsonSerializer;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class MovieDTO {

    @JsonView(Views.All.Id.class)
    private Long id;

    @NonNull
    @JsonView(Views.All.Default.class)
    private String name;

    @NonNull
    @JsonView(Views.All.Default.class)
    private String director;

    @NonNull
    @JsonView(Views.All.Default.class)
    private String description;

    @JsonView(value = {Views.Movie.Full.class, Views.Movie.Default.class})
    @JsonSerialize(using = MovieCategoryJsonSerializer.class)
    @JsonDeserialize(using = MovieCategoryJsonDeserializer.class)
    private Set<CategoryDTO> categories = new HashSet<>();
}
