package com.assettrader.model.coin;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderBook {

	@Id
	private long id;
	private int quantity;
	private double rate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}
