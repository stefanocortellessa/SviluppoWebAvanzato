package it.univaq.disim.swa.visitaq.business.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import it.univaq.disim.swa.visitaq.business.VisitaqBusinessException;
import it.univaq.disim.swa.visitaq.business.AccountResourceService;
import it.univaq.disim.swa.visitaq.domain.Attraction;
import it.univaq.disim.swa.visitaq.domain.Category;
import it.univaq.disim.swa.visitaq.domain.Session;
import it.univaq.disim.swa.visitaq.domain.User;

public class AccountResourceServiceImpl implements AccountResourceService {

	final private String port = "localhost:8889/visitaq";
	final private String user = "root";
	final private String pwd = "root";
	final private String timeZone = TimeZone.getTimeZone("Europe/Rome").getID();

	public Connection getConnection(Connection connection) {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				//System.out.println("Errore Connessione");
				e.printStackTrace();
			}
			connection = DriverManager.getConnection("jdbc:mysql://" + port + "?user=" + user + "&password=" + pwd
					+ "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone="
					+ timeZone);

			//System.out.println("Connection established");
		} catch (Exception e) {
			//System.out.println("SQLException: " + e.getMessage());
		}
		return connection;
	}
	
	@Override
	public Boolean insertUser(User user) throws VisitaqBusinessException {

		Connection connection = null;
		PreparedStatement userPs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Boolean response = false;
		Utility utility = new Utility();
		String sql = "SELECT * FROM users WHERE email = ?";
		String insertSql = "INSERT INTO users (name,surname,email,password) VALUES (?,?,?,?)";
		
		try {
			connection = this.getConnection(connection);
			
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			
			rs = ps.executeQuery();
			rs.last();
			
			int num_rows = rs.getRow();
			rs.first();
			
			// if inserted email already exists, don't insert the user!
			if(num_rows > 0 ) {
				
				response = false; 
			// else insert user on db
			}else {
				userPs = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
	
				userPs.setString(1, user.getName());
				userPs.setString(2, user.getSurname());
				userPs.setString(3, user.getEmail());
				//password hashed using google.common.hash functions
				userPs.setString(4, utility.hashPwd(user.getPassword()));
	
				userPs.executeUpdate();

				response = true;
			}
			return response;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VisitaqBusinessException("Something went wrong with User Signup");
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if (userPs != null) {
				try {
					userPs.close();
				} catch (SQLException e) {
				}
			}
			if (connection != null) {
				try {
					//System.out.println("connection close");
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	@Override
	public Boolean deleteUser(Long id) throws VisitaqBusinessException {

		Connection connection = null;
		PreparedStatement ps = null;
	
		Boolean response = false;
		String deleteSql = "DELETE FROM users WHERE id = ?";
		
		try {

			connection = this.getConnection(connection);
			
			ps = connection.prepareStatement(deleteSql);
			ps.setLong(1, id);
			
			if(ps.executeUpdate() == 1) {
				response = true;
				//System.out.println("User deleted successfully!");
			}else {
				response = false;
				//System.out.println("User NOT deleted");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VisitaqBusinessException("Something went wrong with User Deleting");
		} finally {
			if (ps != null) {
				try {
					//System.out.println("ps close");
					ps.close();
				} catch (SQLException e) {
				}
			}
			if (connection != null) {
				try {
					//System.out.println("connection close");
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
		return response;
	}
	
	@Override
	public User updateUser(User user, Long userId) throws VisitaqBusinessException {

		Connection connection = null;
		PreparedStatement ps = null;
		
		User response = new User();
		Utility utility = new Utility();
		String updateSql = "UPDATE users SET name=?, surname=?, email=?, password=? WHERE id=?";
		
		try {

			connection = this.getConnection(connection);
			
			ps = connection.prepareStatement(updateSql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getSurname());
			ps.setString(3, user.getEmail());
			ps.setString(4, utility.hashPwd(user.getPassword()));
			ps.setLong(5, userId);
			
			if(ps.executeUpdate() == 1) {
				//System.out.println("User updated successfully!");
			}else {
				//System.out.println("User NOT updated");
			}
			response.setId(user.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VisitaqBusinessException("Something went wrong with User Deleting");
		} finally {
			if (ps != null) {
				try {
					//System.out.println("ps close");
					ps.close();
				} catch (SQLException e) {
				}
			}
			if (connection != null) {
				try {
					//System.out.println("connection close");
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
		return response;
	}
	
	@Override
	public Session loginUser(User user) throws VisitaqBusinessException {
		
		Session response = new Session();
		String selectUserSql = "SELECT * FROM users WHERE email = ?";
		String sqlSession = "INSERT into sessions(token, user_id) VALUES(?,?)";
		Utility utility = new Utility();
		
		Connection connection = null;
		PreparedStatement ps = null;
		PreparedStatement sessionPs = null;
		ResultSet rs = null;

		try {

			connection = this.getConnection(connection);
			ps = connection.prepareStatement(selectUserSql);

			ps.setString(1, user.getEmail());
			rs = ps.executeQuery();
			rs.last();
			
			int num_rows = rs.getRow();
			rs.first();
			
			// if the user email exists on the db and the inserted password is correct (hashed)
			if(num_rows != 0 && utility.hashPwd(user.getPassword()).equals(rs.getString("password"))) {
				
				long idUtente = rs.getLong("id");
				
				user.setId(idUtente);
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				
				// generate session token
				String token = Utility.generateToken();

				sessionPs = connection.prepareStatement(sqlSession);
				sessionPs.setString(1, token);
				sessionPs.setLong(2, user.getId());

				if(sessionPs.executeUpdate() == 1) {	
					response.setId(idUtente);
					response.setToken(token);
				} else {
					response.setToken(null);
				}
			} else {
				response.setToken(null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VisitaqBusinessException("Something was wrong with User Login");
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		return response;
	}
	
	@Override
	public Boolean logoutUser(String token) throws VisitaqBusinessException {

		Connection connection = null;
		PreparedStatement ps = null;
		
		Boolean response = false;
		String sql = "DELETE FROM sessions WHERE token = ?";

		try {			
			connection = this.getConnection(connection);
			
			ps = connection.prepareStatement(sql);
			ps.setString(1, token);
			
			if(ps.executeUpdate() == 1) {
				response = true;
				//System.out.println("Logout Success");
			} else {
				response = false;
				//System.out.println("Logout went Wrong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VisitaqBusinessException("Something was wrong with User Logout");
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		return response;
	}
	
	@Override
	public Boolean checkSession(String token) throws VisitaqBusinessException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM sessions WHERE token = ?";
		Boolean response;

		try {			
			
			connection = this.getConnection(connection);
			
			ps = connection.prepareStatement(sql);
			ps.setString(1, token);
			rs = ps.executeQuery();
			rs.last();
			
			int num_rows = rs.getRow();

			if(num_rows != 0) {
				response = true;
			} else {
				response = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VisitaqBusinessException("Something was wrong with Checking Session");
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		return response;
	}
	
	@Override
	public List<User> selectAllUsers() {

		PreparedStatement sql = null;
		Connection connection = null;
		List<User> response = new ArrayList<User>();
		
		String query = "SELECT * FROM users";
		
		try {

			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			sql = connection.prepareStatement(query);
			
			ResultSet rs = sql.executeQuery();
			
			while(rs.next()) {
				
				User user = new User();
				
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
								
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				
				response.add(user);		
			}
			rs.close(); 
			
			return response;
		} catch (SQLException e) {
			return response;
		}finally {
			if (sql != null) {
				try {
					sql.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	@Override
	public User selectUserByEmail(String email) {

		PreparedStatement sql = null;
		Connection connection = null;
		
		User user = new User();
		String query = "SELECT * FROM users WHERE email=?";
		
		try {
			
			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			sql = connection.prepareStatement(query);

			sql.setString(1, email);

			ResultSet rs = sql.executeQuery();
			
			while(rs.next()) {
				
				//System.out.println("RSSSS : " + rs.getString("categoriesName"));
				
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
								
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));				
			}
			return user;
		} catch (SQLException e) {
			return user;
		}finally {
			if (sql != null) {
				try {
					sql.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}