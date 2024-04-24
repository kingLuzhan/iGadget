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

public class Feedback extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try {
			String name = req.getParameter("txtname").trim();
			String email = req.getParameter("txtemail").trim();
			String mobile = req.getParameter("txtphone").trim();
			String feedback = req.getParameter("txtfeedback").trim();

			Connection con = DB_Connection.get_DBConnection();

			PreparedStatement pst = con.prepareStatement("insert into feedback values(?,?,?,?)");
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, mobile);
			pst.setString(4, feedback);

			pst.executeUpdate();
			pst.close();

			req.setAttribute("msg",
					"<div class='alert alert-success' style='text-align: center;'>Thank you for your valuable feed back. We value your association with us..</div>");
			RequestDispatcher rd = req.getRequestDispatcher("feedback.jsp");
			rd.forward(req, res);

		} catch (Exception e) {
			pw.println(e);
		}

	}
}