package ua.cursor.filmrate.coreservice.dto.api;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class ReviewApiDTO {
    private Long id;
    private Long movieId;
    private String message;
    private Boolean liked;
}
