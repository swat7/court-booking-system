package com.sports.bookingsystem.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ErrorDTO implements Serializable {

	private static final long serialVersionUID = 5538376237674141388L;

	private ErrorType type;

	private String field;

	private String code;

	private String message;

	public ErrorDTO(String message) {
		this.message = message;
		this.code = "GENERIC_ERROR";
	}

	@Builder
	public ErrorDTO(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public enum ErrorType {
		VALIDATION_ERROR, DATABASE_ERROR, THIRD_PARTY_ERROR, BUSINESS_ERROR, INTERNAL_ERROR;
	}
}