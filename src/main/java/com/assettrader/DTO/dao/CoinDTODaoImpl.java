package com.assettrader.DTO.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transaction;

import org.springframework.stereotype.Service;

import com.assettrader.model.coin.Coin;
import com.assettrader.model.coin.Currency;
import com.assettrader.model.coin.MarketHistory;
import com.assettrader.model.coin.MarketSummary;
import com.assettrader.model.coin.OrderBook;
import com.assettrader.model.coin.Ticker;
import com.assettrader.utils.DAOUtilities;
import com.mysql.jdbc.Statement;


@Service
public class CoinDTODaoImpl implements CoinDTODao {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	

	@Override
	public void saveGetTicker(Ticker ticker) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGetMarketSummary(MarketSummary marketSummary) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGetMarketHistory(List<MarketHistory> marketHistory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGetOrderBook(List<OrderBook> orderBook) {
		// TODO Auto-generated method stub
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
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
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
				statement.execute();
				rs = statement.getGeneratedKeys();
				
	            while (rs.next()) {
	                System.out.println("Key returned from getGeneratedKeys():"
	                        + rs.getInt(1) + " == " + rs.getString(1));
	            } 
				
				System.out.println();
			}
				
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
					+ "CURRENCY_LONG, CURRENCY, IS_ACTIVE, MIN_CONFIRMATION, TX_FEE)"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?) ";
			
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			for (Currency currency : currencyList) {
				statement.setString(1, currency.getBaseAddress() );
				statement.setString(2, currency.getCoinType());
				statement.setString(3, currency.getCurrencyLong());
				statement.setString(4,  currency.getCurrency());
				statement.setBoolean(5, currency.isActive());
				statement.setShort(6, currency.getMinConfirmation());
				statement.setDouble(7, currency.getTxFee());
				statement.executeUpdate();
				rs = statement.getGeneratedKeys();
	            while (rs.next()) {
	                System.out.println("Key returned from getGeneratedKeys():"
	                        + rs.getInt(1) + " == " + rs.getString(1));
	            } 
				
				System.out.println();
			}
			
			
		} catch (SQLException ex) {
			System.out.println("Error inserting currencies " + ex.getMessage());
		} finally {
			closeResources();
		}
	}

	@Override
	public void saveGetMarketSummaries(List<MarketSummary> marketSummaryList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGetMarketSummary(List<MarketSummary> marketSummary) {
		// TODO Auto-generated method stub
		
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
