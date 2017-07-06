package com.assettrader.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.assettrader.dao.CoinDao;
import com.assettrader.model.coinmarket.Coin;
import com.assettrader.service.CoinService;

public class CoinServiceImpl implements CoinService {
	
	@Autowired
	private CoinDao coinDao;

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

	@Override 
	public String getCoinLogo(String coinMarketName) {
		return coinDao.getCoinLogo(coinMarketName);
	}

	@Override 
	public List<Coin> getAllCoinLogos() {
		return coinDao.getAllCoinLogos();
	}
	




}
