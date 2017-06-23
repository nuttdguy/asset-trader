package com.assettrader.model.coin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "TICKER" )
public class Ticker {

	@Id
	@Column( name = "TICKER_ID" )
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column( name = "BID" )
	private double bid;
	
	@Column( name = "ASK" )
	private double ask;
	
	@Column( name = "LAST" )
	private double last;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "COIN_ID"),
		@JoinColumn(name = "MARKET_NAME") })
	private Coin coin;

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

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}
	
	

}
