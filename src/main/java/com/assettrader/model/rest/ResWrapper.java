package com.assettrader.model.rest;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ResWrapper<T> {

	private boolean success;
	private HttpStatus status;
	private String message;

	private T result;
	
	private List<T> resultList;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public void setResult(T result) {
		this.result = result;
	}

}
