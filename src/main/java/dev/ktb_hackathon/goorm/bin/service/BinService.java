package dev.ktb_hackathon.goorm.bin.service;

import dev.ktb_hackathon.goorm.bin.entity.BinEntity;
import dev.ktb_hackathon.goorm.bin.repository.BinJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BinService {

    private final BinJpaRepository binJpaRepository;

    public List<BinEntity> searchBin(double latitude, double longitude) {
        double startLatitude = latitude - 0.005;
        double startLongitude = longitude - 0.005;

        double endLatitude = latitude + 0.005;
        double endLongitude = longitude + 0.005;

        return binJpaRepository.findByLatitudeBetweenAndLongitudeBetween(startLatitude, endLatitude, startLongitude, endLongitude);
    }
}
