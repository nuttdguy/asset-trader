package com.assettrader.model.coin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.assettrader.entities.ids.MarketHistoryId;
import com.assettrader.model.utils.OrderType;

@Entity
@Table( name = "MARKET_HISTORY")
@IdClass(MarketHistoryId.class)
public class MarketHistory {

	@GeneratedValue( strategy = GenerationType.TABLE )
	@Column( name = "MARKET_HISTORY_ID", columnDefinition="serial")
	private long id;
	
	@Id
	@Column( name = "ORDER_ID")
	private long orderId;
	
	@Column( name = "PRICE")
	private double price;
	
	@Column( name = "TOTAL")
	private double total;
	
	@Column( name = "FILL_TYPE")
	private String fillType;
	
	@Column( name = "QUANTITY")
	private double quantity;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ORDER_TYPE")
	private OrderType orderType;
	
	@Id
	@Column( name = "TIME_STAMP")
	private Date timeStamp;

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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
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

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Coin getCoin() {
		return coin;
	}

	public void setCoin(Coin coin) {
		this.coin = coin;
	}

	
}
