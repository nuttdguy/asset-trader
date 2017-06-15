package com.assettrader.model.coin;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MarketSummary {

	@Id
	private long id;
	private double high;
	private double low;
	private double volume;
	private LocalDate timeStamp;
	private int openBuyOrders;
	private int openSellOrders;
	private int prevDay;
	private LocalDate created;
	private String displayMarketName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getOpenBuyOrders() {
		return openBuyOrders;
	}

	public void setOpenBuyOrders(int openBuyOrders) {
		this.openBuyOrders = openBuyOrders;
	}

	public int getOpenSellOrders() {
		return openSellOrders;
	}

	public void setOpenSellOrders(int openSellOrders) {
		this.openSellOrders = openSellOrders;
	}

	public int getPrevDay() {
		return prevDay;
	}

	public void setPrevDay(int prevDay) {
		this.prevDay = prevDay;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public String getDisplayMarketName() {
		return displayMarketName;
	}

	public void setDisplayMarketName(String displayMarketName) {
		this.displayMarketName = displayMarketName;
	}

}
