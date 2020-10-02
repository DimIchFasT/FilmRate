package ua.cursor.filmrate.coreservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ua.cursor.filmrate.coreservice.dto.RateDTO;
import ua.cursor.filmrate.coreservice.dto.api.RateApiDTO;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RateMapper {

    RateDTO toRateDTO(RateApiDTO apiDTO);

    RateApiDTO toRateApiDTO(RateDTO rateDTO);

}
