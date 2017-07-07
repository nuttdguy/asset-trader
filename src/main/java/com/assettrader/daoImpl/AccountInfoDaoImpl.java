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

import com.assettrader.dao.AccountInfoDao;
import com.assettrader.model.view.AccountInfoView;
import com.assettrader.utils.DAOUtils;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Repository
@Transactional
public class AccountInfoDaoImpl implements AccountInfoDao {

	Connection connection = null;
	PreparedStatement statement = null;
	
	@Override
	public List<AccountInfoView> getMarketInfoView(String exchange) {
		List<AccountInfoView> marketInfoList = null;
		
		// 1. GET THE RESULTS FROM FIRST TABLE IN FIRST QUERY
		// 2. GET FIRST TICKER ASK PRICE IN DESC ORDER THROUGH ANOTHER LOOP
		try {
			connection = DAOUtils.getConnection();
			String sqlQuery1 = "SELECT A.COIN_ID, A.LOGO_URL, A.MARKET_NAME, "
					+ "A.MARKET_CURRENCY_LONG, A.MARKET_CURRENCY, "
					+ "B.VOLUME, B.OPEN_BUY_ORDERS, B.OPEN_SELL_ORDERS, B.HIGH, B.LOW "
					+ "FROM COIN A JOIN MARKET_SUMMARY B "
					+ "ON A.MARKET_NAME = B.MARKET_NAME "
					+ "WHERE A.EXCHANGE_NAME = ? "
					+ "ORDER BY B.VOLUME DESC; ";
			
			String sqlQuery2 = "SELECT * FROM TICKER WHERE MARKET_NAME = ? "
					+ " ORDER BY ASK DESC LIMIT 1 ";
			
			statement = connection.prepareStatement(sqlQuery1);	
			statement.setString(1, exchange);		
			ResultSet rs1 = statement.executeQuery();
			statement.clearParameters();
			
			marketInfoList = new ArrayList<>();
			while(rs1.next()) {
				
				AccountInfoView view = new AccountInfoView();
				String marketName= rs1.getString("MARKET_NAME");
				statement = connection.prepareStatement(sqlQuery2);
				statement.setString(1, marketName);
				ResultSet rs2 = statement.executeQuery();
				
				view.setId(rs1.getLong("COIN_ID"));
				view.setLogo(rs1.getString("LOGO_URL"));
				view.setMarketName(rs1.getString("MARKET_NAME"));
				view.setMarketCurrency(rs1.getString("MARKET_CURRENCY"));
				view.setMarketCurrencyLong(rs1.getString("MARKET_CURRENCY_LONG"));
				view.setVolume(rs1.getDouble("VOLUME"));
				view.setBuyOrders(rs1.getDouble("OPEN_BUY_ORDERS"));
				view.setSellOrders(rs1.getDouble("OPEN_SELL_ORDERS"));
				view.setHigh(rs1.getDouble("HIGH"));
				view.setLow(rs1.getDouble("LOW"));			
				if (rs2.next()) {
					view.setLast(rs2.getDouble("LAST"));
				}
				
				marketInfoList.add(view);
			}		
			
			
			rs1.close();
			
			
			
			
			
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

















