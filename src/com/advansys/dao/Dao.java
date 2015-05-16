package com.advansys.dao;

import java.sql.Connection;

import com.advansys.db.DBConnection;

public class Dao
{
	protected Connection conn = null;
	
	public Dao()
	{
		conn = DBConnection.getConn();
	}
}
