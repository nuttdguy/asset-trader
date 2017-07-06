package com.assettrader.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.assettrader.model.UserProfile;
import com.assettrader.model.rest.RWApiCredential;
import com.assettrader.model.rest.RWFavorite;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.model.view.AccountInfoView;

@Repository
@Transactional
public interface AccountInfoDao {

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
	
	public List<AccountInfoView> getMarketInfoView(String exchange);
	
	//============================================
	//=== VALIDATE
	//============================================

	
}
