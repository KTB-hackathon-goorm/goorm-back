package dev.ktb_hackathon.goorm.question.entity;

import dev.ktb_hackathon.goorm.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="answer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AnswerEntity {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionEntity questionEntity;

    private boolean answer;

    public AnswerEntity(MemberEntity memberEntity, QuestionEntity questionEntity, boolean answer) {
        this.memberEntity = memberEntity;
        this.questionEntity = questionEntity;
        this.answer = answer;
    }
}
