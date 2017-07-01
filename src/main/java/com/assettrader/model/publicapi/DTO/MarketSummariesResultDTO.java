package com.assettrader.model.publicapi.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "success", "message" })
public class MarketSummariesResultDTO {

	private MarketSummariesDTO[] result;

	public MarketSummariesDTO[] getResult() {
		return result;
	}

	public void setResult(MarketSummariesDTO[] result) {
		this.result = result;
	}
	
}
