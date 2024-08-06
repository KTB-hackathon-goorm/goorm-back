package dev.ktb_hackathon.goorm.bin.service;

import dev.ktb_hackathon.goorm.bin.entity.BinEntity;
import dev.ktb_hackathon.goorm.bin.repository.BinJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BinService {

    private final static double BASE_WEIGHT = 0.0017;

    private final BinJpaRepository binJpaRepository;

    public List<BinEntity> searchBin(double latitude, double longitude, int zoomLevel) {
        int weight = 10 - zoomLevel;

        double startLatitude = latitude - (BASE_WEIGHT * weight);
        double startLongitude = longitude - (BASE_WEIGHT * weight);

        double endLatitude = latitude + (BASE_WEIGHT * weight);
        double endLongitude = longitude + (BASE_WEIGHT * weight);

        return binJpaRepository.findByLatitudeBetweenAndLongitudeBetween(
                startLatitude,
                endLatitude,
                startLongitude,
                endLongitude
        );
    }
}
