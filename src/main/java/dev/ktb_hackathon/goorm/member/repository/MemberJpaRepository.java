package dev.ktb_hackathon.goorm.member.repository;

import dev.ktb_hackathon.goorm.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByEmail(String email);

    Optional<MemberEntity> findByEmailAndPassword(String email, String password);
}
