package com.assettrader.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties( {"success", "message" })
public class CoinResultDTO<T>  {
	
	
	private CoinDTO[] result;

	public CoinDTO[] getResult() {
		return result;
	}

	public void setResult(CoinDTO[] result) {
		this.result = result;
	}
	
}
