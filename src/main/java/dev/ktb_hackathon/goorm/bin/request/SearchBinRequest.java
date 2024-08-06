package dev.ktb_hackathon.goorm.bin.request;

public record SearchBinRequest(
        double latitude,
        double longitude,
        int zoomLevel
) {
}
