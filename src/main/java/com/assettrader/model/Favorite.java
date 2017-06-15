package com.assettrader.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Favorite {

	@Id
	private long favoriteId;
	private FavoriteType favoriteType;

	public long getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(long favoriteId) {
		this.favoriteId = favoriteId;
	}

	public FavoriteType getFavoriteType() {
		return favoriteType;
	}

	public void setFavoriteType(FavoriteType favoriteType) {
		this.favoriteType = favoriteType;
	}

}
