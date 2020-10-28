package com.sports.bookingsystem.util;

import lombok.Getter;

@Getter
public enum Status {
	OK(200, "Request succeeded"), ERROR(500, "Error");

	private int code;

	private final String description;

	private Status(int code, String description) {
		this.description = description;
		this.code = code;
	}
}
