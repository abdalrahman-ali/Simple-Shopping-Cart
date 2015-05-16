package com.advansys.model;

public class User
{
	private int id = 0;
	private String userName;
	private String password;
	private boolean admin;
	
	public User()
	{
		
	}
	public User(int id, String u,String p,boolean d)
	{
		this.id=id;
		this.userName=u;
		this.password=p;
		this.admin=d;
	}
	
	public User(String usnam, String pass)
	{
		userName = usnam;
		password = pass;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public boolean isAdmin()
	{
		return admin;
	}
	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	
	
}
