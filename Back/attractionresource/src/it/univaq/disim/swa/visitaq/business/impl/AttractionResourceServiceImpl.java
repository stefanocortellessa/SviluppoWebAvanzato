package it.univaq.disim.swa.visitaq.business.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import it.univaq.disim.swa.visitaq.business.AttractionResourceBusinessException;
import it.univaq.disim.swa.visitaq.business.AttractionResourceService;
import it.univaq.disim.swa.visitaq.domain.Attraction;

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
				System.out.println("Errore Connessione");
				e.printStackTrace();
			}

			connection = DriverManager.getConnection("jdbc:mysql://" + port + "?user=" + user + "&password=" + pwd
					+ "&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone="
					+ timeZone);

			System.out.println("Connection established");

		} catch (Exception e) {
			System.out.println("SQLException: " + e.getMessage());
		}
		return connection;
	}

	@Override
	public Attraction insertAttraction(Attraction attraction) throws AttractionResourceBusinessException {

		Connection connection = null;
		Attraction response = new Attraction();

		String query = "INSERT INTO attractions (name, locality, id_category, id_creator, lat, lng, description) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = null;

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

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AttractionResourceBusinessException("Something was wrong with Insert an Attraction..");
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
	public void deleteAttraction(Long attractionId, Attraction attraction) throws AttractionResourceBusinessException {

		Connection connection = null;
		Utility utility = new Utility();
		
		String query = "DELETE FROM attractions WHERE id = ?";
		PreparedStatement ps = null;
		
		try {

			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			if (utility.checkCreator(connection, attractionId, attraction.getCreator().getId())) {
				
				ps = connection.prepareStatement(query);
				
				ps.setLong(1, attractionId);
			
				ps.executeUpdate();
				System.out.println("Attraction Deleted");
			} else {
				System.out.println("Attraction not Deleted : user is not its creator");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AttractionResourceBusinessException("Something was wrong with Deleting an Attraction..");
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
	}
	
	@Override
	public Attraction updateAttraction(Attraction attraction, Long attractionId) throws AttractionResourceBusinessException {

		Connection connection = null;
		Attraction response = new Attraction();
		Utility utility = new Utility();
		
		String query = "UPDATE attractions SET name=?, locality=?, id_category=?, id_creator=?, lat=?, lng=?, description=? WHERE id=?";
		PreparedStatement ps = null;
		
		
		try {

			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			if(utility.checkCreator(connection, attractionId, attraction.getCreator().getId())) {	
				
				ps = connection.prepareStatement(query);

				ps.setString(1, attraction.getName());
				ps.setString(2, attraction.getLocality());
				ps.setLong(3, attraction.getCategory().getId());
				ps.setLong(4, attraction.getCreator().getId());
				ps.setString(5, attraction.getLat());
				ps.setString(6, attraction.getLng());
				ps.setString(7, attraction.getDescription());
				ps.setLong(8, attractionId);

				if(ps.executeUpdate() == 1) {
					System.out.println("Attraction updated");
				}else {
					System.out.println("Attraction not updated");
				}
			}else {
				System.out.println("Attraction not udpated: user is not its creator");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new AttractionResourceBusinessException("Something was wrong with Update an Attraction..");
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
	
	public Attraction selectAttractionDetail(Long id) {

		Connection connection = null;
		
		Attraction attraction = new Attraction();
		String query = "SELECT * FROM attractions WHERE id=?";
		PreparedStatement sql = null;

		try {
			
			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			sql = connection.prepareStatement(query);

			sql.setLong(1, id);

			ResultSet rs = sql.executeQuery();
			
			while(rs.next()) {
			
				attraction.setId(rs.getLong("id"));
				attraction.setName(rs.getString("name"));
				attraction.setLocality(rs.getString("locality"));
				attraction.setCategoryId(rs.getLong("id_category"));
				attraction.setCreatorId(rs.getLong("id_creator"));
				
				attraction.setLat(rs.getString("lat"));
				attraction.setLng(rs.getString("lng"));
				attraction.setDescription(rs.getString("description"));
				
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
	
	public List<Attraction> selectAttractions() {

		Connection connection = null;
		List<Attraction> response = new ArrayList<Attraction>();
		
		String query = "SELECT * FROM attractions";
		PreparedStatement sql = null;

		try {

			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			sql = connection.prepareStatement(query);
			
			ResultSet rs = sql.executeQuery();
			
			while(rs.next()) {
				
				Attraction attraction = new Attraction();
				
				attraction.setId(rs.getLong("id"));
				attraction.setName(rs.getString("name"));
				attraction.setLocality(rs.getString("locality"));
								
				attraction.setCategoryId(rs.getLong("id_category"));
				attraction.setCreatorId(rs.getLong("id_creator"));
				
				attraction.setLat(rs.getString("lat"));
				attraction.setLng(rs.getString("lng"));
				attraction.setDescription(rs.getString("description"));
				
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