package com.assettrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.assettrader.model.coinmarket.Coin;
import com.assettrader.service.CoinService;
import com.assettrader.service.DTO.AccountDataServiceDTO;
import com.assettrader.utils.BittrexKeyUtil;

@CrossOrigin
@RestController
public class AccountDataApiController {
	
	@Autowired
	private AccountDataServiceDTO accountDataServiceDTO;
	
	@Autowired
	private CoinService coinService;
	
	// IS WORKING -- IS SHOWING LOGOS
	@RequestMapping(value = "/account/balances", method = RequestMethod.GET)
	public ApiResult<List<Balance>> getBalances() {

		ApiResult<List<Balance>> balanceApiDTO = 
				initBittrexClient().getAccountApi().getBalances();
		
		return accountDataServiceDTO.saveAllAccountBalancesDTO(addLogoUrlToBalanceDTO(balanceApiDTO));
	}
	
	// IS WORKING
	@RequestMapping(value = "/account/balances/{currency}", method = RequestMethod.GET)
	public ApiResult<Balance> getBalanceByCurrency(@PathVariable String currency) {
		
		ApiResult<Balance> balanceApiDTO = 
				initBittrexClient().getAccountApi().getBalance(currency);
		
		// TODO -- ADD LOGIC FOR LOGO 
		
		return accountDataServiceDTO.saveAccountBalanceDTO(balanceApiDTO);
	}
	
	// IS WORKING 
	@RequestMapping(value = "/account/deposit/address/{currency}", method = RequestMethod.GET)
	public ApiResult<DepositAddress> getDepositAddressByCurrency(@PathVariable String currency) {
		
		ApiResult<DepositAddress> depositAddressDTO = 
				initBittrexClient().getAccountApi().getDepositAddress(currency);
		
		// TODO -- ENABLE END-POINT FEATURE 
		
		return accountDataServiceDTO.saveDepositAddressDTO(depositAddressDTO);
	}
	
	// IS WORKING -- LOGO IS SHOWING
	@RequestMapping(value = "/account/orderhistory/{market}", method = RequestMethod.GET )
	public ApiResult<List<OrderHistoryEntry>> getOrderHistory(@PathVariable String market) {
		
		ApiResult<List<OrderHistoryEntry>> orderHistoryDTO =
				initBittrexClient().getAccountApi().getOrderHistory(market);
		
		return accountDataServiceDTO.saveAllOrderHistoryEntry(addLogoUrlToOrderHistoryDTO(orderHistoryDTO), market);
	}

	// IS WORKING -- LOGO IS WORKING
	@RequestMapping(value = "/account/orderhistory", method = RequestMethod.GET )
	public ApiResult<List<OrderHistoryEntry>> getOrderHistory() {
		
		ApiResult<List<OrderHistoryEntry>> orderHistoryDTO =
				initBittrexClient().getAccountApi().getOrderHistory();
		
		return accountDataServiceDTO.saveAllOrderHistoryEntry(addLogoUrlToOrderHistoryDTO(orderHistoryDTO));
	}
	
	
	// IS WORKING // TODO -- TEST LOGO
	@RequestMapping(value = "/account/withdrawalhistory", method = RequestMethod.GET )
	public ApiResult<List<WithdrawalHistoryEntry>> getWithdrawalHistory() {
		
		ApiResult<List<WithdrawalHistoryEntry>> withdrawalHistoryDTO = 
				initBittrexClient().getAccountApi().getWithdrawalHistory();
		
		return accountDataServiceDTO.saveAllWithdrawalHistory(addLogoUrlToWithdrawalDTO(withdrawalHistoryDTO));
	}
	
	// IS WORKING // TODO -- TEST LOGO
	@RequestMapping(value = "/account/withdrawalhistory/{currency}", method = RequestMethod.GET )
	public ApiResult<List<WithdrawalHistoryEntry>> getWithdrawalHistory(@PathVariable String currency) {
		
		ApiResult<List<WithdrawalHistoryEntry>> withdrawalHistoryDTO =
				initBittrexClient().getAccountApi().getWithdrawalHistory(currency);
		
		return accountDataServiceDTO.saveAllWithdrawalHistory(addLogoUrlToWithdrawalDTO(withdrawalHistoryDTO));
	}

	
	// IS WORKING // TODO -- TEST LOGO
	@RequestMapping(value = "/account/deposithistory/{marketname}", method = RequestMethod.GET )
	public ApiResult<List<DepositHistoryEntry>> getDepositHistory(@PathVariable String marketname) {
		
		ApiResult<List<DepositHistoryEntry>> depositHistoryDTO =
				initBittrexClient().getAccountApi().getDepositHistory(marketname);

		return accountDataServiceDTO.saveAllDepositHistory(addLogoUrlsToDTOResult(depositHistoryDTO));
	}
	
	// IS WORKING // TODO -- TEST LOGO
	@RequestMapping(value = "/account/deposithistory", method = RequestMethod.GET )
	public ApiResult<List<DepositHistoryEntry>> getDepositHistory() {
		
		ApiResult<List<DepositHistoryEntry>> depositHistoryDTO =
				initBittrexClient().getAccountApi().getDepositHistory();
		
		return accountDataServiceDTO.saveAllDepositHistory(addLogoUrlsToDTOResult(depositHistoryDTO));
	}

	
	//========================================================================
	//   PRIVATE METHOD FOR ADDING LOGO URLS 
	//========================================================================
	
	private ApiResult<List<Balance>> addLogoUrlToBalanceDTO(ApiResult<List<Balance>> balanceApiDTO) {
		List<Coin> logoList = coinService.getAllCoinLogos();
		
		List<Balance> dtoList = balanceApiDTO.getResult();
		START: for (int i = 0; i < dtoList.size(); i++ ) {
			 for (Coin logo: logoList) {
				 
				String name = dtoList.get(i).getCurrency().toUpperCase();
				if (name.equals(logo.getMarketCurrency())) {
					dtoList.get(i).setLogoUrl(logo.getLogoUrl());
					continue START;
				}
			}
		}
		return balanceApiDTO;
	}
	
	
	private ApiResult<List<OrderHistoryEntry>> addLogoUrlToOrderHistoryDTO(ApiResult<List<OrderHistoryEntry>> orderHistoryDTO) {
		List<Coin> logoList = coinService.getAllCoinLogos();
		
		List<OrderHistoryEntry> dtoList = orderHistoryDTO.getResult();
		START: for (int i = 0; i < dtoList.size(); i++ ) {
			 for (Coin logo: logoList) {
				 
				String name = dtoList.get(i).getExchange().substring(4).toUpperCase();
				if (name.equals(logo.getMarketCurrency())) {
					dtoList.get(i).setLogoUrl(logo.getLogoUrl());
					continue START;
				}
			}
		}
		return orderHistoryDTO;
	}
	
	
	private ApiResult<List<WithdrawalHistoryEntry>> addLogoUrlToWithdrawalDTO(ApiResult<List<WithdrawalHistoryEntry>> withdrawalHistoryDTO) {
		List<Coin> logoList = coinService.getAllCoinLogos();
		
		List<WithdrawalHistoryEntry> dtoList = withdrawalHistoryDTO.getResult();
		START: for (int i = 0; i < dtoList.size(); i++ ) {
			 for (Coin logo: logoList) {
				 
				String name = dtoList.get(i).getCurrency().toUpperCase();
				if (name.equals(logo.getMarketCurrency())) {
					dtoList.get(i).setLogoUrl(logo.getLogoUrl());
					continue START;
				}
			}
		}
		return withdrawalHistoryDTO;
	}
	
	
	private ApiResult<List<DepositHistoryEntry>>  addLogoUrlsToDTOResult(ApiResult<List<DepositHistoryEntry>> depositHistoryDTO) {
		List<Coin> logoList = coinService.getAllCoinLogos();		
		List<DepositHistoryEntry> dtoList = depositHistoryDTO.getResult();
		START: for (int i = 0; i < dtoList.size(); i++ ) {
			 for (Coin logo: logoList) {
				 
				String name = dtoList.get(i).getCurrency().toUpperCase();
				if (name.equals(logo.getMarketCurrency())) {
					dtoList.get(i).setLogoUrl(logo.getLogoUrl());
					continue START;
				}
			}
		}
		return depositHistoryDTO;
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
