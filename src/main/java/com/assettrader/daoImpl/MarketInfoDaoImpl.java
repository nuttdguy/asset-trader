package com.assettrader.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.assettrader.dao.MarketInfoDao;
import com.assettrader.model.view.MarketInfoView;
import com.assettrader.utils.DAOUtils;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Repository
@Transactional
public class MarketInfoDaoImpl implements MarketInfoDao {

	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet rs = null;
	
	@Override
	public List<MarketInfoView> getMarketInfoView(String exchange) {
		List<MarketInfoView> marketInfoList = null;
		
		// TODO -- CHANGE BACK TO VARIABLE; TEST HARD-CODE VALUE
		String sqlExchangeName = "BITTREX";
		
		try {
			connection = DAOUtils.getConnection();
			String sql = "SELECT A.COIN_ID, A.LOGO_URL, A.MARKET_NAME, A.MARKET_CURRENCY_LONG, A.MARKET_CURRENCY, "
					+ "B.VOLUME, B.OPEN_BUY_ORDERS, B.OPEN_SELL_ORDERS, B.HIGH, B.LOW, "
					+ "C.LAST "
					+ "FROM COIN A "
					+ "JOIN MARKET_SUMMARY B 	ON A.MARKET_NAME = B.MARKET_NAME "
					+ "JOIN TICKER C 			ON A.MARKET_NAME = C.MARKET_NAME "
					+ "WHERE A.EXCHANGE = ?; ";
			
			statement = connection.prepareStatement(sql);	
			statement.setString(1, sqlExchangeName);
			
			rs = statement.executeQuery();
			
			marketInfoList = new ArrayList<>();
			while(rs.next()) {
				MarketInfoView view = new MarketInfoView();
				view.setId(rs.getLong("COIN_ID"));
				view.setLogo(rs.getString("LOGO_URL"));
				view.setMarketName(rs.getString("MARKET_NAME"));
				view.setMarketCurrency(rs.getString("MARKET_CURRENCY"));
				view.setMarketCurrencyLong(rs.getString("MARKET_CURRENCY_LONG"));
				view.setVolume(rs.getDouble("VOLUME"));
				view.setBuyOrders(rs.getDouble("OPEN_BUY_ORDERS"));
				view.setSellOrders(rs.getDouble("OPEN_SELL_ORDERS"));
				view.setHigh(rs.getDouble("HIGH"));
				view.setLow(rs.getDouble("LOW"));
				view.setLast(rs.getDouble("LAST"));
				marketInfoList.add(view);
			}		
			rs.close();
			
		} catch (SQLSyntaxErrorException sqx) {
			System.out.println("Syntax error : " + sqx.getMessage());
		} catch (MySQLIntegrityConstraintViolationException cex) {
			System.out.println("Error inserting into get markets " + cex.getMessage());
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeResources();
		}
			
		return marketInfoList;
	}

	
	//==|| CLOSE RESOURCES 
	private void closeResources() {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException sex) {
			System.out.println("Could not close statement");
			sex.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException sex) {
			System.out.println("Could not close connection");
			sex.printStackTrace();
		}
	}
	
	
}

















