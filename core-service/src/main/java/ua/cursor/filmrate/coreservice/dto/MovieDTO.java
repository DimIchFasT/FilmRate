package ua.cursor.filmrate.coreservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDTO {

    private Long id;
    private String name;
    private String director;
    private String description;
    private Double rateValue;
    private Long votesCount;
    private List<CategoryDTO> categories;
    private List<ReviewDTO> reviews;

}
