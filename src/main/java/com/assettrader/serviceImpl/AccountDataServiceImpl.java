package com.assettrader.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assettrader.api.bittrex.model.accountapi.Balance;
import com.assettrader.dao.AccountDataDao;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.service.AccountDataService;
import com.assettrader.utils.BittrexKeyUtil;

@Service
public class AccountDataServiceImpl implements AccountDataService {

	@Autowired
	public AccountDataDao accountDataDao;
	
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
	public BittrexKeyUtil getApiKey(RWLoginDetail userDetail) {
		return accountDataDao.getApiKey(userDetail);
	}

	@Override
	public List<Balance> getAccountBalances(Long id) {
		return accountDataDao.getAccountBalances(id);
	}
	

}
