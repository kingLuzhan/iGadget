package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseconnection.DB_Connection;

public class GetProductImage extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// PrintWriter pw=res.getWriter();
		res.setContentType("image/jpeg");
		// HttpSession hs=req.getSession(false);
		byte b[] = null;
		try {

			String p = req.getParameter("pcode");

			Connection con = DB_Connection.get_DBConnection();
			PreparedStatement pst1 = con.prepareStatement("Select * from products where pcode=?");
			pst1.setString(1, p);
			ResultSet rs = pst1.executeQuery();
			rs.next();

			b = rs.getBytes("pic1");
			ServletOutputStream o = res.getOutputStream();
			o.write(b);
			o.flush();
			o.close();

		} catch (Exception e) {

		}
	}

}