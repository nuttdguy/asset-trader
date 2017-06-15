package com.assettrader.model;

import javax.persistence.Entity;

@Entity
public class PhoneType {

	private Long id;
	private String typeDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

}
