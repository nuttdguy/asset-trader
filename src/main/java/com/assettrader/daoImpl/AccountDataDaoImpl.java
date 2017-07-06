package com.assettrader.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

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
