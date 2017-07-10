package com.assettrader.service;

import java.util.List;

import com.assettrader.api.bittrex.model.accountapi.Balance;
import com.assettrader.api.bittrex.model.accountapi.DepositHistoryEntry;
import com.assettrader.api.bittrex.model.accountapi.WithdrawalHistoryEntry;
import com.assettrader.api.bittrex.model.common.ApiResult;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.utils.BittrexKeyUtil;


public interface AccountDataService {

	
	//============================================
	//== CREATE
	//============================================
	
	public void saveCurrentBalance(Double btcprice, Double balance, Long userId);
	public void saveDepositHistory(ApiResult<List<DepositHistoryEntry>> depositHistory, Long userId);
	public void saveWithdrawalHistory(ApiResult<List<WithdrawalHistoryEntry>> withdrawalHistory, Long userId);
	public boolean saveReturnHistory(Double totalDeposit, Double totalWithdrawal, Double totalBalance, Double btcprice, Long userId);

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
	public BittrexKeyUtil getApiKey(Long userId);
	
	public List<Balance> getAccountBalances(Long id);
	
	//============================================
	//=== VALIDATE
	//============================================

}
