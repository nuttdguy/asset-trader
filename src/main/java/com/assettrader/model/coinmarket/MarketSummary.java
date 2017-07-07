package com.assettrader.model.coinmarket;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.type.TrueFalseType;

import com.assettrader.entities.ids.MarketSummaryId;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MARKET_SUMMARY")
@IdClass(MarketSummaryId.class)
public class MarketSummary {

	@Column(name = "MARKET_SUMMARY_ID", columnDefinition="SERIAL")
	@GeneratedValue(strategy = GenerationType.TABLE )
	@JsonIgnore
	private long id;

	@Id
	@Column(name = "MARKET_NAME_SUMMARY")
	private String marketName;

	@Column(name = "HIGH")
	private double high;

	@Column(name = "LOW")
	private double low;
	
	@Column(name = "BASE_VOLUME")
	private double baseVolume;

	@Column(name = "VOLUME")
	private double volume;

	@Id
	@Column(name = "TIME_STAMP")
	private Date timeStamp;

	@Column(name = "BID")
	private double bid;

	@Column(name = "ASK")
	private double ask;

	@Column(name = "OPEN_BUY_ORDERS")
	private int openBuyOrders;

	@Column(name = "OPEN_SELL_ORDERS")
	private int openSellOrders;

	@Column(name = "PREV_DAY")
	private int prevDay;

	@Column(name = "CREATED")
	private Date created;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "MARKET_NAME", referencedColumnName="MARKET_NAME", insertable=true, updatable=false),
		@JoinColumn(name = "EXCHANGE_NAME", referencedColumnName="EXCHANGE_NAME", insertable=true, updatable=false) })
	private Coin coin;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
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

	public double getBaseVolume() {
		return baseVolume;
	}

	public void setBaseVolume(double baseVolume) {
		this.baseVolume = baseVolume;
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

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

}
