package dev.ktb_hackathon.goorm.post.request;

import org.springframework.web.multipart.MultipartFile;

public record RegisterPostRequest(
        String memberEmail,
        double latitude,
        double longitude,
        MultipartFile image,
        String content,
        String location,
        int point
) {
}
