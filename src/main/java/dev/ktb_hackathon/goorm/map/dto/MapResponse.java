package dev.ktb_hackathon.goorm.map.dto;

import java.time.LocalDateTime;
import java.util.List;

public record MapResponse(List<Boolean> results, boolean win, LocalDateTime date) {
}
