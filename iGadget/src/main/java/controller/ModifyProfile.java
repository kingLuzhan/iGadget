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
import javax.servlet.http.HttpSession;

import databaseconnection.DB_Connection;

public class ModifyProfile extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try {
			HttpSession hs = req.getSession(false);
			String fname = req.getParameter("txtfname").trim();
			String lname = req.getParameter("txtlname").trim();
			String mobile = req.getParameter("txtmobile").trim();

			Connection con = DB_Connection.get_DBConnection();

			PreparedStatement pst = con
					.prepareStatement("update customers set fname=?, lname=? ,mobile=? where emailid=?");
			pst.setString(1, fname);
			pst.setString(2, lname);
			pst.setString(3, mobile);

			pst.setString(4, (String) hs.getAttribute("A1"));
			pst.executeUpdate();
			pst.close();

			req.setAttribute("msg", "$('#modal-msg').modal('show');");

			RequestDispatcher rd = req.getRequestDispatcher("modifyprofile.jsp");
			rd.forward(req, res);

		} catch (Exception e) {
			pw.println(e);
		}
	}
}