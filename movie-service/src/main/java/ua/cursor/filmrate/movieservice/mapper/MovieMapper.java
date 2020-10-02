package ua.cursor.filmrate.movieservice.mapper;


import org.mapstruct.*;
import ua.cursor.filmrate.movieservice.dto.MovieDTO;
import ua.cursor.filmrate.movieservice.entity.Movie;
import ua.cursor.filmrate.movieservice.mapper.annotations.DefaultMapper;
import ua.cursor.filmrate.movieservice.mapper.annotations.FullMapper;

import java.util.Set;

@Mapper(uses = {CategoryMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface MovieMapper {

    @DefaultMapper
    @Mapping(target = "categories", ignore = true)
    MovieDTO toDefaultMovieDTO(Movie movie);

    @DefaultMapper
    @IterableMapping(qualifiedBy = DefaultMapper.class)
    Set<MovieDTO> toDefaultMoviesDtoSet(Set<Movie> movies);

    @FullMapper
    @Mapping(target = "categories", qualifiedBy = DefaultMapper.class)
    MovieDTO toFullMovieDTO(Movie movie);

    @FullMapper
    @IterableMapping(qualifiedBy = FullMapper.class)
    Set<MovieDTO> toFullMoviesDtoSet(Set<Movie> movies);

    Movie toMovie(MovieDTO movieDTO);
}
