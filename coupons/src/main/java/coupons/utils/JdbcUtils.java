package coupons.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtils {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection(String username,String password, String host,String port) throws SQLException {
		 String dbURL = String.format("jdbc:mysql://$S:$S/CouponsDataBase?useSSL=true",host,port);
		Connection connection = DriverManager.getConnection(dbURL, username, password);
		// CouponsDataBase is the DB name, AKA Schema name.
		return connection;
	}

	public static void closeResources(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Write to log that we have a resource leak
		}
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// Write to log that we have a resource leak
		}

		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			// Write to log that we have a resource leak
			e.printStackTrace();
		}
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		closeResources(connection, preparedStatement);
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			// Write to log that we have a resource leak
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) throws SQLException  {
//		//test db connection
//		Connection connection = JdbcUtils.getConnection();
//		if (connection != null) {
//			System.out.println("Connected");
//		}	
//		connection.close();
//	}
}



