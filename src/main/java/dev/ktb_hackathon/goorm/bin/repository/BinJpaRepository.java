package dev.ktb_hackathon.goorm.bin.repository;

import dev.ktb_hackathon.goorm.bin.entity.BinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BinJpaRepository extends JpaRepository<BinEntity, Long> {

    List<BinEntity> findByLatitudeBetweenAndLongitudeBetween(double startLatitude, double endLatitude, double startLongitude, double endLongitude);
}
