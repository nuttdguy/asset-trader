package com.assettrader.DTO;

import java.util.Date;

public class BuyDTO {

	private double Quantity;

	private double Rate;
	
	private Date date;

	public double getQuantity() {
		return Quantity;
	}

	public void setQuantity(double quantity) {
		Quantity = quantity;
	}

	public double getRate() {
		return Rate;
	}

	public void setRate(double rate) {
		Rate = rate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
