package com.assettrader.DTO;

public class TickerDTO {

	private double Bid;

	private double Ask;

	private double Last;

	public double getBid() {
		return Bid;
	}

	public void setBid(double bid) {
		Bid = bid;
	}

	public double getAsk() {
		return Ask;
	}

	public void setAsk(double ask) {
		Ask = ask;
	}

	public double getLast() {
		return Last;
	}

	public void setLast(double last) {
		Last = last;
	}

}
