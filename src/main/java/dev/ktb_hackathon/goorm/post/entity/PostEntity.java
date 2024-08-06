package dev.ktb_hackathon.goorm.post.entity;

import dev.ktb_hackathon.goorm.member.entity.MemberEntity;
import dev.ktb_hackathon.goorm.post.domain.Location;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity {

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

    @Enumerated(EnumType.STRING)
    private Location location;

    @CreatedDate
    private LocalDateTime createdAt;

    public PostEntity(MemberEntity memberEntity, double latitude, double longitude, String imagePath, String content, Location location) {
        this.memberEntity = memberEntity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imagePath = imagePath;
        this.content = content;
        this.location = location;
    }
}
