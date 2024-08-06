package dev.ktb_hackathon.goorm.question.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name="question")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class QuestionEntity {
    @Id
    @GeneratedValue
    private long id;

    private String content;

    private LocalDateTime date;

    public QuestionEntity(String content, LocalDateTime date) {
        this.content = content;
        this.date = date;
    }
}

