package service;

import java.sql.*;

public class DbHelper {
	private Connection myCon = null;
	public DbHelper() throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/demo";
		String username = "root";
		String password = "admin";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, username, password);
		this.myCon = con;
	}
	
	public Connection getConnection() {
		return this.myCon;
	}
}
