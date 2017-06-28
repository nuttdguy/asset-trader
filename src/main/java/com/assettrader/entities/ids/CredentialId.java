package com.assettrader.entities.ids;

import java.io.Serializable;

public class CredentialId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	public CredentialId() {
	}

	public CredentialId(Long id, String username) {
		this.id = id;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
