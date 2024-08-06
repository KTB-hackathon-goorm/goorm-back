package dev.ktb_hackathon.goorm.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ExceptionResponse> handleBaseException(BaseException ex) {
		log.warn(ex.getMessage(), ex);

		return ResponseEntity.status(ex.getHttpStatus())
			.body(new ExceptionResponse(ex.getMessage()));
	}
}
