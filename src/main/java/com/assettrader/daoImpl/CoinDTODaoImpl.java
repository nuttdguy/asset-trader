package com.assettrader.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assettrader.dao.CoinDTODao;
import com.assettrader.model.coin.Coin;
import com.assettrader.model.coin.Currency;
import com.assettrader.model.coin.MarketHistory;
import com.assettrader.model.coin.MarketSummary;
import com.assettrader.model.coin.OrderBook;
import com.assettrader.model.coin.Ticker;
import com.assettrader.utils.DAOUtilities;


@Service
public class CoinDTODaoImpl implements CoinDTODao {

	Connection connection = null;
	PreparedStatement statement = null;
	

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
			statement = connection.prepareStatement(sql);
			
			for (Coin coin : coinList) {
				statement.setString(1, coin.getMarketName());
				statement.setDate(2,  coin.getCreated());
				statement.setString(3, coin.getMinTradeSize());
				statement.setString(4, coin.getNotice());
				statement.setString(5, coin.getBaseCurrency());
				statement.setString(6, coin.getBaseCurrencyLong());
				statement.setBoolean(7, coin.isActive());
				statement.setString(8, coin.getLogoUrl());
				statement.setString(9, coin.getMarketCurrency());
				statement.setString(10, coin.getMarketCurrencyLong());
				
				statement.execute();
			}
				
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeResources();
		}
	}

	@Override
	public void saveGetCurrencies(List<Currency> currencyList) {
		// TODO Auto-generated method stub
		
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
