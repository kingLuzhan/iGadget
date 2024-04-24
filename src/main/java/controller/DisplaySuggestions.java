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
import javax.servlet.http.HttpSession;

import databaseconnection.DB_Connection;

public class DisplaySuggestions extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		HttpSession hs = req.getSession(false);
		PreparedStatement pst2;
		try {

			Connection con = DB_Connection.get_DBConnection();

			if (!hs.getAttribute("A1").toString().equalsIgnoreCase("admin@idesign.com")) {
				pst2 = con.prepareStatement("select * from feedback where emailid=?");
				pst2.setString(1, hs.getAttribute("A1").toString());
			} else {
				pst2 = con.prepareStatement("select * from feedback");

			}

			ResultSet rs1 = pst2.executeQuery();

			String t = "";
			if (rs1.next()) {
				do {
					t = t + "<tr>";
					t = t + "<td>" + rs1.getString(1);
					t = t + "<td>" + rs1.getString(2);
					t = t + "<td>" + rs1.getString(3);
					t = t + "<td>" + rs1.getString(4);

					t = t + "</tr>";
				} while (rs1.next());
				pw.println(t);
			} else {

				pw.println(
						"<p style='font-weight:bolder;color:red;font-size:20px'><i style='font-weight:bolder;color:red;font-size:25px' class='fa fa-frown-o'></i> Oopsss....No Feedback to Display </style> ");

			}

		}

		catch (Exception e) {
			pw.println(e);
		}

	}
}