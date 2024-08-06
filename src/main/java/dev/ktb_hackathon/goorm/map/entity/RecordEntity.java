package dev.ktb_hackathon.goorm.map.entity;

import dev.ktb_hackathon.goorm.question.entity.QuestionEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="record")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RecordEntity {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionEntity questionEntity;

    private boolean hangyeong;

    private boolean hallim;

    private boolean aewol;

    private boolean jeju;

    private boolean jocheon;

    private boolean gujwa;

    private boolean daejeong;

    private boolean andeok;

    private boolean jungmun;

    private boolean seogwipo;

    private boolean namwon;

    private boolean pyoseon;

    private boolean songsan;

    public RecordEntity(QuestionEntity questionEntity, boolean hangyeong, boolean hallim, boolean aewol, boolean jeju, boolean jocheon, boolean gujwa, boolean daejeong, boolean andeok, boolean jungmun, boolean seogwipo, boolean namwon, boolean pyoseon, boolean songsan) {
        this.questionEntity = questionEntity;
        this.hangyeong = hangyeong;
        this.hallim = hallim;
        this.aewol = aewol;
        this.jeju = jeju;
        this.jocheon = jocheon;
        this.gujwa = gujwa;
        this.daejeong = daejeong;
        this.andeok = andeok;
        this.jungmun = jungmun;
        this.seogwipo = seogwipo;
        this.namwon = namwon;
        this.pyoseon = pyoseon;
        this.songsan = songsan;
    }
}
