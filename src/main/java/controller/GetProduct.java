package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseconnection.DB_Connection;

public class GetProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();

		try {

			String p = req.getParameter("pcode");

			Connection con = DB_Connection.get_DBConnection();
			PreparedStatement pst1 = con.prepareStatement("Select * from products where pcode=?");
			pst1.setString(1, p);
			ResultSet rs = pst1.executeQuery();

			if (rs.next()) {
				pw.println(rs.getString(2) + "-" + rs.getString(3) + "-" + rs.getString(4) + "-" + rs.getString(5));

			}

		} catch (Exception e) {

		}
	}

}