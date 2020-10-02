package ua.cursor.filmrate.rateservice.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class ReviewDTO {

    @JsonView(Views.Review.Id.class)
    private Long id;

    @JsonView({Views.Review.Create.class, Views.Review.Default.class})
    private Long movieId;

    @NonNull
    @JsonView({Views.Review.Create.class, Views.Review.Default.class})
    private String message;

    @NonNull
    @JsonView({Views.Review.Create.class, Views.Review.Default.class})
    private Boolean liked;
}
