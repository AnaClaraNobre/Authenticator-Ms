package com.ecommerce.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "AUTH_USER_CRT", schema = "auth")
public class AuthUserModel {
	
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_USER_CRT_SEQ")
    @SequenceGenerator(
        name = "AUTH_USER_CRT_SEQ",
        sequenceName = "AUTH_USER_CRT_SEQ", 
        allocationSize = 1                       
    )
    @Column(name = "ID", nullable = false)
    private Long id;

	@Column(name = "EMAIL",nullable = false, unique = true)
	private String email;

	@Column(name = "PASSWORD_HASH",nullable = false)
	private String passwordHash;
	
	@Column(name = "CREATE_DATE")
	private String createDate;

	public AuthUserModel(String email, String hashedPassword) {
		this.email = email;
		this.passwordHash = hashedPassword;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
