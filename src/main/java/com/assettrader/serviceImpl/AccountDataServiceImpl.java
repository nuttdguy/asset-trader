package com.assettrader.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assettrader.api.bittrex.model.accountapi.Balance;
import com.assettrader.api.bittrex.model.accountapi.DepositHistoryEntry;
import com.assettrader.api.bittrex.model.accountapi.WithdrawalHistoryEntry;
import com.assettrader.api.bittrex.model.common.ApiResult;
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
	
	@Override
	public void saveCurrentBalance(Double btcprice, Double balance, Long userId) {
		accountDataDao.saveCurrentBalance(btcprice, balance, userId);		
	}
	
	@Override
	public void saveDepositHistory(ApiResult<List<DepositHistoryEntry>> depositHistory, Long userId) {
		accountDataDao.saveDepositHistory(depositHistory, userId);		
	}

	@Override
	public void saveWithdrawalHistory(ApiResult<List<WithdrawalHistoryEntry>> withdrawalHistory, Long userId) {
		accountDataDao.saveWithdrawalHistory(withdrawalHistory, userId);	
	}

	@Override
	public boolean saveReturnHistory(Double totalDeposit, Double totalWithdrawal, Double totalBalance, Double btcprice, Long userId) {
		return accountDataDao.saveReturnHistory(totalDeposit, totalWithdrawal, totalBalance, btcprice, userId);
		
	}
	
	
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
	public BittrexKeyUtil getApiKey(Long userId) {
		return accountDataDao.getApiKey(userId);
	}
	
	@Override
	public List<Balance> getAccountBalances(Long id) {
		return accountDataDao.getAccountBalances(id);
	}
	

}
