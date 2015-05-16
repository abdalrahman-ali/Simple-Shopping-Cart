package com.advansys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.advansys.model.User;

public class UserDao extends Dao
{
	

	
	public void authenticateUser(com.advansys.model.User user)
	{
		try {
			PreparedStatement preparedStatement = conn.
					prepareStatement("select * from users where username=? AND password=?");
			
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAdmin(rs.getBoolean("admin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public User getById(int id)
	{
		
		
		try {
			PreparedStatement preparedStatement = conn.
					prepareStatement("select * from users where id=?");
			
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				return new User(id, rs.getString("username"), rs.getString("password"), rs.getBoolean("admin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
