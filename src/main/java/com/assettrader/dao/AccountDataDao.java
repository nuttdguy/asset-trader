package com.assettrader.dao;

import java.util.List;

import com.assettrader.api.bittrex.model.accountapi.Balance;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.utils.BittrexKeyUtil;

public interface AccountDataDao {

	//============================================
	//== CREATE
	//============================================
	
	public void saveCurrentBalance(Double balance, Long userId);

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
