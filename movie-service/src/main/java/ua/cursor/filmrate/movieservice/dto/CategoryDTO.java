package ua.cursor.filmrate.movieservice.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import ua.cursor.filmrate.movieservice.jsonserializer.CategoryMovieJsonDeserializer;
import ua.cursor.filmrate.movieservice.jsonserializer.CategoryMovieJsonSerializer;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@ToString
public class CategoryDTO {

    @JsonView({Views.All.Id.class})
    private Long id;

    @NonNull
    @JsonView(Views.All.Default.class)
    private String name;

    @JsonView({Views.Category.Full.class, Views.Category.Default.class})
    @JsonSerialize(using = CategoryMovieJsonSerializer.class)
    @JsonDeserialize(using = CategoryMovieJsonDeserializer.class)
    private Set<MovieDTO> movies = new HashSet<>();
}