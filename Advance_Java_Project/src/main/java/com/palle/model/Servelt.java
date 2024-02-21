package com.palle.model;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class Servelt
 */
@WebServlet("/hello")
public class Servelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name =request.getParameter("user");
		String email=request.getParameter("email");
		String password=request.getParameter("pass");
		String  gender=request.getParameter("rad");
		String city=request.getParameter("city1");
		
		
		PrintWriter out=response.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee","root","admin");
			
			String query="insert into student values(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,name );
			ps.setString(2,email );
			ps.setString(3,password );
			ps.setString(4, gender);
			ps.setString(5, city);
			int count=ps.executeUpdate();
			
			if(count>0) {
				response.setContentType("text/html");
				out.print("<h3 style='color:green'> user registered successfully </h3>");
				
				RequestDispatcher rd=request.getRequestDispatcher("/register.jsp");
				rd.include(request,response);
			}
			else {
				response.setContentType("text/html");
				out.print("<h3 style='color:green'> user registered successfully </h3>");
				
				RequestDispatcher rd=request.getRequestDispatcher("/register.jsp");
				rd.include(request,response);
			}
			
	
			
			
			
			
			
			
		}catch(ClassNotFoundException |SQLException f) 
		{
			f.printStackTrace();
			response.setContentType("text/html");
			out.print("<h3 style='color:red'> Exception Occurred:"+f.getMessage() +"</h3>");
			
			RequestDispatcher rd=request.getRequestDispatcher("/register.jsp");
			rd.include(request,response);
			
			
		}
			
		
	}

}
