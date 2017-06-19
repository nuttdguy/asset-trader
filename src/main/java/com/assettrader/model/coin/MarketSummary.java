package com.assettrader.model.coin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name = "MARKET_SUMMARY")
public class MarketSummary {

	@Id
	@Column( name = "MARKET_SUMMARY_ID")
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column( name = "HIGH")
	private double high;
	
	@Column( name = "LOW")
	private double low;
	
	@Column( name = "VOLUME")
	private double volume;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column( name = "TIME_STAMP")
	private Date timeStamp;
	
	@Column( name = "OPEN_BUY_ORDERS")
	private int openBuyOrders;
	
	@Column( name = "OPEN_SELL_ORDERS")
	private int openSellOrders;
	
	@Column( name = "PREV_DAY")
	private int prevDay;
	
	@Column( name = "CREATED")
	private Date created;
	
	@Column( name = "DISPLAY_MARKET_NAME")
	private String displayMarketName;
	
	@ManyToOne
	@JoinColumn( name = "COIN_ID")
	private Coin coin;

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

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getDisplayMarketName() {
		return displayMarketName;
	}

	public void setDisplayMarketName(String displayMarketName) {
		this.displayMarketName = displayMarketName;
	}

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

}
