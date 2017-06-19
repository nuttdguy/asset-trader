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

@Entity
@Table( name = "ORDER_BOOK" )
public class OrderBook {

	@Id
	@Column( name = "ORDER_BOOK_ID")
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column( name = "QUANTITY" )
	private int quantity;
	
	@Column( name = "RATE" )
	private double rate;
	
	@Column( name = "ORDER_BOOK_DATETIME" )
	public Date orderBookDateTime;
	
	@ManyToOne
	@JoinColumn( name = "COIN_ID" )
	private Coin coin;
	
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
