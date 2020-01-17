package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManager {

	public void close() {
		DataBaseConnection.closeConnection();
	}

	
	public static String getConfig(String sql) {
		String retorno = "";
		Connection con = DataBaseConnection.getConnection();
		PreparedStatement statement = null;
		try {
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			rs.next();
			retorno = rs.getString("value");

			rs.close();
			statement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retorno;

	}

}
