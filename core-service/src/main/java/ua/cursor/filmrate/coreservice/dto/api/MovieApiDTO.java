package ua.cursor.filmrate.coreservice.dto.api;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class MovieApiDTO {

    private Long id;
    private String name;
    private String director;
    private String description;
    private RateApiDTO rate;
    private Set<CategoryApiDTO> categories;

}
