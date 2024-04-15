/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseManager {

	private static final String dbName = "hotel_ebd";
	private static final String URL = "jdbc:derby:" + dbName + "; create=true";
	private static final String USERNAME = "hotel";
	private static final String PASSWORD = "hotel";
	Connection connection; //  no access modifier is best practice.

	public DatabaseManager() {
		establishConnection();
	}

	/***
	 * Will establish a connection to embedded database. If the database does not
	 * exist then it will create it.
	 */
	public void establishConnection() {
		if (this.connection == null) {
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				System.out.println("Connected: " + dbName);
			}
			catch (SQLException ex) {
				System.out.println("Unable to connect: " + dbName);
				System.out.println(ex.getMessage());
			}
		}
	}

	/**
	 * @return the connection of this database.
	 */
	public Connection getConnection() {
		if (this.connection != null) 
            return this.connection;
		else 
            return null;
	}

	/**
	 * Closes connection to database.
	 */
	public void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
				System.out.println("Disconnected: " + dbName);
			}
			catch (SQLException ex) { 
                System.out.println(ex.getMessage());
            }
		}
	}

	/**
	 * Execute batch/single update command on database.
	 * 
	 * @param sql statements to execute as array.
	 */
	public void update(String sql) throws SQLException {
		Statement statement = connection.createStatement();
		statement.execute(sql);
	}

	public void updateBatch(String... sql) throws SQLException {
		Statement statement = connection.createStatement();
        
		for (String s : sql) 
            statement.addBatch(s);
        
		statement.executeBatch();
	}

	/**
	 * Queries the database returning a ResultSet
	 * 
	 * @param sql statement to query database with.
	 * @return ResultSet of query.
	 */
	public ResultSet query(String sql) {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			return resultSet;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
}