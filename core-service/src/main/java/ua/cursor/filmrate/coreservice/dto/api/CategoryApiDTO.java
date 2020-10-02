package ua.cursor.filmrate.coreservice.dto.api;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@ToString
public class CategoryApiDTO {

    private Long id;
    private String name;
    private Set<MovieApiDTO> movies;
}
