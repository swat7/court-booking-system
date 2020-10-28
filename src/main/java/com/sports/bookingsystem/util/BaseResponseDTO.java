package com.sports.bookingsystem.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class BaseResponseDTO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3350690498042366465L;

	private Status status;

	private List<ErrorDTO> errors;

	private T data;

	public BaseResponseDTO() {
		this.status = Status.OK;
	}

	public BaseResponseDTO(T data) {
		this();
		this.data = data;
	}

	public BaseResponseDTO(ErrorDTO error) {
		this.status = Status.ERROR;
		this.errors = new ArrayList<>();
		this.errors.add(error);
	}

	public BaseResponseDTO(List<ErrorDTO> errors) {
		this.status = Status.ERROR;
		this.errors = errors;
	}

}
