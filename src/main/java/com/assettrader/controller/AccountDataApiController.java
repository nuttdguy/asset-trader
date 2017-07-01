package com.assettrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assettrader.api.bittrex.BittrexClient;
import com.assettrader.api.bittrex.config.ApiCredentials;
import com.assettrader.api.bittrex.model.accountapi.Balance;
import com.assettrader.api.bittrex.model.accountapi.DepositAddress;
import com.assettrader.api.bittrex.model.accountapi.DepositHistoryEntry;
import com.assettrader.api.bittrex.model.accountapi.Order;
import com.assettrader.api.bittrex.model.accountapi.OrderHistoryEntry;
import com.assettrader.api.bittrex.model.accountapi.WithdrawalHistoryEntry;
import com.assettrader.api.bittrex.model.common.ApiResult;
import com.assettrader.service.DTO.AccountDataServiceDTO;
import com.assettrader.utils.BittrexKeyUtil;

import feign.Param;
import feign.RequestLine;

@RestController
public class AccountDataApiController {
	
	@Autowired
	private AccountDataServiceDTO accountDataServiceDTO;
	
	// IS WORKING
	@RequestMapping(value = "/account/balances", method = RequestMethod.GET)
	public ApiResult<List<Balance>> getBalances() {

		ApiResult<List<Balance>> balanceApiDTO = 
				initBittrexClient().getAccountApi().getBalances();
		
		// IS PERSISTING PROPERLY
		return accountDataServiceDTO.saveAllAccountBalancesDTO(balanceApiDTO);
	}
	
	
	// IS WORKING
	@RequestMapping(value = "/account/balances/{currency}", method = RequestMethod.GET)
	public ApiResult<Balance> getBalanceByCurrency(@PathVariable String currency) {
		
		ApiResult<Balance> balanceApiDTO = 
				initBittrexClient().getAccountApi().getBalance(currency);
		
		// IS PERSISTING PROPERLY
		return accountDataServiceDTO.saveAccountBalanceDTO(balanceApiDTO);
	}
	
	// IS WORKING 
	@RequestMapping(value = "/account/deposit/address/{currency}", method = RequestMethod.GET)
	public ApiResult<DepositAddress> getDepositAddressByCurrency(@PathVariable String currency) {
		
		ApiResult<DepositAddress> depositAddressDTO = 
				initBittrexClient().getAccountApi().getDepositAddress(currency);
		
		// IS PERSISTING PROPERLY
		return accountDataServiceDTO.saveDepositAddressDTO(depositAddressDTO);
	}
	
	// IS WORKING
	@RequestMapping(value = "/account/orderhistory/{market}", method = RequestMethod.GET )
	public ApiResult<List<OrderHistoryEntry>> getOrderHistory(@PathVariable String market) {
		
		ApiResult<List<OrderHistoryEntry>> orderHistoryDTO =
				initBittrexClient().getAccountApi().getOrderHistory(market);
		
		// IS PERSISTING PROPERLY
		return accountDataServiceDTO.saveAllOrderHistoryEntry(orderHistoryDTO, market);
	}

	// IS WORKING
	@RequestMapping(value = "/account/orderhistory", method = RequestMethod.GET )
	public ApiResult<List<OrderHistoryEntry>> getOrderHistory() {
		
		ApiResult<List<OrderHistoryEntry>> orderHistoryDTO =
				initBittrexClient().getAccountApi().getOrderHistory();
		
		// IS PERSISTING PROPERLY
		return accountDataServiceDTO.saveAllOrderHistoryEntry(orderHistoryDTO);
	}
	
	
	// IS WORKING
	@RequestMapping(value = "/account/withdrawhistory", method = RequestMethod.GET )
	public ApiResult<List<WithdrawalHistoryEntry>> getWithdrawalHistory() {
		
		ApiResult<List<WithdrawalHistoryEntry>> withdrawalHistoryDTO = 
				initBittrexClient().getAccountApi().getWithdrawalHistory();
		
		// IS PERSISTING PROPERLY
		return accountDataServiceDTO.saveAllWithdrawalHistory(withdrawalHistoryDTO);
	}
	
	// IS WORKING
	@RequestMapping(value = "/account/withdrawhistory/{currency}", method = RequestMethod.GET )
	public ApiResult<List<WithdrawalHistoryEntry>> getWithdrawalHistory(@PathVariable String currency) {
		
		ApiResult<List<WithdrawalHistoryEntry>> withdrawalHistoryDTO =
				initBittrexClient().getAccountApi().getWithdrawalHistory(currency);
		
		// IS PERSISTING PROPERLY
		return accountDataServiceDTO.saveAllWithdrawalHistory(withdrawalHistoryDTO);
	}
	
	@RequestMapping(value = "/account/deposithistory/{marketname}", method = RequestMethod.GET )
	public ApiResult<List<DepositHistoryEntry>> getDepositHistory(@PathVariable String marketname) {
		
		ApiResult<List<DepositHistoryEntry>> depositHistoryDTO =
				initBittrexClient().getAccountApi().getDepositHistory(marketname);
		
		//
		return accountDataServiceDTO.saveAllDepositHistory(depositHistoryDTO);
	}
	
	@RequestMapping(value = "/account/deposithistory", method = RequestMethod.GET )
	public ApiResult<List<DepositHistoryEntry>> getDepositHistory() {
		
		ApiResult<List<DepositHistoryEntry>> depositHistoryDTO =
				initBittrexClient().getAccountApi().getDepositHistory();
		
		return accountDataServiceDTO.saveAllDepositHistory(depositHistoryDTO);
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
