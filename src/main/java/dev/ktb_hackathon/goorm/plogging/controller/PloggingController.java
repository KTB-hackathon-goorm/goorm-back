package dev.ktb_hackathon.goorm.plogging.controller;

import dev.ktb_hackathon.goorm.plogging.entity.PloggingEntity;
import dev.ktb_hackathon.goorm.plogging.request.RegisterPloggingRequest;
import dev.ktb_hackathon.goorm.plogging.service.PloggingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PloggingController {

    private final PloggingService ploggingService;

    @GetMapping("/plogging/{pageNumber}")
    public List<PloggingEntity> searchByPageNumber(@PathVariable int pageNumber) {
        return ploggingService.searchByPageNumber(pageNumber);
    }

    @PostMapping("/plogging")
    public void register(RegisterPloggingRequest request) {
        ploggingService.register(request.memberEmail(), request.latitude(), request.longitude(), request.image(), request.content());
    }
}
