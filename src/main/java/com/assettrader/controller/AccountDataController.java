package com.assettrader.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.assettrader.api.bittrex.model.publicapi.MarketSummary;
import com.assettrader.model.coinmarket.Coin;
import com.assettrader.model.coinmarket.Ticker;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.model.rest.ResWrapper;
import com.assettrader.model.utils.WalletOrigin;
import com.assettrader.model.view.AccountBalanceView;
import com.assettrader.service.AccountDataService;
import com.assettrader.service.CoinService;
import com.assettrader.service.DTO.AccountDataServiceDTO;
import com.assettrader.service.DTO.CoinServiceDTO;
import com.assettrader.utils.BittrexKeyUtil;


@CrossOrigin
@RestController
@RequestMapping(value ="/account")
public class AccountDataController {
	
	@Autowired
	private AccountDataServiceDTO accountDataServiceDTO;
	
	@Autowired
	private AccountDataService accountDataService;
	
	@Autowired
	private CoinService coinService;
	
	@Autowired
	private CoinServiceDTO coinServiceDTO;
	
	//=======================================
	// GENERAL CONTROLLER IMPLEMENTATIONS
	//=======================================
	
	// TODO -- REDO, ADD AND/OR APPEND EXCHANGE_SUFFIX TO CREATE UNIQUE PK FOR DATABASE
	// ** PRE-CONDITIONS (PC) - USER MUST HAVE AN ACCOUNT, API KEY VALID
	// ** ALL ADDRESSES   
	
	@RequestMapping(value ={ "/balances" }, method = RequestMethod.POST)
	public ApiResult<List<Balance>> getBalances(@RequestBody RWLoginDetail userDetail) {
		
		Long userId = userDetail.getId();
		BittrexKeyUtil keys = accountDataService.getApiKey(userDetail);
		
		ApiResult<List<Balance>> balanceApiDTO = 
				initBittrexClient(keys).getAccountApi().getBalances();
		
		// GET DEPOSIT ADDRESS FOR EACH ACTIVE COIN
		for (Balance deposit: balanceApiDTO.getResult()) {
			String currency = deposit.getCurrency();
			ApiResult<DepositAddress> depositAddress = initBittrexClient(keys).getAccountApi().getDepositAddress(currency);
			balanceApiDTO.getResult().get(0).setCryptoAddress(depositAddress.getResult().getAddress());
		}
		
		return accountDataServiceDTO.saveAllAccountBalancesDTO(addLogoUrlToBalanceDTO(balanceApiDTO), userId);
	}
	
	
	@RequestMapping(value = "/orderhistory", method = RequestMethod.POST )
	public ApiResult<List<OrderHistoryEntry>> getOrderHistory(@RequestBody RWLoginDetail userDetail) {
		
		BittrexKeyUtil keys = accountDataService.getApiKey(userDetail);
		
		ApiResult<List<OrderHistoryEntry>> orderHistoryDTO =
				initBittrexClient(keys).getAccountApi().getOrderHistory();
		
		return accountDataServiceDTO.saveAllOrderHistoryEntry(addLogoUrlToOrderHistoryDTO(orderHistoryDTO));
	}
	
	
	@RequestMapping(value = "/withdrawalhistory", method = RequestMethod.POST )
	public ApiResult<List<WithdrawalHistoryEntry>> getWithdrawalHistory(@RequestBody RWLoginDetail userDetail) {
		
		BittrexKeyUtil keys = accountDataService.getApiKey(userDetail);
		
		ApiResult<List<WithdrawalHistoryEntry>> withdrawalHistoryDTO = 
				initBittrexClient(keys).getAccountApi().getWithdrawalHistory();
		
		return accountDataServiceDTO.saveAllWithdrawalHistory(addLogoUrlToWithdrawalDTO(withdrawalHistoryDTO));
	}
	
	
	@RequestMapping(value = "/deposithistory", method = RequestMethod.POST )
	public ApiResult<List<DepositHistoryEntry>> getDepositHistory(@RequestBody RWLoginDetail userDetail) {
		
		BittrexKeyUtil keys = accountDataService.getApiKey(userDetail);
		
		ApiResult<List<DepositHistoryEntry>> depositHistoryDTO =
				initBittrexClient(keys).getAccountApi().getDepositHistory();
		
		return accountDataServiceDTO.saveAllDepositHistory(addLogoUrlsToDTOResult(depositHistoryDTO));
	}
	
	
	//=======================================
	// CUSTOM-SPECIFIC CONTROLLER IMPLEMENTATIONS
	//=======================================
	
	
	
	@RequestMapping(value = {"/balances/{btcprice}&{userId}"}, method = RequestMethod.GET)
	public ResWrapper<AccountBalanceView> getPortfolioValue(@PathVariable Double btcprice, @PathVariable Long userId) {
		
		// GET CURRENT BALANCE OF ALL COINS
		List<Balance> balances = accountDataService.getAccountBalances(userId);

		// GET CURRENT ASK FROM TICKER; USE MARKET_NAME - WILL RETURN A SINGLE VALUE
		List<Ticker> tickerList = new ArrayList<>();
		Double portfolioValue = 0.00;		
		String market = "BTC-";		
		String exchangeName =  WalletOrigin.BITTREX.name();
		
		for (Balance currencyName : balances) {		
			String marketName = market + currencyName.getCurrency();
			if (!marketName.equals("BTC-BTC") && currencyName.getCurrency() != null ) {
				Ticker tickerPrice = coinServiceDTO.getTicker(marketName, exchangeName );
				portfolioValue += multiplyValues(currencyName.getBalance(), tickerPrice.getAsk(), btcprice );
				tickerList.add(tickerPrice);
			}

		}
		
		
		ResWrapper<AccountBalanceView> res = new ResWrapper<>();
		AccountBalanceView balanceView = new AccountBalanceView();
		balanceView.setPortfolioValue(portfolioValue);
		
		res.setSuccess(true);
		res.setResult(balanceView);
		res.setMessage("Portfolio was calculated!");
		
		// PERSIST RESULT OF BALANCE TO EACH COIN TO DATABASE FOR GRAPHING INTENT
		accountDataService.saveCurrentBalance(btcprice, portfolioValue, userId);
		
		
		// RETURN VALUE OF PORTFOLIO DISPLAY
		return res;
	}
	
	
	
	@RequestMapping(value = {"/balances/profit/{btcprice}&{userId}"}, method = RequestMethod.GET)
	public ResWrapper<AccountBalanceView> getReturnOnInvestment(@PathVariable Double btcprice, @PathVariable Long userId) {
		
		// USE CLIENT TO GENERATE GET KEYS, GENERATE
		BittrexKeyUtil keys = accountDataService.getApiKey(userId);
		
		// GET CURRENT DATA FROM API
		ApiResult<List<DepositHistoryEntry>> depositHistory =
				initBittrexClient(keys).getAccountApi().getDepositHistory();
		
		ApiResult<List<WithdrawalHistoryEntry>> withdrawalHistory =
				initBittrexClient(keys).getAccountApi().getWithdrawalHistory();
		
		ApiResult<List<Balance>> currentBalance =
				initBittrexClient(keys).getAccountApi().getBalances();
		
		ApiResult<List<MarketSummary>> marketSummary =
				initBittrexClient(keys).getPublicApi().getMarketSummaries();
		
		Double totalDeposit = 0.00;
		Double totalWithdrawal = 0.00;
		Double totalBalance = 0.00;
		// CALCULATE RESULT, WILL REQUIRE HISTORICAL BTC DATA 
		for (DepositHistoryEntry dEntry : depositHistory.getResult()) {
			if (dEntry.getAmount() > 0 && dEntry.getCurrency().equals("BTC")) {
				totalDeposit += dEntry.getAmount();
			}
		}
		
		for (WithdrawalHistoryEntry wEntry : withdrawalHistory.getResult()) {
			if (wEntry.getAmount() > 0 && wEntry.getCurrency().equals("BTC")) {
				totalWithdrawal += wEntry.getAmount();
			}
		}
		
		List<MarketSummary> mSummary = marketSummary.getResult();
		
		START: for (Balance bEntry : currentBalance.getResult()) {
			if (bEntry.getBalance() > 0 ) {
				
				String currency = bEntry.getCurrency();
				for (MarketSummary ms : mSummary) {
					if (ms.getMarketName().substring(4).equals( currency )) {
						totalBalance += ( bEntry.getBalance() * ms.getAsk() ) ;
						continue START;
					}
				}
			}
		}
		
		Double returnPercent = 0.00;
		returnPercent = (( totalDeposit - totalWithdrawal ) + totalBalance) / totalDeposit ;
		
		// PERSIST CURRENT DATA TO DB FOR HISTORY
		// TODO -- CREATE TABLES TO ASSOCIATE HISTORY WITH SPECIFIC USER
		// accountDataService.saveDepositHistory(depositHistory, userId);
		// accountDataService.saveWithdrawalHistory(withdrawalHistory, userId);
		accountDataService.saveReturnHistory(totalDeposit, totalWithdrawal, totalBalance, btcprice, userId);
		
		// Create ResWrapper, return returnPercent
		ResWrapper<AccountBalanceView> response = new ResWrapper<>();
		AccountBalanceView balanceView = new AccountBalanceView();
		balanceView.setReturnPercent(returnPercent);
		response.setResult(balanceView);
		response.setMessage("RETURN PERCENT CALCULATED");
		response.setStatus(HttpStatus.OK);
		
		return response;
	}
	
	
	//========================================================================
	//   PRIVATE METHOD FOR ADDING LOGO URLS 
	//========================================================================
	
	private Double multiplyValues(Double balance, double ask, Double btcprice) {
		return balance * ask * btcprice;
	}

	
	private ApiResult<List<Balance>> addLogoUrlToBalanceDTO(ApiResult<List<Balance>> balanceApiDTO) {
		List<Coin> logoList = coinService.getAllCoinLogos();
		
		// TODO - MODIFY ALGORITHM, GET FIRST LETTER OF COIN, SUB-DIVIDE INTO SMALLER UNITS 
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
				 
				String name = dtoList.get(i).getWalletOrderOrigin().name().substring(4).toUpperCase();
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
	
	private BittrexClient initBittrexClient(BittrexKeyUtil keys) {
		
		ApiCredentials credentials = new ApiCredentials(
				keys.getKEY(), keys.getSECRET());
		
		return new BittrexClient(credentials);
	}
}
