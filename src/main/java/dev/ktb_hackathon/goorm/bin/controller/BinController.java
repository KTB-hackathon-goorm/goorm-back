package dev.ktb_hackathon.goorm.bin.controller;

import dev.ktb_hackathon.goorm.bin.entity.BinEntity;
import dev.ktb_hackathon.goorm.bin.request.SearchBinRequest;
import dev.ktb_hackathon.goorm.bin.service.BinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BinController {

    private final BinService binService;

    @GetMapping("/bin")
    public List<BinEntity> searchBin(SearchBinRequest request) {
        return binService.searchBin(request.latitude(), request.longitude());
    }
}
