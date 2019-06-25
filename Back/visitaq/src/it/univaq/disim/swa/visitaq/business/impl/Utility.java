package it.univaq.disim.swa.visitaq.business.impl;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import com.google.common.hash.Hashing;

public class Utility {

	protected static SecureRandom random = new SecureRandom();

	// Method to generate a User Token
	public static String generateToken() {

		long longToken = Math.abs(random.nextLong());
		String stringToken = Long.toString(longToken, 400);
		String token = stringToken.substring(0, 15);
		
		return token;
	}

	// Method to encode the password
	public String hashPwd(String originalString) {

		String hashed = Hashing.sha256().hashString(originalString, StandardCharsets.UTF_8).toString();

		return hashed;
	}
	
	// Method to check the attraction creator
	public boolean checkCreatorAttractions(Connection connection, Long attractionId, Long creatorId) {

		String query = "SELECT id_creator FROM attractions WHERE id = ?";
		PreparedStatement sql = null;

		try {
			sql = connection.prepareStatement(query);

			sql.setLong(1, attractionId);
			
			ResultSet rs = sql.executeQuery();

			while (rs.next()) {
				if (rs.getLong("id_creator") == creatorId) {

					//System.out.println("attraction creator identified");
					return true;
				} else {
					return false;
				}
			}
			return false;
		} catch (SQLException e) {
			return false;
		} finally {
			if (sql != null) {
				try {
					sql.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	// Method to check the attraction creator
	public boolean checkCreatorEvents(Connection connection, Long attractionId, Long creatorId) {

		String query = "SELECT id_creator FROM events WHERE id = ?";
		PreparedStatement sql = null;

		try {
			sql = connection.prepareStatement(query);

			sql.setLong(1, attractionId);
			
			ResultSet rs = sql.executeQuery();

			while (rs.next()) {
				if (rs.getLong("id_creator") == creatorId) {

					//System.out.println("attraction creator identified");
					return true;
				} else {
					return false;
				}
			}
			return false;
		} catch (SQLException e) {
			return false;
		} finally {
			if (sql != null) {
				try {
					sql.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	//Method that converts date from XMLGregorianCalendar to Date
	public Date convertDate(XMLGregorianCalendar calendar){

		if(calendar == null) {
			return null;
		}
		Date date = (Date) calendar.toGregorianCalendar().getTime();
		date.setTime(date.getTime() - 3600 * 1000);

		return date;
	}
}
