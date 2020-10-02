package ua.cursor.filmrate.rateservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateDTO {

    @JsonView(Views.Rate.Id.class)
    private Long id;

    @JsonView({Views.Rate.Create.class, Views.Rate.Default.class})
    private Long movieId;

    @JsonView(Views.Rate.Default.class)
    private Long votesCount;

    @JsonView(Views.Rate.Default.class)
    private Double rateValue;
}
