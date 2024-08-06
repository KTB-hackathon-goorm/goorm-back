package dev.ktb_hackathon.goorm.plogging.request;

import org.springframework.web.multipart.MultipartFile;

public record RegisterPloggingRequest(
        String memberEmail,
        double latitude,
        double longitude,
        MultipartFile image,
        String content
) {
}
