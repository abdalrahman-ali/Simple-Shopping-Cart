package com.advansys.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.advansys.model.Product;

public class ProductDao extends Dao
{
	public ArrayList<com.advansys.model.Product> getAllProducts()
	{
		ArrayList<com.advansys.model.Product> products = new ArrayList<com.advansys.model.Product>();
		
		try {
			PreparedStatement preparedStatement = conn.
					prepareStatement("select * from products");
			
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) 
			{
				products.add(new com.advansys.model.Product(
						rs.getInt("id"),
						rs.getString("prodname"),
						rs.getString("proddisc"),
						rs.getDouble("prodprice")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return products;
	}
	
	public Product getById(int id)
	{
		try {
			PreparedStatement preparedStatement = conn.
					prepareStatement("select * from products where id=?");
			
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) 
				return new Product(rs.getInt("id"), rs.getString("prodname"), rs.getString("proddisc"), rs.getDouble("prodprice"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void updateProduct(Product prod)
	{
		try {
			PreparedStatement preparedStatement = conn.
					prepareStatement("update products set prodname=?,proddisc=?,prodprice=? where id=?");
			
			preparedStatement.setString(1, prod.getName());
			preparedStatement.setString(2, prod.getDescription());
			preparedStatement.setDouble(3, prod.getPrice());
			preparedStatement.setInt(4, prod.getId());
			preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public void createProduct(String name,String des, double price)
	{
		try {
			PreparedStatement preparedStatement = conn.
					prepareStatement("INSERT INTO products (prodname,proddisc,prodprice) VALUES (?,?,?)");
			
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, des);
			preparedStatement.setDouble(3, price);
			preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public void deleteProduct(int id)
	{
		try {
			PreparedStatement preparedStatement = conn.
					prepareStatement("delete from products where id=?");
			
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
