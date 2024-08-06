package dev.ktb_hackathon.goorm.member.request;

public record SelectTeamRequest(
        String email,
        boolean team
) {
}
