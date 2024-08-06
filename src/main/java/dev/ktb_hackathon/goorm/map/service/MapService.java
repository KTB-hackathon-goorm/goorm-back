package dev.ktb_hackathon.goorm.map.service;

import dev.ktb_hackathon.goorm.map.dto.MapResponse;
import dev.ktb_hackathon.goorm.map.dto.RecordResponse;
import dev.ktb_hackathon.goorm.member.entity.MemberEntity;
import dev.ktb_hackathon.goorm.member.exception.NotFoundMemberException;
import dev.ktb_hackathon.goorm.member.repository.MemberJpaRepository;
import dev.ktb_hackathon.goorm.post.domain.Location;
import dev.ktb_hackathon.goorm.post.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MapService {

    private final MemberJpaRepository memberJpaRepository;
    private final PostJpaRepository postJpaRepository;

    // 현재 경쟁 현황 확인
    public MapResponse getCurrentMap(String memberEmail){
        MemberEntity foundMemberEntity = memberJpaRepository.findByEmail(memberEmail)
                .orElseThrow(NotFoundMemberException::new);
        return getMapByDate(LocalDate.now(), foundMemberEntity.isTeam());
    }

    // 과거 경쟁 기록 조회
    public RecordResponse getMapRecords(String memberEmail){
        MemberEntity foundMemberEntity = memberJpaRepository.findByEmail(memberEmail)
                .orElseThrow(NotFoundMemberException::new);

        List<MapResponse> mapRecords = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (int i = 0; i < 7; i++) {
            LocalDate date = today.minusDays(i);
            MapResponse mapResponse = getMapByDate(date, foundMemberEntity.isTeam());
            mapRecords.add(mapResponse);
        }

        return new RecordResponse(mapRecords);
    }

    // 우승 지역 수를 계산하여 내 팀이 이겼는지 확인
    private boolean isWinner(List<Boolean> results, boolean my){
        long trueCount = results.stream().filter(result -> result).count();
        long falseCount = results.size() - trueCount;
        return trueCount >= falseCount == my;
    }

    // 입력 받은 날짜를 기준으로 승리 여부 확인
    private MapResponse getMapByDate(LocalDate date, boolean my){
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        List<Boolean> results = new ArrayList<>();
        for (Location location : Location.values()) {
            List<Object[]> counts = postJpaRepository.countPostsByLocationAndTeam(startOfDay, endOfDay, location);
            long trueCount = 0;
            long falseCount = 0;
            for (Object[] count : counts) {
                boolean team = (boolean) count[1];
                long postCount = (long) count[2];
                if (team) {
                    trueCount += postCount;
                } else {
                    falseCount += postCount;
                }
            }
            results.add(trueCount > falseCount);
        }

        return new MapResponse(
                results,
                isWinner(results, my),
                LocalDateTime.of(date, LocalTime.MIDNIGHT)
        );
    }
}
