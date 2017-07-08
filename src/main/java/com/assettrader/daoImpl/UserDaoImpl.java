package com.assettrader.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.stereotype.Repository;

import com.assettrader.dao.UserDao;
import com.assettrader.model.Address;
import com.assettrader.model.Credential;
import com.assettrader.model.SocialNetwork;
import com.assettrader.model.UserProfile;
import com.assettrader.model.coinmarket.Coin;
import com.assettrader.model.rest.RWApiCredential;
import com.assettrader.model.rest.RWFavorite;
import com.assettrader.model.rest.RWLoginDetail;
import com.assettrader.model.rest.RWPassword;
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
			statement.setString(1, newUser.getFirstName());
			statement.setString(2, newUser.getLastName());
			statement.setString(3, newUser.getUsername().toUpperCase());
			
			Long id = (long) statement.executeUpdate();
			statement.clearParameters();
			
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

	//============================================
	//=== UPDATE
	//============================================
	
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
	
	//============================================
	//=== DELETE
	//============================================
	
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
	
	
	public boolean updatePassword(RWPassword password) {
		int result = 0;
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
					+ "AND B.PASSWORD = ?;";
			
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
	
	//============================================
	//=== VALIDATE
	//============================================
	
	@Override
	public boolean checkIfUserExists(String username) {
		
		try {
			connection = DAOUtils.getConnection();
			String sql = "SELECT B.USERNAME "
					+ "FROM CREDENTIAL B "
					+ "WHERE B.USERNAME = ? ;";
			
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
