package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseconnection.DB_Connection;

public class DeleteProduct extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		String pid = req.getParameter("pid");
		try {

			Connection con = DB_Connection.get_DBConnection();

			PreparedStatement pst = con.prepareStatement("delete from products where pcode=?");
			pst.setString(1, pid);
			pst.executeUpdate();
			pst.close();

			RequestDispatcher rd = req.getRequestDispatcher("ManageProducts");
			rd.forward(req, res);

		} catch (Exception e) {
			pw.println(e);
		}
	}

}