package it.univaq.disim.swa.visitaq.business;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utility {

	protected static SecureRandom random = new SecureRandom();

	// Method that queries the database checking if the user
	// that want to do something on an event is its creator
	public boolean checkCreator(Connection con, Long id, Long creatorId) {

		String query = "SELECT id_creator FROM events WHERE id = ?";
		PreparedStatement sql = null;

		try {
			sql = con.prepareStatement(query);

			sql.setLong(1, id);

			ResultSet rs = sql.executeQuery();

			while (rs.next()) {
				if (rs.getLong("id_creator") == creatorId) {

					System.out.println("Event Creator Identified");

					return true;
				} else {
					System.out.println("Event Creator NOT Identified");
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
}