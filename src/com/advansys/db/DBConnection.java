package com.advansys.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection
{
	
	private static DBConnection self = null;
	public Connection con = null;
	
	private DBConnection()
	{
		try
		{
			String dbUrl = "jdbc:mysql://localhost:3306/mydb";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(dbUrl, "root", "");
		} 
		catch (Exception e)
		{
			System.out.println("Database connection exception=" + e);
		}
	}
	
	public static Connection getConn()
	{
		if(self == null)
			self = new DBConnection();
		
		return self.con;
	}

}
