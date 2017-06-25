package com.assettrader.entities.ids;

import java.io.Serializable;
import java.util.Date;

public class MarketHistoryId implements Serializable {

	private static final long serialVersionUID = 1L;

	private long orderId;
	private Date timeStamp;

	public MarketHistoryId() {}
	
	public MarketHistoryId(long orderId, Date timeStamp) {
		this.orderId = orderId;
		this.timeStamp = timeStamp;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
