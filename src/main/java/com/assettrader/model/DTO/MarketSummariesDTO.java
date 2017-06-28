package com.assettrader.model.DTO;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "success", "message", "result", "Notice" })
public class MarketSummariesDTO {

	private String MarketName;
	private Double High;
	private Double Low;
	private Double Volume;
	private Double Last;
	private Double BaseVolume;
	private Date TimeStamp;
	private Double Bid;
	private Double Ask;
	private int OpenBuyOrders;
	private int OpenSellOrders;
	private int PrevDay;
	private Date Created;

	public String getMarketName() {
		return MarketName;
	}

	public void setMarketName(String marketName) {
		MarketName = marketName;
	}

	public Double getHigh() {
		return High;
	}

	public void setHigh(Double high) {
		High = high;
	}

	public Double getLow() {
		return Low;
	}

	public void setLow(Double low) {
		Low = low;
	}

	public Double getVolume() {
		return Volume;
	}

	public void setVolume(Double volume) {
		Volume = volume;
	}

	public Double getLast() {
		return Last;
	}

	public void setLast(Double last) {
		Last = last;
	}

	public Double getBaseVolume() {
		return BaseVolume;
	}

	public void setBaseVolume(Double baseVolume) {
		BaseVolume = baseVolume;
	}

	public Date getTimeStamp() {
		return TimeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		TimeStamp = timeStamp;
	}

	public Double getBid() {
		return Bid;
	}

	public void setBid(Double bid) {
		Bid = bid;
	}

	public Double getAsk() {
		return Ask;
	}

	public void setAsk(Double ask) {
		Ask = ask;
	}

	public int getOpenBuyOrders() {
		return OpenBuyOrders;
	}

	public void setOpenBuyOrders(int openBuyOrders) {
		OpenBuyOrders = openBuyOrders;
	}

	public int getOpenSellOrders() {
		return OpenSellOrders;
	}

	public void setOpenSellOrders(int openSellOrders) {
		OpenSellOrders = openSellOrders;
	}

	public int getPrevDay() {
		return PrevDay;
	}

	public void setPrevDay(int prevDay) {
		PrevDay = prevDay;
	}

	public Date getCreated() {
		return Created;
	}

	public void setCreated(Date created) {
		Created = created;
	}

}
