package com.assettrader.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.stereotype.Repository;

import com.assettrader.dao.UserDao;
import com.assettrader.model.Address;
import com.assettrader.model.Credential;
import com.assettrader.model.EmailProvider;
import com.assettrader.model.SocialNetwork;
import com.assettrader.model.UserProfile;
import com.assettrader.model.coinmarket.Coin;
import com.assettrader.model.rest.RWApiCredential;
import com.assettrader.model.rest.RWExternalWallet;
import com.assettrader.model.rest.RWFavorite;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.model.rest.RWPassword;
import com.assettrader.model.utils.EmailProviderName;
import com.assettrader.model.utils.ExchangeName;
import com.assettrader.model.view.FavoriteCoinView;
import com.assettrader.utils.DAOUtils;

@Repository
public class UserDaoImpl implements UserDao {

	Connection connection = null;
	PreparedStatement statement = null;
	
	
	//============================================
	//== CREATE
	//============================================
	
	@Override 
	public UserProfile registerUser(UserProfile newUser) {
		
		try {
			connection = DAOUtils.getConnection();
			String userSql = "INSERT INTO USER_PROFILE( "
					+ "FIRST_NAME, LAST_NAME, CREATED_DATE, USERNAME) "
					+ "VALUES( ?, ?, NOW(), ? )";
			
			statement = connection.prepareStatement(userSql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, newUser.getFirstName().toUpperCase());
			statement.setString(2, newUser.getLastName().toUpperCase());
			statement.setString(3, newUser.getUsername().toUpperCase());
			
			statement.executeUpdate();
			statement.clearParameters();
			
			ResultSet keyResult = statement.getGeneratedKeys();
			Long id = 0L; 
			
			if (keyResult.next()) {
				id = keyResult.getLong("GENERATED_KEY");
			}
			
			String credentialSql = "INSERT INTO CREDENTIAL( "
					+ " PASSWORD, USER_PROFILE_ID, ACTIVITY_DATE ) "
					+ "VALUES( ?, ?, NOW() )";
			
			statement = connection.prepareStatement(credentialSql);
			statement.setString(1, newUser.getCredential().getPassword());
			statement.setLong(2, id);
			
			statement.execute();
			
			newUser.setId(id);
			
			System.out.println("NEW USER CREATED SUCCESSFULLY ----- ");
			
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		} finally {
			closeResources();
		}
		return newUser;
	}

	
	@Override
	public boolean saveCoinAsFavorite(RWFavorite userFav) {
		
		try {
			connection = DAOUtils.getConnection();
			String sqlOne = "SELECT MARKET_NAME, EXCHANGE_NAME FROM COIN WHERE COIN_ID = ?";
			String sqlThree = "INSERT INTO USER_COIN_FAVORITE( "
					+ "EXCHANGE_NAME, MARKET_NAME, USER_PROFILE_ID, ADD_DATE, ACTIVE ) "
					+ "VALUES (?, ?, ?, NOW(), ? )";
			
			// (1a) GET MARKET-NAME FROM COIN USING COIN_ID
			// (1b) GET EXCHANGE_NAME FROM COIN USING COIN_ID
			String marketName = "";
			String exchangeName = "";
			statement = connection.prepareStatement(sqlOne);
			statement.setLong(1, userFav.getCoinId());
			ResultSet rs = statement.executeQuery();
			statement.clearParameters();
						
			// (2) INSERT INTO USER_COIN_FAVORITE
			statement = connection.prepareStatement(sqlThree);
			
			if(rs.next()) {
				marketName = rs.getString("MARKET_NAME");
				exchangeName = rs.getString("EXCHANGE_NAME");
			}
						
			statement.setString(1, exchangeName);
			statement.setString(2, marketName);
			statement.setLong(3, userFav.getId());
			statement.setBoolean(4, true);
			
			return statement.execute();
			
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		} finally {
			closeResources();
		}
		return false;
		
	}
	
	
	@Override
	public boolean saveApiKey(RWApiCredential credential) {
		
		try {
			connection = DAOUtils.getConnection();
			String sqlInsert1 = "INSERT INTO API_CREDENTIAL( "
					+ "API_KEY, EXCHANGE_NAME, SECRET_KEY, SET_PRIMARY, USER_PROFILE_ID) "
					+ "VALUES( ?, ?, ?, ?, ? ) ";
			
			statement = connection.prepareStatement(sqlInsert1);
			statement.setString(1, credential.getApiKey());
			statement.setString(2, credential.getExchangeName() );
			statement.setString(3, credential.getSecretKey() );
			statement.setBoolean(4, true);
			statement.setLong(5, credential.getId());
			
			return statement.execute();
			
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		}
		return false;
	}
	
	@Override
	public boolean addFriend(SocialNetwork friend) {
		
		try {
			connection = DAOUtils.getConnection();
			String sqlInsert = "INSERT INTO SOCIAL_NETWORK( "
					+ "EMAIL, FIRST_NAME, LAST_NAME, IS_ACTIVE, "
					+ "ENABLE_SEND_TO, MESSAGE, USER_PROFILE_ID ) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ? )";
			
			statement = connection.prepareStatement(sqlInsert);
			statement.setString(1, friend.getEmail());
			statement.setString(2, friend.getFirstName());
			statement.setString(3, friend.getLastName());
			statement.setBoolean(4, friend.isActive());
			statement.setBoolean(5, friend.isEnableSendTo());
			statement.setString(6, friend.getMessage());
			statement.setLong(7, friend.getId());
			
			return statement.execute();
			
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		}
		return false;
	}
	
	@Override
	public boolean addExternalWallet(RWExternalWallet walletDetail) {
		
		boolean isSuccess = true;
		try {
			connection = DAOUtils.getConnection();
			String sqlSelect = "SELECT MARKET_CURRENCY, MARKET_CURRENCY_LONG FROM COIN";
			
			String sqlInsert1 = "INSERT IGNORE INTO ACCOUNTS ( "
					+ "CURRENCY, EXCHANGE_NAME, ADD_DATE, USER_PROFILE_ID ) VALUES (?, ?, NOW(), ?)";
			
			String sqlInsert3 = "INSERT INTO DEPOSIT_ADDRESS ( "
					+ "ADDRESS, CURRENCY, EXCHANGE_NAME) VALUES("
					+ "?, ?, ? ) "
					+ "ON DUPLICATE KEY UPDATE EXCHANGE_NAME = ?";
			
			String sqlInsert4 = "INSERT INTO BALANCE ( "
					+ "ACCOUNT_BALANCE, AVAILABLE, BALANCE_DATE, CRYPTO_ADDRESS, CURRENCY_NAME, CURRENCY, EXCHANGE_NAME ) "
					+ "VALUES( ?, ?, NOW(), ?, ?, ?, ? ) "
					+ "ON DUPLICATE KEY UPDATE "
					+ "ACCOUNT_BALANCE = ?, "
					+ "AVAILABLE = ?, "
					+ "BALANCE_DATE = NOW(), "
					+ "CRYPTO_ADDRESS = ?, "
					+ "CURRENCY_NAME = ?, "
					+ "CURRENCY = ?, "
					+ "EXCHANGE_NAME = ? ";
			

			// ADD TO DEPOSIT HISTORY
			String sqlInsert5 = "INSERT INTO DEPOSIT_HISTORY_ENTRY ( "
					+ "TX_ID, AMOUNT, CRYPTO_ADDRESS, "
					+ "CURRENCY_NAME, LAST_UPDATED, CURRENCY, EXCHANGE_NAME ) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?) "
					+ "ON DUPLICATE KEY UPDATE "
					+ "AMOUNT = ?, "
					+ "LAST_UPDATED = ?";
					
			// ADD TO RETURN HISTORY
			
			
			statement = connection.prepareStatement(sqlSelect);
			ResultSet rsSelect = statement.executeQuery();
			statement.clearParameters();
			
			// TODO - REFACTOR TO CHECK IF COIN IS VALID, RATHER THAN LOOP THROUGH EVERY COIN
			
			
			while (rsSelect.next()) {
				String marketCurrency = rsSelect.getString("MARKET_CURRENCY").toUpperCase();
				String marketCurrencyLong = rsSelect.getString("MARKET_CURRENCY_LONG").toUpperCase();
				
				// PERFORM INSERT 1
				if (marketCurrency.equals(walletDetail.getCoinName().toUpperCase()) 
						|| marketCurrencyLong.equals(walletDetail.getCoinName().toUpperCase() )) {
					statement = connection.prepareStatement(sqlInsert1);
					statement.setString(1, marketCurrency);
					statement.setString(2, walletDetail.getExchangeName().name());
					statement.setLong(3, walletDetail.getUserId());
					isSuccess = statement.execute();
					statement.clearParameters();
				}
				

				// PERFORM INSERT 3
				if (!isSuccess) {
					statement = connection.prepareStatement(sqlInsert3);
					statement.setString(1, walletDetail.getCoinDepositAddress());
					statement.setString(2, walletDetail.getCoinName().toUpperCase());
					statement.setString(3, walletDetail.getExchangeName().name());
					statement.setString(4, walletDetail.getExchangeName().name() );
					isSuccess = statement.execute();
					statement.clearParameters();
				}
				
				
				// PERFORM INSERT 4
				if (!isSuccess) {
					statement = connection.prepareStatement(sqlInsert4);
					statement.setDouble(1, walletDetail.getCoinBalance());
					statement.setDouble(2, walletDetail.getCoinBalance());
					statement.setString(3, walletDetail.getCoinDepositAddress());
					statement.setString(4, marketCurrency);
					statement.setString(5, marketCurrency);
					statement.setString(6, walletDetail.getExchangeName().name());
					
					statement.setDouble(7, walletDetail.getCoinBalance());
					statement.setDouble(8, walletDetail.getCoinBalance());
					statement.setString(9, walletDetail.getCoinDepositAddress());
					statement.setString(10, marketCurrency);
					statement.setString(11, marketCurrency);
					statement.setString(12, walletDetail.getExchangeName().name());
					isSuccess = statement.execute();
					statement.clearParameters();
				}
				
								
				// PERFORM INSERT 5
				if(!isSuccess) {
					statement = connection.prepareStatement(sqlInsert5);
					Date date = new Date();
					statement.setString(1, "00000001_Wallet");
					statement.setDouble(2, walletDetail.getCoinBalance());
					statement.setString(3, walletDetail.getCoinDepositAddress());
					statement.setString(4, walletDetail.getCoinName());
					statement.setTimestamp(5, new Timestamp(date.getTime()) );
					statement.setString(6, walletDetail.getCoinName());					
					statement.setString(7, walletDetail.getExchangeName().name());
					
					statement.setDouble(8, walletDetail.getCoinBalance());
					statement.setTimestamp(9, new Timestamp(date.getTime()));
					isSuccess = statement.execute();
					break;
				}

			}
			
			rsSelect.close();
			return isSuccess;
			
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		}
		return isSuccess;
		
	}
	
	public boolean addExternalEmail(EmailProvider emailProvider) {
		
		boolean isSuccess = true;
		try {
			connection = DAOUtils.getConnection();
			String sqlInsert = "INSERT INTO EMAIL_PROVIDER( "
					+ "ACCOUNT_NOTE, ADD_DATE, EMAIL_FROM, EMAIL_FROM_PASSWORD, "
					+ "PROVIDER_NAME, USER_PROFILE_ID ) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ? )";
			
			Date date = new Date();
			statement = connection.prepareStatement(sqlInsert);
			
			statement.setString(1, emailProvider.getAccountNote());
			statement.setTimestamp(2, new Timestamp(date.getTime()));
			statement.setString(3, emailProvider.getEmailFrom());
			statement.setString(4, emailProvider.getEmailFromPassword() );
			statement.setString(5, emailProvider.getEmailProviderName().name() );
			statement.setLong(6, emailProvider.getUserId() );
			return statement.execute();
			
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		}
		return isSuccess;
	}

	//============================================
	//=== UPDATE
	//============================================
	
	@Override
	public boolean updateProfile(RWLoginDetail userDetail) {
		
		try {
			connection = DAOUtils.getConnection();
			String sqlUpdate1 = "UPDATE USER_PROFILE SET FIRST_NAME = ? WHERE USER_PROFILE_ID = ?; ";
			String sqlUpdate2 = "UPDATE USER_PROFILE SET LAST_NAME = ? WHERE USER_PROFILE_ID = ?; ";
			String sqlUpdate3 = "UPDATE USER_PROFILE SET USERNAME = ? WHERE USER_PROFILE_ID = ?; ";
			
			statement = connection.prepareStatement(sqlUpdate1);
			statement.setString(1, userDetail.getFirstName() );
			statement.setLong(2, userDetail.getId());
			int result1 = statement.executeUpdate();
			statement.clearParameters();
			
			statement = connection.prepareStatement(sqlUpdate2);
			statement.setString(1, userDetail.getLastName());
			statement.setLong(2, userDetail.getId());	
			int result2 = statement.executeUpdate();
			statement.clearParameters();
			
			statement = connection.prepareStatement(sqlUpdate3);
			statement.setString(1, userDetail.getUserName());
			statement.setLong(2, userDetail.getId());	
			
			int result3 = statement.executeUpdate();

			if ( (result1 & result2 & result3) == 1) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		}
		return false;
	}
	
	
	@Override
	public boolean updatePassword(RWPassword password) {

		try {
			connection = DAOUtils.getConnection();
			String sqlCompare = "SELECT PASSWORD FROM CREDENTIAL WHERE USER_PROFILE_ID = ?";
			String sqlUpdate = "UPDATE CREDENTIAL SET PASSWORD = ? WHERE USER_PROFILE_ID = ?";
			
			statement = connection.prepareStatement(sqlCompare);
			statement.setLong(1, password.getId());
			ResultSet rs1 = statement.executeQuery();
			statement.clearParameters();
			if (rs1.next()) {
				if (rs1.getString("PASSWORD").equals(password.getCurrentPassword())) {
					statement = connection.prepareStatement(sqlUpdate);
					statement.setString(1, password.getNewPassword());
					statement.setLong(2, password.getId());
					return statement.execute();
				}
			}
			rs1.close();
			
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		}
		return false;
		
	}
	
	
	//============================================
	//=== DELETE
	//============================================
	
	@Override
	public boolean deleteFriend(Long friendId) {
		
		try {
			
			connection = DAOUtils.getConnection();
			String sqlDelete = "DELETE FROM SOCIAL_NETWORK WHERE SOCIAL_NETWORK_ID = ?";
			
			statement = connection.prepareStatement(sqlDelete);
			statement.setLong(1, friendId);
			return statement.execute();
			
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		}
		return false;
	}
	
	@Override
	public boolean deleteCoinFavorite(Long userCoinFavId) {
		boolean result = false;
		
		 try {
			 connection = DAOUtils.getConnection();
			 String sqlDelete = "DELETE FROM USER_COIN_FAVORITE "
			 		+ "WHERE USER_COIN_FAVORITE_ID = ?";
			 
			 statement = connection.prepareStatement(sqlDelete);
			 statement.setLong(1, userCoinFavId);
			 return statement.execute();
			 
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		} finally {
			closeResources();
		}
		return result;
		 
	}
	
	@Override
	public boolean deleteExternalEmail(Long emailId) {
		boolean result = false;
		
		 try {
			 connection = DAOUtils.getConnection();
			 String sqlDelete = "DELETE FROM EMAIL_PROVIDER "
			 		+ "WHERE EMAIL_PROVIDER_ID = ?";
			 
			 statement = connection.prepareStatement(sqlDelete);
			 statement.setLong(1, emailId);
			 return statement.execute();
			 
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		} finally {
			closeResources();
		}
		return result;
		 
	}
	
	@Override
	public boolean deleteExternalWallet(Long walletId) {
		boolean result = false;
		
		try {
			connection = DAOUtils.getConnection();
			String sqlSelect1 = "SELECT * FROM ACCOUNTS WHERE ACCOUNT_ID = ?";
			
			String sqlDelete1 = "DELETE FROM BALANCE WHERE EXCHANGE_NAME = ? AND CURRENCY = ? ";
			
			statement = connection.prepareStatement(sqlSelect1);
			statement.setLong(1, walletId);
			return statement.execute();
			
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		} finally {
			closeResources();
		}
		return result;
		
	}
	
	//============================================
	//=== RETRIEVE
	//============================================
	
	@Override
	public RWLoginDetail loginUser(String username, String password) {
		
		RWLoginDetail loginDetail = new RWLoginDetail();
		try {
			connection = DAOUtils.getConnection();
			String sql = "SELECT A.*, B.TOKEN "
					+ "FROM USER_PROFILE A "
					+ "JOIN CREDENTIAL B "
					+ "ON A.USER_PROFILE_ID = B.USER_PROFILE_ID "
					+ "WHERE A.USERNAME = ? "
					+ "AND B.PASSWORD = ? ";
			
			statement = connection.prepareStatement(sql);
			statement.setString(1, username.toUpperCase());
			statement.setString(2, password);
			
			ResultSet rs = statement.executeQuery();			
		
			if (rs.next()) {
				loginDetail.setFirstName(rs.getString("FIRST_NAME"));
				loginDetail.setLastName(rs.getString("LAST_NAME"));
				loginDetail.setId(rs.getLong("USER_PROFILE_ID"));
				loginDetail.setActive(rs.getBoolean("IS_ACTIVE"));
				loginDetail.setUserName(rs.getString("USERNAME"));
				loginDetail.setActive(rs.getBoolean("IS_ACTIVE"));	
				loginDetail.setToken(rs.getString("TOKEN"));
			}
			
			return loginDetail;
			
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		} finally {
			closeResources();
		}
		
		return loginDetail;
	}


	@Override
	public List<SocialNetwork> getFriendList(Long userId) {
		
		List<SocialNetwork> socialNetworkList = null;
		
		try {
			connection = DAOUtils.getConnection();
			String sqlSelect = "SELECT * FROM SOCIAL_NETWORK WHERE USER_PROFILE_ID = ? ";
			
			statement = connection.prepareStatement(sqlSelect);
			statement.setLong(1, userId);
			ResultSet rs = statement.executeQuery();
			
			socialNetworkList = new ArrayList<>();
			while (rs.next()) {
				SocialNetwork sn = new SocialNetwork();
				sn.setEmail( rs.getString("EMAIL"));
				sn.setFirstName( rs.getString("FIRST_NAME" ));
				sn.setLastName( rs.getString("LAST_NAME") );
				sn.setId( rs.getLong( "SOCIAL_NETWORK_ID") );
				sn.setActive( rs.getBoolean("IS_ACTIVE"));
				sn.setEnableSendTo( rs.getBoolean("ENABLE_SEND_TO") );
				sn.setMessage( rs.getString( "MESSAGE" ) );
				socialNetworkList.add(sn);
			}
			rs.close();
			
			return socialNetworkList;
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		} finally {
			closeResources();
		}
		
		return socialNetworkList;
	}
	
	
	@Override
	@SuppressWarnings("null")
	public List<FavoriteCoinView> getFavoriteCoins(Long userId) {
		
		List<FavoriteCoinView> favCoinList = null;
		try {
			connection = DAOUtils.getConnection();
			String sqlSelect1 = "SELECT * FROM USER_COIN_FAVORITE WHERE USER_PROFILE_ID = ?";
			
			String sqlSelect2 = "SELECT A.LOGO_URL, A.MARKET_CURRENCY_LONG, A.COIN_ID, "
					+ "B.VOLUME, B.ASK, B.BID "
					+ "FROM COIN A "
					+ "JOIN MARKET_SUMMARY B "
					+ "ON A.MARKET_NAME = B.MARKET_NAME "
					+ "WHERE A.MARKET_NAME = ?";
			
			statement = connection.prepareStatement(sqlSelect1);
			statement.setLong(1, userId);
			ResultSet rs1 = statement.executeQuery();
			statement.clearParameters();
			
			favCoinList = new ArrayList<>();
			
			while (rs1.next()) {
				statement = connection.prepareStatement(sqlSelect2);
				statement.setString(1, rs1.getString("MARKET_NAME"));
				ResultSet rs2 = statement.executeQuery();			
				statement.clearParameters();
				
				if (rs2.next()) {
					FavoriteCoinView favorite = new FavoriteCoinView();
					favorite.setLogoUrl(rs2.getString("LOGO_URL"));
					favorite.setCoinId(rs2.getLong("COIN_ID"));
					favorite.setMarketCurrencyLong(rs2.getString("MARKET_CURRENCY_LONG"));
					favorite.setVolume(rs2.getDouble("VOLUME"));
					favorite.setAsk(rs2.getDouble("ASK"));
					favorite.setBid(rs2.getDouble("BID"));
					favorite.setUserCoinFavoriteId(rs1.getLong("USER_COIN_FAVORITE_ID"));
					favCoinList.add(favorite);
				}
				rs2.close();
			}
			rs1.close();
			return favCoinList;
			
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		} finally {
			closeResources();
		}
		
		return favCoinList;
	}
	

	@Override
	public List<RWExternalWallet> getExternalWallets(Long userId) {
		
		List<RWExternalWallet> walletList = null;
		try {

			connection = DAOUtils.getConnection();
			String sqlSelect1 = "SELECT A.*, B.* FROM ACCOUNTS A "
					+ "JOIN BALANCE B "
					+ "WHERE A.EXCHANGE_NAME = ? "
					+ "AND B.EXCHANGE_NAME = ? " 
					+ "AND A.CURRENCY = B.CURRENCY "
					+ "AND USER_PROFILE_ID = ? ";
			
		
			statement= connection.prepareStatement(sqlSelect1);
			statement.setString(1, "WALLET");
			statement.setString(2, "WALLET");
			statement.setLong(3, userId);
			ResultSet rs1 = statement.executeQuery();
			statement.clearParameters();
			
			walletList = new ArrayList<>();
			while (rs1.next() ) {
				RWExternalWallet wallet = new RWExternalWallet();
				wallet.setUserId(userId);
				wallet.setId(rs1.getLong("BALANCE_ID"));
				wallet.setWalletId(rs1.getLong("ACCOUNT_ID"));
				wallet.setCoinName(rs1.getString("CURRENCY_NAME"));
				wallet.setCoinBalance(rs1.getDouble("ACCOUNT_BALANCE"));
				wallet.setCoinDepositAddress(rs1.getString("CRYPTO_ADDRESS"));
				wallet.setCoinWithdrawalAddress(rs1.getString("CRYPTO_ADDRESS"));
				wallet.setExchangeName( ExchangeName.valueOf(rs1.getString("EXCHANGE_NAME")));
				walletList.add(wallet);
			}
			rs1.close();
			return walletList;
			
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		} finally {
			closeResources();
		}
		
		return walletList;
	}
	
	
	
	@Override
	public List<EmailProvider> getExternalEmails(Long userId) {
		
		List<EmailProvider> externalEmails = null;
		
		try {
			connection = DAOUtils.getConnection();
			String sqlInsert = "SELECT * FROM EMAIL_PROVIDER WHERE USER_PROFILE_ID = ? ";
			
			statement = connection.prepareStatement(sqlInsert);
			statement.setLong(1, userId);
			ResultSet rs = statement.executeQuery();
			
			externalEmails = new ArrayList<>();
			while (rs.next() ) {
				
				EmailProvider email = new EmailProvider();
				email.setAccountNote(rs.getString("ACCOUNT_NOTE"));
				email.setAddDate(rs.getDate("ADD_DATE"));
				email.setEmailFrom(rs.getString("EMAIL_FROM"));
				email.setId(rs.getLong("EMAIL_PROVIDER_ID") );
				email.setEmailProviderName(EmailProviderName.valueOf(rs.getString("PROVIDER_NAME")));
				email.setUserId(userId);
				externalEmails.add(email);
			}
			
			rs.close();
			return externalEmails;
		
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		} finally {
			closeResources();
		}
		return externalEmails;
	}
	
	//============================================
	//=== VALIDATE
	//============================================
	
	@Override
	public boolean checkIfUserExists(String username) {
		
		try {
			connection = DAOUtils.getConnection();
			String sql = "SELECT USERNAME "
					+ "FROM USER_PROFILE "
					+ "WHERE USERNAME = ? ;";
			
			statement = connection.prepareStatement(sql);
			statement.setString(1, username.toUpperCase());
			
			ResultSet rs = statement.executeQuery();

			if (rs.first()) {
				return true;
			}
			
		} catch(SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage() );
		} finally {
			closeResources();
		}
		return false;
	}

	
	
	//============================================
	//=== MISC.
	//============================================
	private void closeResources() {
		
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException ex) {
			System.out.println("Could not close statement!");
			ex.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException ex) {
			System.out.println("Could not close connection!");
			ex.printStackTrace();
		}
	}

}
