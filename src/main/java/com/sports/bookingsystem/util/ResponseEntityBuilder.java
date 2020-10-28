package com.sports.bookingsystem.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.List;

public class ResponseEntityBuilder {
	public static <T> ResponseEntity buildSuccessResponse(T payload) {
		return ResponseEntity.ok().body(new BaseResponseDTO<>(payload));
	}

	public static ResponseEntity buildSuccessResponse() {
		return buildSuccessResponse("OK");
	}

	public static ResponseEntity buildFailureResponse(List<ErrorDTO> errors) {
		return ResponseEntity.status(BAD_REQUEST).body(new BaseResponseDTO<>(errors));
	}

	public static ResponseEntity buildFailureResponse(Exception exception) {
		ErrorDTO error;
		HttpStatus status = INTERNAL_SERVER_ERROR;	
		error = new ErrorDTO(exception.getMessage());
		return ResponseEntity.status(status).body(new BaseResponseDTO<>(error));
	}

}
