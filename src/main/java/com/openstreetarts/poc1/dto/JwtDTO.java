package com.openstreetarts.poc1.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtDTO implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;

	public JwtDTO(String jwttoken) {
		this.jwttoken = jwttoken;
	}

}