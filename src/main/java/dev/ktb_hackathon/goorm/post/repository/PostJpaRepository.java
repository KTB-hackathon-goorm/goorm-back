package dev.ktb_hackathon.goorm.post.repository;

import dev.ktb_hackathon.goorm.post.domain.Location;
import dev.ktb_hackathon.goorm.post.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {

    Slice<PostEntity> findAllToSliceBy(Pageable pageable);

    @Query("SELECT p.location, m.team, COUNT(p) " +
            "FROM PostEntity p " +
            "JOIN p.memberEntity m " +
            "WHERE p.createdAt >= :startDate AND p.createdAt < :endDate AND p.location = :location " +
            "GROUP BY p.location, m.team")
    List<Object[]> countPostsByLocationAndTeam(@Param("startDate") LocalDateTime startDate,
                                               @Param("endDate") LocalDateTime endDate,
                                               @Param("location") Location location);

}
