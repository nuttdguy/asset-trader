package com.assettrader.model.coin;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ticker {

	@Id
	private long id;
	private double bid;
	private double ask;
	private double last;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getBid() {
		return bid;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public double getAsk() {
		return ask;
	}

	public void setAsk(double ask) {
		this.ask = ask;
	}

	public double getLast() {
		return last;
	}

	public void setLast(double last) {
		this.last = last;
	}

}
