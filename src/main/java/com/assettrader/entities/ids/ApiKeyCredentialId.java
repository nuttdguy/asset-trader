package com.assettrader.entities.ids;

import java.io.Serializable;

public class ApiKeyCredentialId implements Serializable {

	private static final long serialVersionUID = 2458779311736198385L;
	
	private String apiKey;
	private String secretKey;
	
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
	
	
}
