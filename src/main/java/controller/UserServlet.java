package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.DbHelper;
import service.AppUrl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet("/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type.equals(null)) type = "get";
		else if(type.equals("showUpdateView")) {
			doShowUpdateView(request, response); 
			return;
		} else if(type.equals("bindUpdateFrom")) {
			doBindUpdateForm(request, response); 
			return;
		} else if(type.equals("put") || type.equals("PUT")) {
			doPut(request, response); 
			return;
		} else if(type.equals("delete") || type.equals("DELETE")) {
			doDelete(request, response);
			return;
		} else if(type.equals("deleteView")) {
			doShowDeleteView(request, response); 
			return;
		}

		try {
			Statement st = new DbHelper().getConnection().createStatement();
			
			ResultSet rs = st.executeQuery(AppUrl.GET_ALL_USERS);
			
			ArrayList<User> users = new ArrayList<User>();
			while(rs.next()) {
				users.add(new User(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("UserList.jsp");
			request.setAttribute("users", users);
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	protected void doShowDeleteView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Statement st = new DbHelper().getConnection().createStatement();
			
			ResultSet rs = st.executeQuery(AppUrl.GET_ALL_USERS);
			
			ArrayList<User> users = new ArrayList<User>();
			while(rs.next()) {
				users.add(new User(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("DeleteUser.jsp");
			request.setAttribute("users", users);
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	protected void doShowUpdateView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Statement st = new DbHelper().getConnection().createStatement();
			
			ResultSet rs = st.executeQuery(AppUrl.GET_ALL_USERS);
			
			ArrayList<User> users = new ArrayList<User>();
			while(rs.next()) {
				users.add(new User(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("UpdateUser.jsp");
			request.setAttribute("users", users);
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type != null && type.equals("put")) {
			doPut(request, response);
			return;
		}
		
		String POST_USER = "INSERT INTO USERS(name, email, location) VALUES (";
		Statement st = null;
		try {
			st = new DbHelper().getConnection().createStatement();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String location = request.getParameter("location");
		System.out.println("name: "+name);
		System.out.println("email: "+email);
		System.out.println("location: "+location);
		
		try {
			st.executeUpdate(POST_USER + "'"+ name + "','" + email + "','" + location + "')");
			doShowDeleteView(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
//		Enumeration<String> params = request.getParameterNames();
//	    out.println(params);
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String location = request.getParameter("location");
//		out.println(request.getParameter("id"));
//		out.println(name);
//		out.println(email);
//		out.println(location);
		
		String UPDATE_USER = "UPDATE users "
				+ "SET name = ?,"
				+ " email= ?,"
				+ " location= ? "
				+ "WHERE id = ? ";
		
		Statement st = null;
		
		try {
			Connection con = new DbHelper().getConnection();
			PreparedStatement pstmt = con.prepareStatement(UPDATE_USER);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, location);
			pstmt.setInt(4, id);
			st = con.createStatement();
			out.print(pstmt.toString());
			pstmt.executeUpdate();
			doShowUpdateView(request, response);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	protected void doBindUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		User user = new User(Integer.parseInt(request.getParameter("id")), request.getParameter("name"), request.getParameter("email"), request.getParameter("location"));
		RequestDispatcher rd = request.getRequestDispatcher("UpdateUserForm.jsp");
		request.setAttribute("user", user);
		rd.forward(request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Statement st = null;
		try {
			st = new DbHelper().getConnection().createStatement();
			st.executeUpdate(AppUrl.DELETE_USER + id);
			doShowDeleteView(request, response);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}