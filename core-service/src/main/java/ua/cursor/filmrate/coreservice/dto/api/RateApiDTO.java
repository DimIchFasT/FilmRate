package ua.cursor.filmrate.coreservice.dto.api;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class RateApiDTO {

    private Long id;
    private Long movieId;
    private Long votesCount;
    private Double rateValue;
}
