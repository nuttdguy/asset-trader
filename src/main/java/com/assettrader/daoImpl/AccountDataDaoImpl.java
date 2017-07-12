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
	
	@Override
	public void saveCurrentBalance(Double btcprice, Double balance,  Long userId) {
		
		try {
			
			connection = DAOUtils.getConnection();
			String sqlInsert = "INSERT INTO BALANCE_HISTORY( "
					+ "BALANCE_AMOUNT, BTC_PRICE, USER_PROFILE_ID, BALANCE_DATE_TIME ) "
					+ "VALUES( ?, ?, ?, NOW() )";
			
			statement = connection.prepareStatement(sqlInsert);
			statement.setDouble(1, balance);
			statement.setDouble(2, btcprice);
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
					+ "ENTRY_DATE, TOTAL_BALANCE, TOTAL_DEPOSIT, "
					+ "TOTAL_WITHDRAWAL, BTC_PRICE, USER_PROFILE_ID ) "
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
	

	// TODO -- FIX TABLE AND RESULT OF QUERY, 
	@Override
	public List<Balance> getAccountBalances(Long id) {
		List<Balance> balances = new ArrayList<>();
		
		try {
			connection = DAOUtils.getConnection();
			
			// TODO -- GET ALL VALUES FROM EACH EXCHANGE INDIVIDUALLY
			String sqlSelectWallet = "SELECT DISTINCT * FROM ACCOUNTS "
					+ "WHERE USER_PROFILE_ID = ? "
					+ "AND EXCHANGE_NAME = ? ";
			
			String sqlSelectBittrex = "SELECT DISTINCT * FROM ACCOUNTS "
					+ "WHERE USER_PROFILE_ID = ? "
					+ "AND EXCHANGE_NAME = ? ";
			
			String sqlSelectBalance = "SELECT * FROM BALANCE "
					+ "WHERE ACCOUNT_BALANCE > 0 "
					+ "AND CURRENCY = ? ";
			
			// RESULT_1 == ALL WALLET-TYPE CURRENCY NAMES
			statement = connection.prepareStatement(sqlSelectWallet);
			statement.setLong(1, id);	
			statement.setString(2, ExchangeName.WALLET.name());	
			ResultSet rs1 = statement.executeQuery();
			statement.clearParameters();
			
			// RESULT_2 == ALL BITTREX-TYPE CURRENCY NAMES
			statement = connection.prepareStatement(sqlSelectBittrex);
			statement.setLong(1, id);		
			statement.setString(2, ExchangeName.BITTREX.name());	
			ResultSet rs2 = statement.executeQuery();
			statement.clearParameters();
			
			// QUERY FOR BALANCES GREATER-THAN 0 BY EXCHANGE-TYPE AND CURRENCY NAME
			while (rs1.next() ) {
				
				statement = connection.prepareStatement(sqlSelectBalance);
				statement.setString(1, rs1.getString("CURRENCY"));
				ResultSet rs3 = statement.executeQuery();
				statement.clearParameters();
				
				if (rs3.next() ) {
					Balance b = new Balance();
					b.setCurrency(rs3.getString("CURRENCY"));
					b.setBalance(rs3.getDouble("ACCOUNT_BALANCE"));
					b.setAvailable(rs3.getDouble("AVAILABLE"));
					Date date = rs3.getDate("BALANCE_DATE");
					b.setBalanceDate(date);
					b.setExchangeName(ExchangeName.valueOf(rs3.getString("EXCHANGE_NAME")));
					balances.add(b);
				}		
				rs3.close();
			}
			
			while (rs1.next() ) {
				
				statement = connection.prepareStatement(sqlSelectBalance);
				statement.setString(1, rs1.getString("CURRENCY"));
				ResultSet rs4 = statement.executeQuery();
				statement.clearParameters();
				
				if (rs4.next() ) {
					Balance b = new Balance();
					b.setCurrency(rs4.getString("CURRENCY"));
					b.setBalance(rs4.getDouble("ACCOUNT_BALANCE"));
					b.setAvailable(rs4.getDouble("AVAILABLE"));
					Date date = rs4.getDate("BALANCE_DATE");
					b.setBalanceDate(date);
					b.setExchangeName(ExchangeName.valueOf(rs4.getString("EXCHANGE_NAME")));
					balances.add(b);
				}	
				rs4.close();
			}
			
			rs1.close();
			rs2.close();

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
