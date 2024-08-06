package dev.ktb_hackathon.goorm.bin.service;

import dev.ktb_hackathon.goorm.bin.entity.BinEntity;
import dev.ktb_hackathon.goorm.bin.repository.BinJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BinService {

    private final BinJpaRepository binJpaRepository;

    public List<BinEntity> searchBin(double latitude, double longitude, int zoomLevel) {
        double weight = getBaseWeight(zoomLevel);

        double startLatitude = latitude - (weight);
        double startLongitude = longitude - (weight);

        double endLatitude = latitude + (weight);
        double endLongitude = longitude + (weight);

        return binJpaRepository.findByLatitudeBetweenAndLongitudeBetween(
                startLatitude,
                endLatitude,
                startLongitude,
                endLongitude
        );
    }

    private double getBaseWeight(int zoomLevel) {
        return switch (zoomLevel) {
            case 1 -> 0.001;
            case 2 -> 0.0015;
            case 3 -> 0.005;
            case 4 -> 0.025;
            case 5 -> 0.05;
            case 6 -> 0.1;
            default -> 0.1;
        };
    }
}
