package ua.cursor.filmrate.coreservice.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.coreservice.dto.RateDTO;
import ua.cursor.filmrate.coreservice.mapper.RateMapper;
import ua.cursor.filmrate.coreservice.service.RateApiService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rates")
public class RESTRateController {
    private final RateApiService rateApiService;
    private final RateMapper rateMapper;

    @PostMapping("/{movieId}")
    public RateDTO addRate(@PathVariable Long movieId, @RequestBody RateDTO rateDTO) {
        rateDTO.setMovieId(movieId);
        return rateMapper.toRateDTO(rateApiService.update(movieId, rateMapper.toRateApiDTO(rateDTO)));
    }
}
