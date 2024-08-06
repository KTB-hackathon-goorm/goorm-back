package dev.ktb_hackathon.goorm.bin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bin")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class BinEntity {

    @Id
    private String id;

    private double latitude; // 위도

    private double longitude; // 경도

    private String rnAdres; // 도로명 주소

    private String emdNm; // 읍면동 명

    private String lnmAdres; // 단지명
}
