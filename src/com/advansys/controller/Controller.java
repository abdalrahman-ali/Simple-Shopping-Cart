package com.advansys.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.advansys.dao.ProductDao;
import com.advansys.dao.UserDao;
import com.advansys.db.DBConnection;
import com.advansys.model.Product;
import com.advansys.model.User;

@WebServlet(name = "Controller", urlPatterns = { "/Controller" })
public class Controller extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		
		if(request.getParameter("logout") != null )
		{	
			request.getSession().invalidate();
		    response.sendRedirect("login.jsp");
		}
		else if(request.getParameter("id") != null )
		{	
			System.out.println("here");
			Product p = new ProductDao().getById(Integer.parseInt(request.getParameter("id")));
			System.out.println(p.getName());
			System.out.println(p.getDescription());
			
			request.setAttribute("uid", request.getParameter("uid"));
			request.setAttribute("prod", p);
			RequestDispatcher view = request.getRequestDispatcher("/update.jsp");
			view.forward(request, response);
			
		} else {
			HttpSession session = request.getSession(true);
			User user = (User) session.getAttribute("user");
			if(user == null) 
				user = new com.advansys.model.User();
			RequestDispatcher view = request
					.getRequestDispatcher((user.getId() == 0) ? "/login.jsp" : "/advansys.jsp");
			request.setAttribute("user", user);	
			if(user.getId() != 0) {
				session.setAttribute("user", user);
				if (session.getAttribute("cart") == null){
					ArrayList<Product> cart =  new ArrayList<Product>();
					session.setAttribute("cart", cart);
				}
				
				if(!user.isAdmin())
				{
					ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
					session.setAttribute("cart", cart);
					request.setAttribute("cart",cart);
				}
				
				
				request.setAttribute("prods",
						new com.advansys.dao.ProductDao().getAllProducts());
			}
				
			view.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		
		if(request.getParameter("action") != null)
		{
			new ProductDao().updateProduct(
					new Product(Integer.parseInt(request.getParameter("id")),
							request.getParameter("name"),
							request.getParameter("description"),
							Double.parseDouble(request.getParameter("price") )));
			
			response.getWriter().write("success");
		}
		else if(request.getParameter("deletefrmcart") != null)
		{
			int id = Integer.parseInt(request.getParameter("deletefrmcart"));
			ArrayList<Product> prods = (ArrayList<Product>) request.getSession().getAttribute("cart");
			
			for(int i = 0 ;i < prods.size();i++)
			{
				if(prods.get(i).getId() == id)
				{
					prods.remove(i);
					break;
				}
			}
			
			request.getSession().setAttribute("cart", prods);
			response.getWriter().write("success");
		}
		else if(request.getParameter("addid") != null)
		{
			Product prod = new ProductDao().getById(Integer.parseInt(request.getParameter("addid")));
			ArrayList<Product> prods = (ArrayList<Product>) request.getSession().getAttribute("cart");
			
			prods.add(prod);
			
			request.getSession().setAttribute("cart", prods);
			response.getWriter().write("success");
			
			
		}
		else if(request.getParameter("pric") != null)
		{
			new ProductDao().createProduct(request.getParameter("name"), request.getParameter("desc"), Double.parseDouble(request.getParameter("pric")));
			response.getWriter().write("success");
			
		}
		else if(request.getParameter("delete")!= null)
		{
			int id = Integer.parseInt(request.getParameter("delete"));
			new com.advansys.dao.ProductDao().deleteProduct(id);
			response.getWriter().write("success");
		} else
		{
			HttpSession session = request.getSession(true);

			com.advansys.model.User user;
			if(session.getAttribute("user") == null){
				user = new User(
						request.getParameter("username"),
						request.getParameter("password"));
	
				new com.advansys.dao.UserDao().authenticateUser(user);
				
			}else {			
			  user = (User) request.getSession().getAttribute("user");
			}
			
			RequestDispatcher view = request
					.getRequestDispatcher((user.getId() == 0) ? "/login.jsp"
							: "/advansys.jsp");
			request.setAttribute("user", user);
			if(user.getId() != 0)
			{
				session.setAttribute("user", user);

				if (session.getAttribute("cart") == null) 
				{
					ArrayList<Product> cart =  new ArrayList<Product>();
					session.setAttribute("cart", cart);
				}
				
				if(!user.isAdmin())
				{
					@SuppressWarnings("unchecked")
					ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
					session.setAttribute("cart", cart);
					request.setAttribute("cart",cart);
				}
				request.setAttribute("prods",
						new com.advansys.dao.ProductDao().getAllProducts());
			}
			view.forward(request, response);
		}

	}

}
