package com.luv2code.web.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		Connection conn =	DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_tracker?useSSL=false", "employee", "employee");
		//Variable of type Connection to hold the connection
			
		Statement stmt = conn.createStatement();
		//Connection.createStatement method returns an object of type Statement which we assign to stmt 
				
		ResultSet myRs = stmt.executeQuery("Select * from employee");
		
		while(myRs.next()) {
			String email = myRs.getString("email");
			System.out.println(email);
		}
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
		
		
	}

}
