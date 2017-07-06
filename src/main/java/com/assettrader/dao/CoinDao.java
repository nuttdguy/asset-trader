package com.assettrader.dao;

import java.util.List;

import com.assettrader.model.coinmarket.Coin;
import com.assettrader.model.view.AccountInfoView;


public interface CoinDao {


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
	
	public Long getCoinMarketId(String marketName);
	public String getCoinMarketName(String marketName);
	public String getMarketNameByCurrencyShortName(String currencyShortName);
	public String getCoinLogo(String coinMarketName);
	public List<Coin> getAllCoinLogos();
	
	//============================================
	//=== VALIDATE
	//============================================

}
