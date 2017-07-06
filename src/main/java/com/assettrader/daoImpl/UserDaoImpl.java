package com.assettrader.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.assettrader.dao.UserDao;
import com.assettrader.model.Address;
import com.assettrader.model.Credential;
import com.assettrader.model.UserProfile;
import com.assettrader.model.coinmarket.Coin;
import com.assettrader.model.rest.RWApiCredential;
import com.assettrader.model.rest.RWFavorite;
import com.assettrader.model.rest.RWLoginDetail;
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
					+ "EXCHANGE_NAME, MARKET_NAME, USER_PROFILE_ID) "
					+ "VALUES (?, ?, ? )";
			
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
	

	//============================================
	//=== UPDATE
	//============================================
	
	@Override
	public void updateUsername(String newUsername) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePassword(String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserAddress(Address newAddress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateIsActive() {
		// TODO Auto-generated method stub
		
	}

	
	//============================================
	//=== DELETES
	//============================================
	
	@Override
	public void deleteUser(UserProfile userProfile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFavoriteCoin(int favoriteId) {
		// TODO Auto-generated method stub
		
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
	public UserProfile getUser(int profileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile getUser(String searchParam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address getUserAddress(int profileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coin> getUserFavoriteCoins(int profileId) {
		// TODO Auto-generated method stub
		return null;
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
