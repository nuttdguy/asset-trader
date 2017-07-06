package com.assettrader.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.model.view.AccountInfoView;
import com.assettrader.utils.BittrexKeyUtil;


public interface AccountInfoService {

	
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
	
	List<AccountInfoView> getMarketInfo(String exchange);
	
	//============================================
	//=== VALIDATE
	//============================================

		
}
