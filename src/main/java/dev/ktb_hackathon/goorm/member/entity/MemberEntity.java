package dev.ktb_hackathon.goorm.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(unique = true, nullable = false)
    private String nickname;

    private boolean isFirstLogin;

    private boolean team;

    private int point;

    public MemberEntity(String email, String password, String nickname, boolean team) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.isFirstLogin = true;
        this.team = team;
    }

    public void updateLogin() {
        this.isFirstLogin = false;
    }

    public void selectTeam(boolean team) {
        this.team = team;
    }

    public void increasePoint(int point) {
        this.point += point;
    }
}
