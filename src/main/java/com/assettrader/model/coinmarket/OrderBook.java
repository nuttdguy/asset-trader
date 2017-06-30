package com.assettrader.model.coinmarket;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.assettrader.model.utils.OrderType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ORDER_BOOK")
public class OrderBook {

	@Id
	@Column(name = "ORDER_BOOK_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private long id;

	@Column(name = "QUANTITY")
	private double quantity;

	@Column(name = "RATE")
	private double rate;

	@Column(name = "ORDER_BOOK_DATETIME")
	private Date orderBookDateTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "ORDER_TYPE")
	private OrderType orderType;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "MARKET_NAME", referencedColumnName="MARKET_NAME", insertable=true, updatable=false),
		@JoinColumn(name = "EXCHANGE", referencedColumnName="EXCHANGE", insertable=true, updatable=false) })
	private Coin coin;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public Date getOrderBookDateTime() {
		return orderBookDateTime;
	}

	public void setOrderBookDateTime(Date orderBookDateTime) {
		this.orderBookDateTime = orderBookDateTime;
	}

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

}
