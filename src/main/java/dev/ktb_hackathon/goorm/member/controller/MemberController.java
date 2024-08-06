package dev.ktb_hackathon.goorm.member.controller;

import dev.ktb_hackathon.goorm.member.request.SelectTeamRequest;
import dev.ktb_hackathon.goorm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PatchMapping("/members")
    public void selectTeam(@RequestBody SelectTeamRequest selectTeamRequest) {
        memberService.selectTeam(selectTeamRequest.email(), selectTeamRequest.team());
    }
}
