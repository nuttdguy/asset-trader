package com.assettrader.DTO.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assettrader.dao.CoinDao;
import com.assettrader.model.coin.Coin;
import com.assettrader.model.coin.Currency;
import com.assettrader.model.coin.MarketHistory;
import com.assettrader.model.coin.MarketSummary;
import com.assettrader.model.coin.OrderBook;
import com.assettrader.model.coin.Ticker;
import com.assettrader.utils.DAOUtilities;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


@Service
public class CoinDTODaoImpl implements CoinDTODao {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	
	@Autowired
	CoinDao coinDAO;
	

	@Override
	public void saveGetTicker(Ticker ticker) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO TICKER"
					+ "(ASK, BID, LAST, TIME_STAMP, MARKET_NAME)"
					+ "VALUES(?, ?, ?, NOW(), ?) "
					+ "ON DUPLICATE KEY UPDATE "
					+ "ASK = ?";
			
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement.setDouble(1, ticker.getAsk());
			statement.setDouble(2, ticker.getBid());
			statement.setDouble(3, ticker.getLast());
			statement.setString(4, ticker.getCoin().getMarketName());
			
			// FOR UPDATE
			statement.setDouble(5, ticker.getAsk());
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
            while (rs.next()) {
                // System.out.println("Key returned from getGeneratedKeys():"
                rs.getInt(1);
            } 
			
		} catch (MySQLIntegrityConstraintViolationException cex) {
			System.out.println("Error inserting into Ticker " + cex.getMessage());
		} catch (SQLException sx) {
			System.out.println("Error inserting Ticker" + sx.getMessage() );
		} finally {
			closeResources();
		}
		
	}

	@Override // TODO - FIX CONSTRAINT, IS ALLOWING MULTIPLE RECORDS
	public void saveGetMarketSummary(MarketSummary marketSummary) {

		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO MARKET_SUMMARY"
					+ "(ASK, BASE_VOLUME, BID, CREATED, HIGH, LOW, MARKET_NAME, OPEN_BUY_ORDERS,"
					+ "OPEN_SELL_ORDERS, PREV_DAY, TIME_STAMP, VOLUME)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
					+ " ON DUPLICATE KEY UPDATE "
					+ " ASK = ? ";
			
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement.setDouble(1, marketSummary.getAsk());
			statement.setDouble(2, marketSummary.getBaseVolume());
			statement.setDouble(3, marketSummary.getBid());
			statement.setDate(4, marketSummary.getCreated());
			statement.setDouble(5, marketSummary.getHigh());
			statement.setDouble(6, marketSummary.getLow());
			statement.setString(7, marketSummary.getMarketName());
			statement.setInt(8, marketSummary.getOpenBuyOrders());
			statement.setInt(9, marketSummary.getOpenSellOrders());
			statement.setInt(10, marketSummary.getPrevDay());
			statement.setDate(11, marketSummary.getTimeStamp());
			statement.setDouble(12, marketSummary.getVolume());
			
			statement.setDouble(13, marketSummary.getAsk());
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
            while (rs.next()) {
                // System.out.println("Key returned from getGeneratedKeys():"
                rs.getInt(1);
            } 
			
		} catch (MySQLIntegrityConstraintViolationException cex) {
			System.out.println("Error inserting into market-summary " + cex.getMessage());
		} catch (SQLException sx) {
			System.out.println("Error inserting market-summary " + sx.getMessage() );
		} finally {
			closeResources();
		}
		
	}

	@Override
	public void saveGetMarketHistory(List<MarketHistory> marketHistoryList) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO MARKET_HISTORY "
					+ "(FILL_TYPE, ORDER_ID, ORDER_TYPE, PRICE,"
					+ " QUANTITY, TIME_STAMP, TOTAL, MARKET_NAME)"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
					+ " ON DUPLICATE KEY UPDATE "
					+ " FILL_TYPE = ? ";
			
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
			for (MarketHistory marketHistory : marketHistoryList) {
				// FOR INSERT
				statement.setString(1, marketHistory.getFillType());
				statement.setDouble(2, marketHistory.getOrderId());
				statement.setString(3, marketHistory.getOrderType().name());
				statement.setDouble(4, marketHistory.getPrice());
				statement.setDouble(5, marketHistory.getQuantity());
				statement.setTimestamp(6, new Timestamp( marketHistory.getTimeStamp().getTime()) );
				statement.setDouble(7, marketHistory.getTotal());
				statement.setString(8, marketHistory.getCoin().getMarketName());
				
				// FOR UPDATE
				statement.setString(9, marketHistory.getFillType());
				statement.executeUpdate();
				rs = statement.getGeneratedKeys();
				
	            while (rs.next()) {
	               rs.getInt(1);
	            } 
			}
			
			
		} catch (MySQLIntegrityConstraintViolationException cex) {
			System.out.println("Error inserting into market-history " + cex.getMessage());
		} catch (SQLException sex) {
			System.out.println("Error inserting into market-history " + sex.getMessage());
		} finally {
			closeResources();
		}
		
	}

	@Override
	public void saveGetOrderBook(List<OrderBook> orderBook) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO ORDER_BOOK "
					+ "(ORDER_BOOK_DATETIME, ORDER_TYPE, QUANTITY, RATE, MARKET_NAME)"
					+ " VALUES(NOW(), ?, ?, ?, ?) "
					+ "ON DUPLICATE KEY UPDATE "
					+ "ORDER_TYPE = ?";
			
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			for (OrderBook order : orderBook) {
				
				statement.setString(1, order.getOrderType().name());
				statement.setDouble(2, order.getQuantity());
				statement.setDouble(3, order.getRate());
				statement.setString(4, order.getCoin().getMarketName());
				
				// FOR UPDATE
				statement.setString(5, order.getOrderType().name());
				statement.executeUpdate();
				rs = statement.getGeneratedKeys();
				
	            while (rs.next()) {
	               // System.out.println("Key returned from getGeneratedKeys():"
	               rs.getInt(1);
	            } 
			}
			
			
		} catch (MySQLIntegrityConstraintViolationException cex) {
			System.out.println("Error inserting into order-books " + cex.getMessage());
		} catch (SQLException sex) {
			System.out.println("Error inserting into order-books " + sex.getMessage());
		} finally {
			closeResources();
		}
		
	}

	@Override
	public void saveGetMarkets(List<Coin> coinList) {

		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO COIN"
					+ "(MARKET_NAME, CREATED, MIN_TRADE_SIZE, "
					+ "NOTICE, BASE_CURRENCY, BASE_CURRENCY_LONG, "
					+ "IS_ACTIVE, LOGO_URL, "
					+ "MARKET_CURRENCY, MARKET_CURRENCY_LONG) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
					+ "ON DUPLICATE KEY UPDATE "
					+ "MIN_TRADE_SIZE = ?";
			
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// TODO, RETURN THE GENERATED KEY, SAVE THE CURRENT PK STRING VALUE
			for (Coin coin : coinList) {
				statement.setString(1, coin.getMarketName());
				statement.setDate(2, coin.getCreated());
				statement.setString(3, coin.getMinTradeSize());
				statement.setString(4, coin.getNotice());
				statement.setString(5, coin.getBaseCurrency());
				statement.setString(6, coin.getBaseCurrencyLong());
				statement.setBoolean(7, coin.isActive());
				statement.setString(8, coin.getLogoUrl());
				statement.setString(9, coin.getMarketCurrency());
				statement.setString(10, coin.getMarketCurrencyLong());		
				
				statement.setString(11, coin.getMinTradeSize());
				statement.execute();
				rs = statement.getGeneratedKeys();
				
	            while (rs.next()) {
	                System.out.println("Key returned from getGeneratedKeys():"
	                        + rs.getInt(1) + " == " + rs.getString(1));
	            } 
				
				System.out.println();
			}
				
		} catch (MySQLIntegrityConstraintViolationException cex) {
			System.out.println("Error inserting into get markets " + cex.getMessage());
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeResources();
		}
	}

	@Override
	public void saveGetCurrencies(List<Currency> currencyList) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO CURRENCY"
					+ "(BASE_ADDRESS, COIN_TYPE, "
					+ "CURRENCY_LONG, CURRENCY, IS_ACTIVE, MIN_CONFIRMATION, TX_FEE, MARKET_NAME)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?) "
					+ "ON DUPLICATE KEY UPDATE "
					+ "BASE_ADDRESS = ?";
			
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			for (Currency currency : currencyList) {
				String marketName = coinDAO.getMarketNameByCurrencyShortName(currency.getCurrency());
				
				if (!marketName.equals("NOT FOUND")) {
					statement.setString(1, currency.getBaseAddress());
					statement.setString(2, currency.getCoinType());
					statement.setString(3, currency.getCurrencyLong());
					statement.setString(4,  currency.getCurrency());
					statement.setBoolean(5, currency.isActive());
					statement.setShort(6, currency.getMinConfirmation());
					statement.setDouble(7, currency.getTxFee());
					statement.setString(8, marketName);
					
					// FOR UPDATE
					statement.setString(9, currency.getBaseAddress());
					statement.executeUpdate();
				}
			}
			
		} catch (MySQLIntegrityConstraintViolationException cex) {
			System.out.println("Error inserting into currencies " + cex.getMessage());
		} catch (SQLException ex) {
			System.out.println("Error inserting currencies " + ex.getMessage());
		} finally {
			closeResources();
		}
	}

	@Override // TODO - CONSTRAINT ALLOWS DUPLICATE RECORDS ??
	public void saveGetMarketSummaries(List<MarketSummary> marketSummaryList) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO MARKET_SUMMARY"
					+ "(ASK, BID, CREATED, HIGH, LOW, MARKET_NAME, OPEN_BUY_ORDERS,"
					+ "OPEN_SELL_ORDERS, PREV_DAY, TIME_STAMP, VOLUME)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
					+ "ON DUPLICATE KEY UPDATE "
					+ "ASK = ? ";
			
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			for (MarketSummary market : marketSummaryList) {
				
				String name = market.getMarketName();
				String marketName = coinDAO.getCoinMarketName(name);
					
				if (!marketName.equals("NOT FOUND")) {
					statement.setDouble(1, market.getAsk());
					statement.setDouble(2, market.getBid());
					statement.setDate(3, market.getCreated());
					statement.setDouble(4, market.getHigh());
					statement.setDouble(5, market.getLow());
					statement.setString(6, market.getMarketName());
					statement.setInt(7, market.getOpenBuyOrders());
					statement.setInt(8, market.getOpenSellOrders());
					statement.setInt(9, market.getPrevDay());
					statement.setDate(10, market.getTimeStamp());
					statement.setDouble(11, market.getVolume());
					
					// FOR UPDATE
					statement.setDouble(12, market.getAsk());
					statement.executeUpdate();
					rs = statement.getGeneratedKeys();
		            while (rs.next()) {
		                System.out.println("Key returned from getGeneratedKeys():"
		                        + rs.getInt(1) + " == " + rs.getString(1));
		            } 
				}
			}
			
		} catch (MySQLIntegrityConstraintViolationException cex) {
			System.out.println("Error inserting into market-summaries " + cex.getMessage());
		} catch (SQLException sx) {
			System.out.println("Error inserting market-summaries " + sx.getMessage() );
		} finally {
			closeResources();
		}
		
	}

	@Override
	public void saveGetMarketSummary(List<MarketSummary> marketSummary) {
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO MARKET_SUMMARY"
					+ "(ASK, BID, CREATED, HIGH, LOW, MARKET_NAME, OPEN_BUY_ORDERS,"
					+ "OPEN_SELL_ORDERS, PREV_DAY, TIME_STAMP, VOLUME)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
					+ "ON DUPLICATE KEY UPDATE"
					+ " ASK = ? ";
			
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			for (MarketSummary market : marketSummary) {
				statement.setDouble(1, market.getAsk());
				statement.setDouble(2, market.getBid());
				statement.setDate(3, market.getCreated());
				statement.setDouble(4, market.getHigh());
				statement.setDouble(5, market.getLow());
				statement.setString(6, market.getMarketName());
				statement.setInt(7, market.getOpenBuyOrders());
				statement.setInt(8, market.getOpenSellOrders());
				statement.setInt(9, market.getPrevDay());
				statement.setDate(10, market.getTimeStamp());
				statement.setDouble(11, market.getVolume());
				
				statement.setDouble(12, market.getAsk());
				statement.executeUpdate();
				rs = statement.getGeneratedKeys();
	            while (rs.next()) {
	                System.out.println("Key returned from getGeneratedKeys():"
	                        + rs.getInt(1) + " == " + rs.getString(1));
	            } 
			}
			
		} catch (MySQLIntegrityConstraintViolationException cex) {
			System.out.println("Error inserting into market summary " + cex.getMessage());
		} catch (SQLException sx) {
			System.out.println("Error inserting market-summary " + sx.getMessage() );
		} finally {
			closeResources();
		}
		
	}
	
	
	private void closeResources() {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

}
