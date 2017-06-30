package com.assettrader.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assettrader.api.bittrex.BittrexClient;
import com.assettrader.api.bittrex.config.ApiCredentials;
import com.assettrader.api.bittrex.model.accountapi.Balance;
import com.assettrader.api.bittrex.model.accountapi.DepositAddress;
import com.assettrader.api.bittrex.model.accountapi.DepositHistoryEntry;
import com.assettrader.api.bittrex.model.accountapi.OrderHistoryEntry;
import com.assettrader.api.bittrex.model.accountapi.WithdrawalHistoryEntry;
import com.assettrader.api.bittrex.model.common.ApiResult;
import com.assettrader.utils.BittrexKeyUtil;

@RestController
public class AccountDataController {
	
	
	// IS WORKING
	@RequestMapping(value = "/account/balances", method = RequestMethod.GET)
	public ApiResult<List<Balance>> getBalances() {

		ApiResult<List<Balance>> balances = 
				initBittrexClient().getAccountApi().getBalances();
		
		// TODO - ADD RESULT TO DATABASE
		return balances;
	}
	
	
	// IS WORKING
	@RequestMapping(value = "/account/balances/{currency}", method = RequestMethod.GET)
	public ApiResult<Balance> getBalanceByCurrency(@PathVariable String currency) {
		

		ApiResult<Balance> balance = 
				initBittrexClient().getAccountApi().getBalance(currency);
		
		// TODO - ADD RESULT TO DATABASE
		return balance;
	}
	
	// IS WORKING
	@RequestMapping(value = "/account/deposit/address/{currency}", method = RequestMethod.GET)
	public ApiResult<DepositAddress> getDepositAddressByCurrency(@PathVariable String currency) {
		
		ApiResult<DepositAddress> depositAddress = 
				initBittrexClient().getAccountApi().getDepositAddress(currency);
		
		// TODO -- PERSIST TO DATABASE
		return depositAddress;
	}
	
	// IS WORKING
	@RequestMapping(value = "/account/orderhistory/{marketname}", method = RequestMethod.GET )
	public ApiResult<List<OrderHistoryEntry>> getOrderHistory(@PathVariable String marketname) {
		
		ApiResult<List<OrderHistoryEntry>> orderHistory =
				initBittrexClient().getAccountApi().getOrderHistory(marketname);
		
		// TODO -- PERSIST TO DATABASE
		return orderHistory;
	}

	// IS WORKING
	@RequestMapping(value = "/account/orderhistory", method = RequestMethod.GET )
	public ApiResult<List<OrderHistoryEntry>> getOrderHistory() {
		
		ApiResult<List<OrderHistoryEntry>> orderHistory =
				initBittrexClient().getAccountApi().getOrderHistory();
		
		// TODO -- PERSIST TO DATABASE
		return orderHistory;
	}
	
	// IS WORKING
	@RequestMapping(value = "/account/withdrawhistory", method = RequestMethod.GET )
	public ApiResult<List<WithdrawalHistoryEntry>> getWithdrawalHistory() {
		
		ApiResult<List<WithdrawalHistoryEntry>> withdrawHistory = 
				initBittrexClient().getAccountApi().getWithdrawalHistory();
		
		// TODO -- PERSIST TO DATABASE
		return withdrawHistory;
	}
	
	// IS WORKING
	@RequestMapping(value = "/account/withdrawhistory/{marketname}", method = RequestMethod.GET )
	public ApiResult<List<WithdrawalHistoryEntry>> getWithdrawalHistory(@PathVariable String marketname) {
		
		ApiResult<List<WithdrawalHistoryEntry>> withdrawHistory =
				initBittrexClient().getAccountApi().getWithdrawalHistory(marketname);
		
		// TODO -- PERSIST TO DATABASE
		return withdrawHistory;
	}
	
	@RequestMapping(value = "/account/deposithistory/{marketname}", method = RequestMethod.GET )
	public ApiResult<List<DepositHistoryEntry>> getDepositHistory(@PathVariable String marketname) {
		
		ApiResult<List<DepositHistoryEntry>> depositHistory =
				initBittrexClient().getAccountApi().getDepositHistory(marketname);
		
		// TODO -- PERSIST TO DATABASE
		return depositHistory;
	}
	
	@RequestMapping(value = "/account/deposit", method = RequestMethod.GET )
	public ApiResult<List<DepositHistoryEntry>> getDepositHistory() {
		
		ApiResult<List<DepositHistoryEntry>> depositHistory =
				initBittrexClient().getAccountApi().getDepositHistory();
		
		// TODO -- PERSIST TO DATABASE
		return depositHistory;
	}
	
	//========================================================================
	//   PRIVATE METHOD FOR INITIALIZING KEY AND BITTREX CLIENT FOR ACCOUNT
	//========================================================================
	
	private BittrexClient initBittrexClient() {
		
		ApiCredentials credentials = new ApiCredentials(
				BittrexKeyUtil.getKEY(), BittrexKeyUtil.getSECRET());
		
		return new BittrexClient(credentials);
	}
}
