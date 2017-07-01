package com.assettrader.model.publicapi.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "success", "message" })
public class ResultDTO<T> {
	
	private T[] result;

	public T[] getResult() {
		return result;
	}

	public void setResult(T[] result) {
		this.result = result;
	}

}
