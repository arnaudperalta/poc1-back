package com.openstreetarts.poc1.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
	
	static public final int PSW_MIN_LENGTH = 8;

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;


}
