package com.assettrader.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FavoriteType {

	@Id
	private long id;
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
