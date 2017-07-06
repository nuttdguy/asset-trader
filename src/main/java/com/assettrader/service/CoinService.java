package com.assettrader.service;

import java.util.List;

import com.assettrader.model.coinmarket.Coin;

public interface CoinService {

	
	//============================================
	//== CREATE
	//============================================
	
	
	//============================================
	//=== UPDATE
	//============================================
	
	
	//============================================
	//=== DELETES
	//============================================
	

	//============================================
	//=== RETRIEVE
	//============================================
	
	public String getCoinLogo(String coinMarketName);
	public List<Coin> getAllCoinLogos();
	
}
