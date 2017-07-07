package com.assettrader.service;

import java.util.List;

import com.assettrader.api.bittrex.model.accountapi.Balance;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.utils.BittrexKeyUtil;


public interface AccountDataService {

	
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
	
	public BittrexKeyUtil getApiKey(RWLoginDetail userDetail);
	public List<Balance> getAccountBalances(Long id);
	
	//============================================
	//=== VALIDATE
	//============================================

}
