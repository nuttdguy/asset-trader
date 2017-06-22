package com.assettrader.model.DTO;

import java.util.List;

public class CoinResultDTO {
	
	private boolean success;
	
	private String message;
	
	private List<CoinDTO> result;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<CoinDTO> getResult() {
		return result;
	}

	public void setResult(List<CoinDTO> result) {
		this.result = result;
	}

	
}
