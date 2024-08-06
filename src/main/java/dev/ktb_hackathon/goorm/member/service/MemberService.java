package dev.ktb_hackathon.goorm.member.service;

import dev.ktb_hackathon.goorm.member.entity.MemberEntity;
import dev.ktb_hackathon.goorm.member.exception.NotFoundMemberException;
import dev.ktb_hackathon.goorm.member.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    @Transactional
    public boolean signIn(String email, String password) {
        MemberEntity foundMemberEntity = memberJpaRepository.findByEmailAndPassword(email, password)
                .orElseThrow(NotFoundMemberException::new);

        if (foundMemberEntity.isFirstLogin()) {
            foundMemberEntity.updateLogin();

            return true;
        } else {
            return false;
        }
    }
}
