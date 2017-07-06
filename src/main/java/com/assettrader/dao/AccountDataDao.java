package com.assettrader.dao;

import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.utils.BittrexKeyUtil;

public interface AccountDataDao {

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
	
	//============================================
	//=== VALIDATE
	//============================================

}
