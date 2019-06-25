package it.univaq.disim.swa.visitaq.business.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import it.univaq.disim.swa.visitaq.business.AttractionResourceService;
import it.univaq.disim.swa.visitaq.business.VisitaqBusinessException;
import it.univaq.disim.swa.visitaq.domain.Attraction;
import it.univaq.disim.swa.visitaq.domain.Category;
import it.univaq.disim.swa.visitaq.domain.User;

public class AttractionResourceServiceImpl implements AttractionResourceService {

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
	public Attraction insertAttraction(Attraction attraction) throws VisitaqBusinessException {

		PreparedStatement ps = null;
		Connection connection = null;
		
		Attraction response = new Attraction();
		String query = "INSERT INTO attractions (name, locality, id_category, id_creator, lat, lng, description, image) VALUES (?,?,?,?,?,?,?,?)";

		try {

			connection = this.getConnection(connection);
			connection.setAutoCommit(false);

			ps = connection.prepareStatement(query);
			ps.setString(1, attraction.getName());
			ps.setString(2, attraction.getLocality());
			ps.setLong(3, attraction.getCategory().getId());
			ps.setLong(4, attraction.getCreator().getId());
			ps.setString(5, attraction.getLat());
			ps.setString(6, attraction.getLng());
			ps.setString(7, attraction.getDescription());
			ps.setString(8, attraction.getImage());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VisitaqBusinessException("Something was wrong with Insert an Attraction..");
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
		response.setName(attraction.getName());

		return response;
	}

	@Override
	public Boolean deleteAttraction(Long attractionId, Attraction attraction) throws VisitaqBusinessException {

		PreparedStatement ps = null;
		Connection connection = null;
		
		Utility utility = new Utility();
		Boolean response = false;
		String query = "DELETE FROM attractions WHERE id = ?";
		
		try {

			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			if (utility.checkCreatorAttractions(connection, attractionId, attraction.getCreator().getId())) {
				
				ps = connection.prepareStatement(query);
				
				ps.setLong(1, attractionId);
				
				if(ps.executeUpdate() == 1) {
					response = true;
					//System.out.println("User deleted successfully!");
				}else {
					response = false;
					//System.out.println("User NOT deleted");
				}
				//System.out.println("Attraction Deleted");
			} else {
				//System.out.println("Attraction not Deleted : user is not its creator");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new VisitaqBusinessException("Something was wrong with Deleting an Attraction..");
		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
		return response;
	}
	
	@Override
	public Attraction updateAttraction(Attraction attraction, Long attractionId) throws VisitaqBusinessException {
		
		PreparedStatement ps = null;
		Connection connection = null;
		
		Attraction response = new Attraction();
		Utility utility = new Utility();
		String query = "UPDATE attractions SET name=?, locality=?, id_category=?, id_creator=?, lat=?, lng=?, description=?, image=? WHERE id=?";
		
		try {

			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			if(utility.checkCreatorAttractions(connection, attractionId, attraction.getCreator().getId())) {	
				
				ps = connection.prepareStatement(query);

				ps.setString(1, attraction.getName());
				ps.setString(2, attraction.getLocality());
				ps.setLong(3, attraction.getCategory().getId());
				ps.setLong(4, attraction.getCreator().getId());
				ps.setString(5, attraction.getLat());
				ps.setString(6, attraction.getLng());
				ps.setString(7, attraction.getDescription());
				ps.setString(8, attraction.getImage());
				ps.setLong(9, attractionId);

				if(ps.executeUpdate() == 1) {
					//System.out.println("Attraction updated");
				}else {
					//System.out.println("Attraction not updated");
				}
			}else {
				//System.out.println("Attraction not udpated: user is not its creator");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new VisitaqBusinessException("Something was wrong with Update an Attraction..");
		}
		finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
		}
		response.setName(attraction.getName());

		return response;
	}
	
	@Override
	public Attraction selectAttractionDetail(Long id) {

		PreparedStatement sql = null;
		Connection connection = null;
		
		Attraction attraction = new Attraction();
		Category category = new Category();
		User creator = new User();
		String query = "SELECT * FROM attractions WHERE id=?";
		
		try {
			
			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			sql = connection.prepareStatement(query);

			sql.setLong(1, id);

			ResultSet rs = sql.executeQuery();
			
			while(rs.next()) {
				
				//System.out.println("RSSSS : " + rs.getString("categoriesName"));
				
				attraction.setId(rs.getLong("id"));
				attraction.setName(rs.getString("name"));
				attraction.setLocality(rs.getString("locality"));
				
				category.setId(rs.getLong("id_category"));
				attraction.setCategory(category);
				creator.setId(rs.getLong("id_creator"));
				attraction.setCreator(creator);
				
				attraction.setLat(rs.getString("lat"));
				attraction.setLng(rs.getString("lng"));
				attraction.setDescription(rs.getString("description"));
				attraction.setImage(rs.getString("image"));
				
			}
			return attraction;
		} catch (SQLException e) {
			return attraction;
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
	public List<Attraction> selectAttractions() {

		PreparedStatement sql = null;
		Connection connection = null;
		List<Attraction> response = new ArrayList<Attraction>();
		
		String query = "SELECT * FROM attractions";
		
		try {

			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			sql = connection.prepareStatement(query);
			
			ResultSet rs = sql.executeQuery();
			
			while(rs.next()) {
				
				Attraction attraction = new Attraction();
				Category category = new Category();
				User creator = new User();
				
				attraction.setId(rs.getLong("id"));
				attraction.setName(rs.getString("name"));
				attraction.setLocality(rs.getString("locality"));
				
				category.setId(rs.getLong("id_category"));
				attraction.setCategory(category);
				creator.setId(rs.getLong("id_creator"));
				attraction.setCreator(creator);
				
				attraction.setLat(rs.getString("lat"));
				attraction.setLng(rs.getString("lng"));
				attraction.setDescription(rs.getString("description"));
				attraction.setImage(rs.getString("image"));
				
				response.add(attraction);		
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
	public List<Attraction> selectAttractionsByUser(Long id) {

		PreparedStatement sql = null;
		Connection connection = null;
		List<Attraction> response = new ArrayList<Attraction>();
		
		String query = "SELECT * FROM attractions WHERE id_creator=?";
		
		try {

			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			sql = connection.prepareStatement(query);

			sql.setLong(1, id);

			ResultSet rs = sql.executeQuery();
			
			while(rs.next()) {
				
				Attraction attraction = new Attraction();
				Category category = new Category();
				User creator = new User();
				
				attraction.setId(rs.getLong("id"));
				attraction.setName(rs.getString("name"));
				attraction.setLocality(rs.getString("locality"));
				
				category.setId(rs.getLong("id_category"));
				attraction.setCategory(category);
				creator.setId(rs.getLong("id_creator"));
				attraction.setCreator(creator);
				
				attraction.setLat(rs.getString("lat"));
				attraction.setLng(rs.getString("lng"));
				attraction.setDescription(rs.getString("description"));
				attraction.setImage(rs.getString("image"));
				
				response.add(attraction);		
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
	public List<Attraction> selectAttractionsByCategory(Long id) {

		PreparedStatement sql = null;
		Connection connection = null;
		List<Attraction> response = new ArrayList<Attraction>();
		
		String query = "SELECT * FROM attractions WHERE id_category=?";
		
		try {

			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			sql = connection.prepareStatement(query);

			sql.setLong(1, id);

			ResultSet rs = sql.executeQuery();
			
			while(rs.next()) {
				
				Attraction attraction = new Attraction();
				Category category = new Category();
				User creator = new User();
				
				attraction.setId(rs.getLong("id"));
				attraction.setName(rs.getString("name"));
				attraction.setLocality(rs.getString("locality"));
				
				category.setId(rs.getLong("id_category"));
				attraction.setCategory(category);
				creator.setId(rs.getLong("id_creator"));
				attraction.setCreator(creator);
				
				attraction.setLat(rs.getString("lat"));
				attraction.setLng(rs.getString("lng"));
				attraction.setDescription(rs.getString("description"));
				attraction.setImage(rs.getString("image"));
				
				response.add(attraction);		
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
}