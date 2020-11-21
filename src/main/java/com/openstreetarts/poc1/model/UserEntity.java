package com.openstreetarts.poc1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
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

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
}
