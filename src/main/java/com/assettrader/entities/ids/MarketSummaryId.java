package com.assettrader.entities.ids;

import java.io.Serializable;
import java.sql.Date;

public class MarketSummaryId implements Serializable {

	private static final long serialVersionUID = -5612327240747157302L;

	private Date timeStamp;
	
	private String marketName;

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	
	
	
}
