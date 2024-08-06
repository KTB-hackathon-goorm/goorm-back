package dev.ktb_hackathon.goorm.member.controller;

import dev.ktb_hackathon.goorm.member.request.SignInRequest;
import dev.ktb_hackathon.goorm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final MemberService memberService;

    // 첫 로그인 시 true
    @PostMapping("/sign-in")
    public boolean signIn(@RequestBody SignInRequest request) {
        return memberService.signIn(request.email(), request.password());
    }
}
