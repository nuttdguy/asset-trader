package com.assettrader.model.publicapi.DTO;

import java.util.Date;

public class MarketHistoryDTO {

	private int Id;
	private Date TimeStamp;
	private double Quantity;
	private double Price;
	private double Total;
	private String FillType;
	private String OrderType;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Date getTimeStamp() {
		return TimeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		TimeStamp = timeStamp;
	}

	public double getQuantity() {
		return Quantity;
	}

	public void setQuantity(double quantity) {
		Quantity = quantity;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public double getTotal() {
		return Total;
	}

	public void setTotal(double total) {
		Total = total;
	}

	public String getFillType() {
		return FillType;
	}

	public void setFillType(String fillType) {
		FillType = fillType;
	}

	public String getOrderType() {
		return OrderType;
	}

	public void setOrderType(String orderType) {
		OrderType = orderType;
	}

}
