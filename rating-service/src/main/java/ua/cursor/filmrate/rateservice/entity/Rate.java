package ua.cursor.filmrate.rateservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, unique = true, updatable = false)
    private Long movieId;

    @NonNull
    private Long votesCount;

    @NonNull
    private Double rateValue;
}
