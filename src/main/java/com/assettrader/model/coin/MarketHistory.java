package com.assettrader.model.coin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "MARKET_HISTORY")
public class MarketHistory {

	@Id
	@Column( name = "MARKET_HISTORY_ID")
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column( name = "PRICE")
	private double price;
	
	@Column( name = "TOTAL")
	private double total;
	
	@Column( name = "FILL_TYPE")
	private String fillType;
	
	@Column(name = "ORDER_TYPE")
	private String orderType;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "COIN_ID", insertable = false, updatable = true),
		@JoinColumn(name = "MARKET_NAME", insertable = false, updatable = true) })
	private Coin coin;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getFillType() {
		return fillType;
	}

	public void setFillType(String fillType) {
		this.fillType = fillType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

	
}
