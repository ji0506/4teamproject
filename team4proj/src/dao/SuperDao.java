package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import model.User;

public interface SuperDao {
	
	public static Connection con = null; 
	
	public static Connection Load()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		//	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/team","root","12345");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}

}
