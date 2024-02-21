package com.palle.model;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/loginform")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		PrintWriter out=response.getWriter()	;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee","root","admin");
			
			String query="select * from student where email=? and password=?";
			PreparedStatement ps=con.prepareStatement(query);
	
			ps.setString(1,email);
			ps.setString(2,password );
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("session_name", rs.getString("name"));
				RequestDispatcher rd=request.getRequestDispatcher("/profile.jsp");
				rd.include(request, response);
			}
			else {
				response.setContentType("text/html");
				out.print("<h3 style='color:red'>Email and password didnt match </h3>");
				
				RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
				rd.include(request,response);
			}
		}
			
			catch(ClassNotFoundException|SQLException e) {
				
				response.setContentType("text/html");
				out.print("<h3 style='color:red'>"+e.getMessage()+"</h3>");
				
				RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
				rd.include(request,response);
		}
		
	
	
	
	
	}
}


