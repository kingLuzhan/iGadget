package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseconnection.DB_Connection;

public class ChangePassword extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try {
			HttpSession hs = req.getSession(false);
			String oldpassword = req.getParameter("txtoldpassword").trim();
			String newpassword = req.getParameter("txtnewpassword").trim();

			Connection con = DB_Connection.get_DBConnection();

			PreparedStatement pst1 = con.prepareStatement("select * from login where userid=?");
			pst1.setString(1, hs.getAttribute("A1").toString());
			ResultSet rs = pst1.executeQuery();
			rs.next();

			if (rs.getString("password").equals(oldpassword)) {
				pst1.close();
				pst1 = con.prepareStatement("update login set password=? where userid=?");
				pst1.setString(1, newpassword);
				pst1.setString(2, hs.getAttribute("A1").toString());
				pst1.executeUpdate();

				req.setAttribute("m1", "Password changed successfully");
				req.setAttribute("m2", "<div style='color:green'>Success</div>");
				req.setAttribute("msg", "$('#modal-msg').modal('show');");

				RequestDispatcher rd = req.getRequestDispatcher("changepassword.jsp");
				rd.forward(req, res);
			} else {
				req.setAttribute("m1", "Old password does not match");
				req.setAttribute("m2", "<div style='color:red'>Error</div>");
				req.setAttribute("msg", "$('#modal-msg').modal('show');");

				RequestDispatcher rd = req.getRequestDispatcher("changepassword.jsp");
				rd.forward(req, res);

			}

		} catch (Exception e) {
			pw.println(e);
		}

	}
}