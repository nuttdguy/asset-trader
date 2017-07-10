package com.assettrader.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.assettrader.api.bittrex.model.accountapi.Balance;
import com.assettrader.api.bittrex.model.accountapi.DepositHistoryEntry;
import com.assettrader.api.bittrex.model.accountapi.WithdrawalHistoryEntry;
import com.assettrader.api.bittrex.model.common.ApiResult;
import com.assettrader.dao.AccountDataDao;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.model.utils.ExchangeName;
import com.assettrader.utils.BittrexKeyUtil;
import com.assettrader.utils.DAOUtils;

@Repository
public class AccountDataDaoImpl implements AccountDataDao {
	
	Connection connection = null;
	PreparedStatement statement = null;
	
	
	//============================================
	//== CREATE
	//============================================
	
	public void saveCurrentBalance(Double btcprice, Double balance, Long userId) {
		
		try {
			
			connection = DAOUtils.getConnection();
			String sqlInsert = "INSERT INTO BALANCE_HISTORY( "
					+ "BALANCE_AMOUNT, BALANCE_DATE_TIME, BTC_PRICE, USER_PROFILE_ID) "
					+ "VALUES( ?, NOW(), ?, ?)";
			
			statement = connection.prepareStatement(sqlInsert);
			statement.setDouble(1, btcprice);
			statement.setDouble(2, balance);
			statement.setLong(3, userId);
			statement.execute();					
			
		} catch (SQLException se) {
			System.out.println("SQL Exception: " + se.getMessage());
		} finally {
			closeResources();
		}
		
	}
	
	
	@Override
	public void saveDepositHistory(ApiResult<List<DepositHistoryEntry>> depositHistory, Long userId) {
		try {
			connection = DAOUtils.getConnection();
			String sql = "INSERT IGNORE INTO DEPOSIT_HISTORY_ENTRY( "
					+ "AMOUNT, CONFIRMATIONS, CRYPTO_ADDRESS, CURRENCY," // 4
					+ "LAST_UPDATED, TX_ID, EXCHANGE_NAME ) " // 3
					+ "VALUES("
					+ "?, ?, ?, ?, ?, "
					+ "?, ? )";
			
			List<DepositHistoryEntry> depositList = depositHistory.getResult();
			
			for (DepositHistoryEntry entry : depositList) {
				statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				
				statement.setDouble(1, entry.getAmount());
				statement.setInt(2, entry.getConfirmations());
				statement.setString(3, entry.getCryptoAddress());
				statement.setString(4, entry.getCurrency());
				statement.setTimestamp(5, new Timestamp(entry.getLastUpdated().getTime()));
				statement.setString(6, entry.getTxId());
				statement.setString(7, ExchangeName.BITTREX.name());
				
				statement.execute();
				statement.clearParameters();
			}
			
			System.out.println("Successfully persisted DEPOSIT HISTORY RECORDS");
		} catch (SQLException se) {
			System.out.println("SQL Exception: " + se.getMessage());
		} finally {
			closeResources();
		}
		
	}

	
	@Override
	public void saveWithdrawalHistory(ApiResult<List<WithdrawalHistoryEntry>> withdrawalHistory, Long userId) {

		try {
			connection = DAOUtils.getConnection();
			String sql = "INSERT IGNORE INTO WITHDRAWAL_HISTORY_ENTRY( "
					+ "PAYMENT_UUID, ADDRESS, AMOUNT, AUTHORIZED, CANCELED, " // 5
					+ "CURRENCY, INVALID_ADDRESS, OPENED, PENDING_PAYMENT, TX_COST, " // 5
					+ "TX_ID, EXCHANGE_NAME ) " // 2
					+ "VALUES("
					+ "?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, "
					+ "?, ? ) ";
			
			List<WithdrawalHistoryEntry> withdrawList = withdrawalHistory.getResult();
			
			for (WithdrawalHistoryEntry entry : withdrawList) {
				
				statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);		
				statement.setString(1, entry.getPaymentUuid());
				statement.setString(2, entry.getAddress());
				statement.setDouble(3, entry.getAmount());
				statement.setBoolean(4, entry.getAuthorized());
				statement.setBoolean(5, entry.getCanceled());
				statement.setString(6, entry.getCurrency());
				statement.setBoolean(7, entry.getInvalidAddress());
				statement.setTimestamp(8, new Timestamp(entry.getOpened().getTime()));
				statement.setBoolean(9, entry.getPendingPayment());
				statement.setDouble(10, entry.getTxCost());
				statement.setString(11, entry.getTxId());
				statement.setString(12, ExchangeName.BITTREX.name());
				
				statement.execute();
				statement.clearParameters();
				
			}
			
			System.out.println("Successfully persisted WITHDRAWAL HISTORY RECORDS");
		} catch (SQLException se) {
			System.out.println("SQL Exception: " + se.getMessage());
		} finally {
			closeResources();
		}
		
		
	}

	
	@Override
	public boolean saveReturnHistory(Double totalDeposit, Double totalWithdrawal, Double totalBalance, Double btcprice,
			Long userId) {
		
		try {
			connection = DAOUtils.getConnection();
			String sqlInsert = "INSERT INTO RETURN_HISTORY( "
					+ "ENTRY_DATE, TOTAL_BALANCE, TOTAL_DEPOSIT, TOTAL_WITHDRAWAL, BTC_PRICE, USER_PROFILE_ID ) "
					+ "VALUES( NOW(), ?, ?, ?, ?, ? )";
			
			statement = connection.prepareStatement(sqlInsert);
			statement.setDouble(1, totalBalance);
			statement.setDouble(2, totalDeposit);
			statement.setDouble(3, totalWithdrawal);
			statement.setDouble(4, btcprice);
			statement.setLong(5, userId);
			return statement.execute();
			
		} catch (SQLException se) {
			System.out.println("SQL Exception: " + se.getMessage());
		} finally {
			closeResources();
		}
		return false;
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
	public BittrexKeyUtil getApiKey(Long userId) {
		BittrexKeyUtil keyUtil = new BittrexKeyUtil();
		
		try {
			connection = DAOUtils.getConnection();
			String sql = "SELECT API_KEY, SECRET_KEY FROM API_CREDENTIAL "
					+ "WHERE USER_PROFILE_ID = ? "
					+ "AND SET_PRIMARY = TRUE";
			
			statement = connection.prepareStatement(sql);
			statement.setLong(1, userId);
			
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
//			String sql = "SELECT 	B.* FROM ACCOUNTS A "
//					+ "LEFT JOIN 	BALANCE B ON A.CURRENCY = B.CURRENCY "
//					+ "RIGHT JOIN  	USER_ACCOUNT C ON A.CURRENCY = C.CURRENCY "
//					+ "WHERE 		C.USER_PROFILE_ID = ?";
			
			// GET CURRENCY, EXCHANGE_NAME, USER_ACCOUNT_ID, ADD_DATE_TIME FROM USER_ACCOUNT WHERE USER_PROFILE_ID = 1;
			// GET CURRENCY, EXCHANGE_NAME, ACCOUNT_ID, ADD_DATE FROM ACCOUNTS;
			// GET * FROM BALANCE WHERE CURRENCY = ? AND EXCHANGE_NAME = ?;
			
			// GET ALL ACTIVE ACCOUNTS
			String sqlSelect1 = "SELECT CURRENCY FROM ACCOUNTS";
			String sqlSelect2 = "SELECT * FROM BALANCE "
					+ "WHERE CURRENCY = ? "
					+ "AND AVAILABLE > 0";
			
			statement = connection.prepareStatement(sqlSelect1);
			statement.setLong(1, id);			
			ResultSet rs1 = statement.executeQuery();
			statement.clearParameters();
			
			while (rs1.next()) {
				statement = connection.prepareStatement(sqlSelect2);
				statement.setString(1, rs1.getString("CURRENCY") );
				ResultSet rs2 = statement.executeQuery();
				
				while (rs2.next()) {
					Balance b = new Balance();
					b.setCurrency(rs2.getString("CURRENCY"));
					b.setBalance(rs2.getDouble("ACCOUNT_BALANCE"));
					b.setAvailable(rs2.getDouble("AVAILABLE"));
					Date date = rs2.getDate("BALANCE_DATE");
					b.setBalanceDate(date);
					balances.add(b);
				}
				rs2.close();
			}
			rs1.close();
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
