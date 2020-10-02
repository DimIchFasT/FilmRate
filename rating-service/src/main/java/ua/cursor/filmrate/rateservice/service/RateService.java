package ua.cursor.filmrate.rateservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.cursor.filmrate.rateservice.entity.Rate;
import ua.cursor.filmrate.rateservice.repository.RateRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateService {

    private final RateRepository rateRepository;

    public List<Rate> getAll() {
        return rateRepository.findAll();
    }

    public Rate getById(Long id) {
        Rate rate = rateRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        log.info("IN getById - rate: {} found by id: {}", rate, id);
        return rate;
    }

    public Rate getByMovieId(Long id) {
        Rate rate = rateRepository.findByMovieId(id).orElseThrow(IllegalArgumentException::new);
        log.info("IN getById - rate: {} found by id: {}", rate, id);
        return rate;
    }

    public Rate save(Rate rate) {
        rate.setRateValue(0D);
        rate.setVotesCount(0L);
        Rate savedRate = rateRepository.save(rate);
        log.info("IN save - rate: {} successfully created", savedRate);
        return savedRate;
    }

    public Rate update(Long id, Rate rate) {
        Rate rateFromDB = rateRepository.getById(id).orElseThrow(IllegalArgumentException::new);
        rateFromDB.setVotesCount(rateFromDB.getVotesCount() + 1);
        if (rate.getRateValue() != null
                && !rate.getRateValue().equals(rateFromDB.getRateValue())) {
            rateFromDB.setRateValue(rate.getRateValue());
        }
        System.out.println("TUT ЛАЖА");
        Rate savedRate = rateRepository.save(rateFromDB);
        log.info("IN update - rate: {} updated", savedRate);
        return savedRate;
    }

    public boolean delete(Long id) {
        rateRepository.deleteById(id);
        if (rateRepository.existsById(id)) {
            log.warn("IN delete - rate with id: {} not deleted", id);
            return false;
        } else {
            log.info("IN delete - rate with id: {} successfully deleted", id);
            return true;
        }
    }

    public boolean deleteByMovieId(Long id) {
        rateRepository.deleteByMovieId(id);
        if (rateRepository.existsById(id)) {
            log.warn("IN deleteByMovieId - rate with movieId: {} not deleted", id);
            return false;
        } else {
            log.info("IN deleteByMovieId - rate with movieId: {} successfully deleted", id);
            return true;
        }
    }
}
