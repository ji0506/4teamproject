package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class SuperDao {
	
	public static Connection con ; 
	
	public static void Load()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://192.168.17.76:3306/team","root","12345");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static void close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static Connection getConnection()
	{
		return con;
	}
	
	
	
	
	
	
	

}
