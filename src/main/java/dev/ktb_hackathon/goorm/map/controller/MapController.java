package dev.ktb_hackathon.goorm.map.controller;


import dev.ktb_hackathon.goorm.map.dto.MapResponse;
import dev.ktb_hackathon.goorm.map.dto.RecordResponse;
import dev.ktb_hackathon.goorm.map.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MapController {
    private final MapService mapService;

    @GetMapping("/map")
    public MapResponse getTodayMap(String email) {
        return mapService.getCurrentMap(email);
    }

    @GetMapping("/map/record")
    public RecordResponse getMapRecords(String email) {
        return mapService.getMapRecords(email);
    }
}
