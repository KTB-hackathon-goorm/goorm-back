package dev.ktb_hackathon.goorm.post.repository;

import dev.ktb_hackathon.goorm.post.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {

    Slice<PostEntity> findAllToSliceBy(Pageable pageable);
}
