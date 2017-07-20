package com.assettrader.model.rest;

public class RWApiCredential {

	private Long walletId;

	private Long userId;

	private String username;

	private String token;

	private String apiKey;

	private String secretKey;

	private String walletOrigin;
	

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long id) {
		this.walletId = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getWalletOrigin() {
		return walletOrigin;
	}

	public void setWalletOrigin(String exchangeName) {
		this.walletOrigin = exchangeName;
	}

}
