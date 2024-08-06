package dev.ktb_hackathon.goorm.plogging.entity;

import dev.ktb_hackathon.goorm.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "Plogging")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PloggingEntity {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    private double latitude;

    private double longitude;

    private String imagePath;

    private String content;

    private int likes;

    @CreatedDate
    private LocalDateTime createdAt;

    public PloggingEntity(MemberEntity memberEntity, double latitude, double longitude, String imagePath, String content) {
        this.memberEntity = memberEntity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imagePath = imagePath;
        this.content = content;
    }
}
