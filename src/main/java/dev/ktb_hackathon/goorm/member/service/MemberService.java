package dev.ktb_hackathon.goorm.member.service;

import dev.ktb_hackathon.goorm.member.entity.MemberEntity;
import dev.ktb_hackathon.goorm.member.exception.NotFoundMemberException;
import dev.ktb_hackathon.goorm.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    public String signIn(String email, String password) {
        MemberEntity foundMemberEntity = memberJpaRepository.findByEmailAndPassword(email, password)
                .orElseThrow(NotFoundMemberException::new);

        return foundMemberEntity.getEmail();
    }
}
