package ua.cursor.filmrate.rateservice.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.cursor.filmrate.rateservice.dto.RateDTO;
import ua.cursor.filmrate.rateservice.dto.Views;
import ua.cursor.filmrate.rateservice.mapper.RateMapper;
import ua.cursor.filmrate.rateservice.service.RateService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RateController {

    private final RateService rateService;
    private final RateMapper rateMapper;

    @GetMapping
    @JsonView(Views.Rate.Full.class)
    public List<RateDTO> getAll() {
        return rateMapper.toRateDTOs(rateService.getAll());
    }

    @GetMapping("/{id}")
    @JsonView(Views.Rate.Full.class)
    public RateDTO getById(@PathVariable Long id) {
        return rateMapper.toRateDTO(rateService.getById(id));
    }

    @GetMapping("/movies/{id}")
    @JsonView(Views.Rate.Full.class)
    public RateDTO getByMovieId(@PathVariable Long id) {
        return rateMapper.toRateDTO(rateService.getByMovieId(id));
    }

    @PostMapping
    @JsonView({Views.Rate.Full.class})
    public RateDTO save(@JsonView(Views.Rate.Create.class) @RequestBody RateDTO rateDTO) {
        return rateMapper.toRateDTO(rateService.save(rateMapper.toRate(rateDTO)));
    }

    @PutMapping("/{id}")
    @JsonView(Views.Rate.Full.class)
    public RateDTO update(@PathVariable Long id, @JsonView(Views.Rate.Default.class) @RequestBody RateDTO rateDTO) {
        return rateMapper.toRateDTO(rateService.update(id, rateMapper.toRate(rateDTO)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        rateService.delete(id);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteByMovieId(@PathVariable Long id) {
        rateService.deleteByMovieId(id);
    }
}
