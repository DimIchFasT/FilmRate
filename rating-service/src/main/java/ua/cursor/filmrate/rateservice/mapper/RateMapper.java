package ua.cursor.filmrate.rateservice.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ua.cursor.filmrate.rateservice.dto.RateDTO;
import ua.cursor.filmrate.rateservice.entity.Rate;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RateMapper {

    RateDTO toRateDTO(Rate rate);

    List<RateDTO> toRateDTOs(List<Rate> rates);

    Rate toRate(RateDTO rateDTO);

}
