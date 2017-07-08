package com.assettrader.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.assettrader.api.bittrex.model.accountapi.Balance;
import com.assettrader.dao.AccountDataDao;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.utils.BittrexKeyUtil;
import com.assettrader.utils.DAOUtils;

@Repository
public class AccountDataDaoImpl implements AccountDataDao {
	
	Connection connection = null;
	PreparedStatement statement = null;
	
	
	//============================================
	//== CREATE
	//============================================
	
	public void saveCurrentBalance(Double balance, Long userId) {
		
		try {
			
			connection = DAOUtils.getConnection();
			String sqlInsert = "INSERT INTO BALANCE_HISTORY( "
					+ "BALANCE_AMOUNT, BALANCE_DATE_TIME, USER_PROFILE_ID) "
					+ "VALUES( ?, NOW(), ?)";
			
			statement = connection.prepareStatement(sqlInsert);
			statement.setDouble(1, balance);
			statement.setLong(2, userId);
			statement.execute();					
			
		} catch (SQLException se) {
			System.out.println("SQL Exception: " + se.getMessage());
		} finally {
			closeResources();
		}
		
	}
	
	//============================================
	//=== UPDATE
	//============================================
	
	
	//============================================
	//=== DELETES
	//============================================
	

	//============================================
	//=== RETRIEVE
	//============================================
	
	@Override
	public BittrexKeyUtil getApiKey(RWLoginDetail userDetail) {
		BittrexKeyUtil keyUtil = new BittrexKeyUtil();
		
		try {
			connection = DAOUtils.getConnection();
			String sql = "SELECT API_KEY, SECRET_KEY FROM API_CREDENTIAL "
					+ "WHERE USER_PROFILE_ID = ? "
					+ "AND SET_PRIMARY = TRUE";
			
			statement = connection.prepareStatement(sql);
			statement.setLong(1, userDetail.getId());
			
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				keyUtil.setKEY(rs.getString("API_KEY"));
				keyUtil.setSECRET(rs.getString("SECRET_KEY"));
			}
			rs.close();
			return keyUtil;
			
		} catch (SQLException se) {
			System.out.println("SQL Exception: " + se.getMessage());
		} finally {
			closeResources();
		}
		
		return keyUtil;
	}

	@Override
	public List<Balance> getAccountBalances(Long id) {
		List<Balance> balances = new ArrayList<>();
		
		try {
			connection = DAOUtils.getConnection();
			String sql = "SELECT 	B.* FROM ACCOUNTS A "
					+ "LEFT JOIN 	BALANCE B ON A.CURRENCY = B.CURRENCY "
					+ "RIGHT JOIN  	USER_ACCOUNT C ON A.CURRENCY = C.CURRENCY "
					+ "WHERE 		C.USER_PROFILE_ID = ?";
			
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Balance b = new Balance();
				b.setCurrency(rs.getString("CURRENCY"));
				b.setBalance(rs.getDouble("ACCOUNT_BALANCE"));
				b.setAvailable(rs.getDouble("AVAILABLE"));
				Date date = rs.getDate("BALANCE_DATE");
				b.setBalanceDate(date);
				balances.add(b);
			}
			rs.close();
			return balances;
						
		} catch (SQLException se) {
			System.out.println("SQL Exception: " + se.getMessage());
		} finally {
			closeResources();
		}
		return balances;
		
	}
	
	
	
	//===================================================
	// PRIVATE METHODS TO CLOSE OPENED RESOURCES
	//===================================================
	
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
