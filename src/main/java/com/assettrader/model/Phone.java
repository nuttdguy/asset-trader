package com.assettrader.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Phone {

	@Id
	private Long id;
	private short importance;
	private PhoneType phoneType;
	private String phoneNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public short getImportance() {
		return importance;
	}

	public void setImportance(short importance) {
		this.importance = importance;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
