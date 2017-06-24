package com.assettrader.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties( {"success", "message" })
public class CurrencyResultDTO {

	private CurrencyDTO[] result;

	public CurrencyDTO[] getResult() {
		return result;
	}

	public void setResult(CurrencyDTO[] result) {
		this.result = result;
	}

}
