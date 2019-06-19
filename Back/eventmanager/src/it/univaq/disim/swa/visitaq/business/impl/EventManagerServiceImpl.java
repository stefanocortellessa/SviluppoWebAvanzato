package it.univaq.disim.swa.visitaq.business.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import it.univaq.disim.swa.visitaq.business.EventManagerBusinessException;
import it.univaq.disim.swa.visitaq.business.EventManagerService;
import it.univaq.disim.swa.visitaq.business.Utility;
import it.univaq.disim.swa.visitaq.domain.Event;


public class EventManagerServiceImpl implements EventManagerService {

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
	public Event insertEvent(Event event) throws EventManagerBusinessException {

		Connection connection = null;
		Event response = new Event();

		String query = "INSERT INTO events (title, locality, startDate, endDate, id_category, id_creator, lat, lng, description) VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		
		try {

			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			ps = connection.prepareStatement(query);
			
			ps.setString(1, event.getTitle());
			ps.setString(2, event.getLocality());
			ps.setDate(3, (Date) event.getStartDate());
			ps.setDate(4, (Date) event.getEndDate());
			ps.setLong(5, event.getCategory().getId());
			ps.setLong(6, event.getCreator().getId());
			ps.setString(7, event.getLat());
			ps.setString(8, event.getLng());
			ps.setString(9, event.getDescription());

			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EventManagerBusinessException("Something went wrong with Insert Event..");
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
		response.setTitle(event.getTitle());

		return response;
	}

	@Override
	public void deleteEvent(Long id, Event event) throws EventManagerBusinessException {
		
		Connection connection = null;
		Utility utility = new Utility();
		
		String query = "DELETE FROM events WHERE id = ?";
		PreparedStatement ps = null;
		
		try {

			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			if (utility.checkCreator(connection, id, event.getCreator().getId())) {
			
				ps = connection.prepareStatement(query);

				ps.setLong(1, id);

				ps.executeUpdate();
			} else {
				System.out.println("Event NOT Deleted: user is NOT its creator");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EventManagerBusinessException("Something went wrong with Deleting an Event..");
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
	public Event updateEvent(Event event, Long id) throws EventManagerBusinessException {
		
		Connection connection = null;
		Event response = new Event();
		Utility utility = new Utility();
		
		String query = "UPDATE events SET title=?, locality=?, startDate=?, endDate=?, id_category=?, id_creator=?, lat=?, lng=?, description=? WHERE id=?";
		PreparedStatement ps = null;

		try {

			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			if (utility.checkCreator(connection, id, event.getCreator().getId())) {
				
				ps = connection.prepareStatement(query);

				ps.setString(1, event.getTitle());
				ps.setString(2, event.getLocality());
				ps.setDate(3, event.getStartDate());
				ps.setDate(4, event.getEndDate());
				ps.setLong(5, event.getCategory().getId());
				ps.setLong(6, event.getCreator().getId());
				ps.setString(7, event.getLat());
				ps.setString(8, event.getLng());
				ps.setString(9, event.getDescription());
				ps.setLong(10, id);
				

				ps.executeUpdate();

			} 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EventManagerBusinessException("Something went wrong with Update Event..");
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
		response.setTitle(event.getTitle());

		return response;
	}
	
	public Event selectEventDetail(Long id) {

		Connection connection = null;
		
		Event event = new Event();
		String query = "SELECT * FROM events WHERE id=?";
		PreparedStatement sql = null;

		try {
			
			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			sql = connection.prepareStatement(query);

			sql.setLong(1, id);

			ResultSet rs = sql.executeQuery();
			
			while(rs.next()) {
			
				event.setId(rs.getLong("id"));
				event.setTitle(rs.getString("title"));
				event.setLocality(rs.getString("locality"));
				event.setStartDate(rs.getDate("startDate"));
				event.setEndDate(rs.getDate("endDate"));
				event.setCategoryId(rs.getLong("id_category"));
				event.setCreatorId(rs.getLong("id_creator"));
				
				event.setLat(rs.getString("lat"));
				event.setLng(rs.getString("lng"));
				event.setDescription(rs.getString("description"));
				
			}
			return event;
		} catch (SQLException e) {
			return event;
		}finally {
			if (sql != null) {
				try {
					sql.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	public List<Event> selectEvents() {

		Connection connection = null;
		List<Event> response = new ArrayList<Event>();
		
		String query = "SELECT * FROM events";
		PreparedStatement sql = null;

		try {

			connection = this.getConnection(connection);
			connection.setAutoCommit(false);
			
			sql = connection.prepareStatement(query);
			
			ResultSet rs = sql.executeQuery();
			
			while(rs.next()) {
				
				Event event = new Event();
				
				event.setId(rs.getLong("id"));
				event.setTitle(rs.getString("title"));
				event.setLocality(rs.getString("locality"));
				event.setStartDate(rs.getDate("startDate"));
				event.setEndDate(rs.getDate("endDate"));
				event.setCategoryId(rs.getLong("id_category"));
				event.setCreatorId(rs.getLong("id_creator"));
				
				event.setLat(rs.getString("lat"));
				event.setLng(rs.getString("lng"));
				event.setDescription(rs.getString("description"));
				response.add(event);	
				
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