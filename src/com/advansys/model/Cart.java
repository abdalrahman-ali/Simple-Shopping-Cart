package com.advansys.model;

import java.util.ArrayList;

public class Cart {
	
	private ArrayList<Product> cartItems;
	
	public Cart()
	{
		cartItems = new ArrayList<Product>();
	}

	public ArrayList<Product> getCartItems() {
		return cartItems;
	}
	

}
