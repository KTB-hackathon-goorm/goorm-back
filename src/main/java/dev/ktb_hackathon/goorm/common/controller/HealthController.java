package dev.ktb_hackathon.goorm.common.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

	@GetMapping
	public ResponseEntity<HealthResponse> check() {
		return ResponseEntity.ok(HealthResponse.of());
	}

	public record HealthResponse(
		String message
	) {

		public static HealthResponse of() {
			return new HealthResponse("ok");
		}
	}
}
