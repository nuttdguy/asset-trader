package com.assettrader.service.DTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assettrader.dao.DTO.CoinDaoDTO;
import com.assettrader.model.coinmarket.Coin;
import com.assettrader.model.coinmarket.Currency;
import com.assettrader.model.coinmarket.MarketHistory;
import com.assettrader.model.coinmarket.MarketSummary;
import com.assettrader.model.coinmarket.OrderBook;
import com.assettrader.model.coinmarket.Ticker;
import com.assettrader.model.publicapi.DTO.BuyDTO;
import com.assettrader.model.publicapi.DTO.CoinDTO;
import com.assettrader.model.publicapi.DTO.CoinResultDTO;
import com.assettrader.model.publicapi.DTO.CurrencyDTO;
import com.assettrader.model.publicapi.DTO.CurrencyResultDTO;
import com.assettrader.model.publicapi.DTO.MarketHistoryDTO;
import com.assettrader.model.publicapi.DTO.MarketSummariesDTO;
import com.assettrader.model.publicapi.DTO.MarketSummariesResultDTO;
import com.assettrader.model.publicapi.DTO.OrderBookDTO;
import com.assettrader.model.publicapi.DTO.ResultDTO;
import com.assettrader.model.publicapi.DTO.SellDTO;
import com.assettrader.model.publicapi.DTO.TickerDTO;
import com.assettrader.model.utils.OrderType;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class CoinServiceDTOImpl implements CoinServiceDTO {

	private static final String PUBLIC_URL = "https://bittrex.com/api/v1.1/public";
	private static final String GET_MARKETS = "/getmarkets";
	private static final String GET_CURRENCIES = "/getcurrencies";
	private static final String GET_MARKET_SUMMARIES = "/getmarketsummaries";
	private static final String GET_TICKER = "/getticker";
	// private static final String GET_MARKET_SUMMARY = "/getmarketsummary";
	private static final String GET_ORDER_BOOK = "/getorderbook";
	private static final String GET_MARKET_HISTORY = "/getmarkethistory";
	private static final String MARKET_PREFIX = "?market=";
	private static final String ORDER_PREFIX = "&type=";

	@Autowired
	CoinDaoDTO coinDTODao;

	// ==|| METHODS :: TO REQUEST DATA FROM API END-POINT
	// ================================================

	// TODO -- REFACTOR METHODS TO PREVENT PERSISTING DATA WHEN NOT REQUIRED
	// TODO -- VERIFY PK, FK RELATIONSHIP MAPPINGS - FIX IF NECCESSARY

	@SuppressWarnings("rawtypes") // WORKING REST ENDPOINT
	public List<Coin> getCoinMarkets(String exchange) {

		List<Coin> coinList = null;
		ObjectMapper mapper = initMapper();

		try {
			URL url = new URL(PUBLIC_URL + GET_MARKETS);
			List<CoinResultDTO> result = mapper.readValue(url, new TypeReference<List<CoinResultDTO>>() {
			});

			coinList = new ArrayList<>();
			for (CoinDTO coinDTO : result.get(0).getResult()) {
				coinList.add(DTOToCoin(coinDTO, exchange));
			}

			// TODO - ADD LOGIC TO PREVENT RELOADING COIN MARKET DATA
			coinDTODao.saveGetMarkets(coinList, exchange);
			return coinList; // RETURN MAPPED RESULT TO UI TO DISPLAY

		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		} finally {

		}

		return coinList;

	}

	@Override // WORKING REST ENDPOINT
	public List<Currency> getCurrencies(String exchange) {
		List<Currency> currencyList = null;
		ObjectMapper mapper = initMapper();

		try {
			URL url = new URL(PUBLIC_URL + GET_CURRENCIES);
			List<CurrencyResultDTO> jsonList = mapper.readValue(url, new TypeReference<List<CurrencyResultDTO>>() {
			});

			currencyList = new ArrayList<>();
			for (CurrencyDTO currencyDTO : jsonList.get(0).getResult()) {
				currencyList.add(DTOToCoin(currencyDTO));
			}

			coinDTODao.saveGetCurrencies(currencyList, exchange);
			return currencyList;
		} catch (IOException io) {
			System.out.println("IOException getting currencyList " + io.getMessage());
		}

		return currencyList;
	}

	@Override // WORKING REST ENDPOINT
	public List<MarketSummary> getMarketSummaries(String exchange) {
		List<MarketSummary> marketSummaryList = null;
		ObjectMapper mapper = initMapper();

		try {
			URL url = new URL(PUBLIC_URL + GET_MARKET_SUMMARIES);
			List<MarketSummariesResultDTO> jsonList = mapper.readValue(url,
					new TypeReference<List<MarketSummariesResultDTO>>() {
					});

			marketSummaryList = new ArrayList<>();
			for (MarketSummariesDTO marketDTO : jsonList.get(0).getResult()) {
				marketSummaryList.add(DTOToMarketSummary(marketDTO));
			}

			coinDTODao.saveGetMarketSummaries(marketSummaryList, exchange);
			return marketSummaryList;

		} catch (IOException io) {
			System.out.println("IOException getting market summaries" + io.getMessage());
		}

		return marketSummaryList;
	}

	@Override // WORKING REST ENDPOINT
	public Ticker getTicker(String marketName, String exchange) {
		Ticker ticker = null;
		ObjectMapper mapper = initMapper();

		try {
			URL url = new URL(PUBLIC_URL + GET_TICKER + MARKET_PREFIX + marketName);
			ResultDTO<TickerDTO> json = mapper.readValue(url, new TypeReference<ResultDTO<TickerDTO>>() {
			});

			ticker = new Ticker();

			for (TickerDTO t : json.getResult()) {
				Coin coin = new Coin();
				Date date = new Date();
				ticker.setAsk(t.getAsk());
				ticker.setBid(t.getBid());
				ticker.setLast(t.getLast());
				ticker.setCoin(coin);
				ticker.getCoin().setMarketName(marketName);
				ticker.setTimeStamp(date);
			}

			coinDTODao.saveGetTicker(ticker, exchange);
			return ticker;

		} catch (IOException io) {
			System.out.println("Error parsing json " + io.getMessage());
		}

		return ticker;
	}

	@Override
	public List<MarketHistory> getMarketHistory(String marketName, String exchange) {
		List<MarketHistory> marketHistoryList = null;
		ObjectMapper mapper = initMapper();

		try {
			URL url = new URL(PUBLIC_URL + GET_MARKET_HISTORY + MARKET_PREFIX + marketName);
			List<ResultDTO<MarketHistoryDTO>> jsonList = mapper.readValue(url,
					new TypeReference<List<ResultDTO<MarketHistoryDTO>>>() {
					});

			marketHistoryList = new ArrayList<>();
			// System.out.println("------- MARKET-NAME IS ------" + marketName +
			// "------ THIS IS NOT AN ERROR FROM MARKET-HISTORY -----------");
			for (MarketHistoryDTO marketHistoryDTO : jsonList.get(0).getResult()) {
				marketHistoryList.add(DTOToMarketHistory(marketHistoryDTO, marketName));
			}

			coinDTODao.saveGetMarketHistory(marketHistoryList, exchange);
			return marketHistoryList;

		} catch (IOException iox) {
			System.out.println("IO Exception " + iox.getMessage());
		}

		return marketHistoryList;
	}

	@Override
	public List<OrderBook> getOrderBook(String marketName, String buyOrSell, String exchange) {
		List<OrderBook> orderBookList = null;
		ObjectMapper mapper = initMapper();

		try {
			URL url = new URL(PUBLIC_URL + GET_ORDER_BOOK + MARKET_PREFIX + marketName + ORDER_PREFIX + buyOrSell);
			List<ResultDTO<OrderBookDTO>> jsonList = mapper.readValue(url,
					new TypeReference<List<ResultDTO<OrderBookDTO>>>() {
					});

			orderBookList = new ArrayList<>();

			for (OrderBookDTO orderDTO : jsonList.get(0).getResult()) {
				for (BuyDTO buyDTO : orderDTO.getBuy()) {
					orderBookList.add(DTOToOrderBook(buyDTO, marketName));
				}
				for (SellDTO sellDTO : orderDTO.getSell()) {
					orderBookList.add(DTOToOrderBook(sellDTO, marketName));
				}
			}

			coinDTODao.saveGetOrderBook(orderBookList, exchange);
			return orderBookList;

		} catch (IOException io) {
			System.out.println("IO Exception getting order book " + io.getMessage());
		}

		return orderBookList;
	}

	@Override // ADD CALL TO API CONTROLLER
	public List<Coin> loadApplicationBootingEndPoints(String exchange) {

		List<Coin> coinList = getCoinMarkets(exchange); // GET ALL THE COIN
														// MARKETS
		getMarketSummaries(exchange); // NEED THE MARKET-NAMES
		getCurrencies(exchange);

		List<String> coinName = new ArrayList<>();
		for (Coin coin : coinList) {
			if (coin.isActive())
				coinName.add(coin.getMarketName());
		}

		
		System.out.println("------- MARKET-HISTORY IS LOADING -----------");
		for (String marketName : coinName) {
			getMarketHistory(marketName, exchange);
		}
		System.out.println("------- MARKET-HISTORY IS DONE LOADING -----------");

		
		System.out.println("------- ORDER BOOK IS LOADING ----------");
		for (String marketName : coinName) {
			getOrderBook(marketName, OrderType.BOTH.name().toLowerCase(), exchange);
		}
		System.out.println("------- ORDER BOOK IS DONE LOADING ----------");

		
		System.out.println("------- TICKER IS LOADING ----------");
		for (String marketName : coinName) {
			getTicker(marketName, exchange);
		}
		System.out.println("------- TICKER IS DONE LOADING ----------");

		
		System.out.println("-----  LOADING AND PERSISTING INITIAL DATA IS DONE!!! ------");
		return coinList;
	}

	// ==|| METHODS :: FOR DATA-TRANSFER
	// ================================================

	private ObjectMapper initMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		return mapper;
	}

	private Coin DTOToCoin(CoinDTO coinDTO, String exchange) {

		Coin coin = new Coin();
		coin.setMarketName(coinDTO.getMarketName());
		coin.setBaseCurrency(coinDTO.getBaseCurrency());
		coin.setBaseCurrencyLong(coinDTO.getBaseCurrencyLong());
		coin.setMarketCurrency(coinDTO.getMarketCurrency());
		coin.setMarketCurrencyLong(coinDTO.getMarketCurrencyLong());
		coin.setActive(coinDTO.isIsActive());
		coin.setCreateDate(coinDTO.getCreateDate());

		if (coinDTO.getMarketCurrency().equals("BTC")) {
			coin.setLogoUrl("http://hd-fractals.com/wp-content/uploads/bitcoin_goldv2.png");
		} else {
			coin.setLogoUrl(coinDTO.getLogoUrl());
		}
		
		coin.setMinTradeSize(coinDTO.getMinTradeSize());
		coin.setCreated(coinDTO.getCreated());
		coin.setNotice(coinDTO.getNotice());
		coin.setExchangeName(exchange); // EXCHANGE NAME VALIDATION OCCURS IN
									// CONTROLLER & FRONT-END ENUM
		return coin;
	}

	private Currency DTOToCoin(CurrencyDTO currencyDTO) {

		Currency currency = new Currency();
		Coin coin = new Coin();

		currency.setCoin(coin);
		currency.setCurrencyShort(currencyDTO.getCurrency());
		currency.setCurrencyLong(currencyDTO.getCurrencyLong());
		currency.setMinConfirmation(currencyDTO.getMinConfirmation());
		currency.setTxFee(currencyDTO.getTxFee());
		currency.setActive(currencyDTO.isIsActive());
		currency.setCoinType(currencyDTO.getCoinType());
		currency.setBaseAddress(currencyDTO.getBaseAddress());
		return currency;
	}

	private MarketSummary DTOToMarketSummary(MarketSummariesDTO marketSummaryDTO) {

		MarketSummary market = new MarketSummary();
		market.setMarketName(marketSummaryDTO.getMarketName());
		market.setHigh(marketSummaryDTO.getHigh());
		market.setLow(marketSummaryDTO.getLow());
		market.setBaseVolume(marketSummaryDTO.getBaseVolume());
		market.setVolume(marketSummaryDTO.getVolume());
		market.setTimeStamp(marketSummaryDTO.getTimeStamp());
		market.setBid(marketSummaryDTO.getBid());
		market.setAsk(marketSummaryDTO.getAsk());
		market.setOpenBuyOrders(marketSummaryDTO.getOpenBuyOrders());
		market.setOpenSellOrders(marketSummaryDTO.getOpenSellOrders());
		market.setPrevDay(marketSummaryDTO.getPrevDay());
		market.setCreated(marketSummaryDTO.getCreated());
		market.setMarketName(marketSummaryDTO.getMarketName());

		return market;
	}

	private OrderBook DTOToOrderBook(BuyDTO buyDTO, String marketName) {

		OrderBook orderBook = new OrderBook();
		Date date = new Date();

		orderBook.setQuantity(buyDTO.getQuantity());
		orderBook.setRate(buyDTO.getRate());
		orderBook.setOrderBookDateTime(date);
		orderBook.setOrderType(OrderType.BUY);

		Coin coin = new Coin();
		coin.setMarketName(marketName);
		orderBook.setCoin(coin);
		orderBook.getCoin().setMarketName(marketName);
		return orderBook;
	}

	private OrderBook DTOToOrderBook(SellDTO sellDTO, String marketName) {

		OrderBook orderBook = new OrderBook();
		Date date = new Date();

		orderBook.setQuantity(sellDTO.getQuantity());
		orderBook.setRate(sellDTO.getRate());
		orderBook.setOrderBookDateTime(date);
		orderBook.setOrderType(OrderType.SELL);

		Coin coin = new Coin();
		coin.setMarketName(marketName);
		orderBook.setCoin(coin);
		orderBook.getCoin().setMarketName(marketName);
		return orderBook;
	}

	private MarketHistory DTOToMarketHistory(MarketHistoryDTO marketHistoryDTO, String marketName) {

		MarketHistory marketHistory = new MarketHistory();
		Coin coin = new Coin();

		marketHistory.setCoin(coin);
		marketHistory.getCoin().setMarketName(marketName);

		marketHistory.setOrderId(marketHistoryDTO.getId());
		marketHistory.setFillType(marketHistoryDTO.getFillType());
		marketHistory.setOrderType(OrderType.valueOf(marketHistoryDTO.getOrderType()));
		marketHistory.setPrice(marketHistoryDTO.getPrice());
		marketHistory.setQuantity(marketHistoryDTO.getQuantity());
		marketHistory.setTotal(marketHistoryDTO.getTotal());
		marketHistory.setTimeStamp(marketHistoryDTO.getTimeStamp());
		return marketHistory;
	}
	
//	@Override
//	public MarketSummary getMarketSummary(String marketName, String exchange) {
//		MarketSummary marketSummary = null;
//		ObjectMapper mapper = initMapper();
//
//		try {
//			URL url = new URL(PUBLIC_URL + GET_MARKET_SUMMARY + MARKET_PREFIX + marketName);
//			MarketSummariesResultDTO marketDTO = mapper.readValue(url, MarketSummariesResultDTO.class);
//
//			marketSummary = new MarketSummary();
//			for (MarketSummariesDTO market : marketDTO.getResult()) {
//				marketSummary = DTOToMarketSummary(market);
//			}
//
//			coinDTODao.saveGetMarketSummary(marketSummary, exchange);
//			return marketSummary;
//
//		} catch (IOException io) {
//			System.out.println("IOException getting market summary " + io.getMessage());
//		}
//
//		return marketSummary;
//	}

}
