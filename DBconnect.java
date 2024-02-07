
package com.DB;

import java.sql.Connection;

// this is my class for providing me jdbc connection..
import java.sql.DriverManager;

public class DBconnect {
	
	private static Connection conn;
	public static Connection getConn()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ebook-app","root","Daryapur@123");
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return conn;
	}

}
