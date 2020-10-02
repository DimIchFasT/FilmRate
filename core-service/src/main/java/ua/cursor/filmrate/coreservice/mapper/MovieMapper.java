package ua.cursor.filmrate.coreservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ua.cursor.filmrate.coreservice.dto.MovieDTO;
import ua.cursor.filmrate.coreservice.dto.api.MovieApiDTO;

import java.util.List;

@Mapper(uses = {CategoryMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MovieMapper {

    @Mapping(target = "votesCount", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "rateValue", ignore = true)
    MovieDTO toMovieDTO(MovieApiDTO apiDTO);

    List<MovieDTO> toMovieDTOSet(List<MovieApiDTO> apiDTOs);

    @Mapping(target = "rate", ignore = true)
    MovieApiDTO toMovieApiDTO(MovieDTO movieDTO);

    List<MovieApiDTO> toMovieApiDTOSet(List<MovieDTO> movieDTOs);
}
