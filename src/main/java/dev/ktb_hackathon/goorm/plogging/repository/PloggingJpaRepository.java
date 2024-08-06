package dev.ktb_hackathon.goorm.plogging.repository;

import dev.ktb_hackathon.goorm.plogging.entity.PloggingEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PloggingJpaRepository extends JpaRepository<PloggingEntity, Long> {

    Slice<PloggingEntity> findAllToSliceBy(Pageable pageable);
}
