package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import databaseconnection.DB_Connection;

public class ForgotPassword extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try {
			String userid = req.getParameter("txtuserid").trim();

			Connection con = DB_Connection.get_DBConnection();
			PreparedStatement pst1 = con.prepareStatement("select * from customers where emailid=?");
			pst1.setString(1, userid);
			ResultSet rs = pst1.executeQuery();

			if (rs.next()) {

				Random r = new Random();
				int a = r.nextInt(10);
				int b = r.nextInt(10);
				int c = r.nextInt(10);
				int d = r.nextInt(10);
				String otp = a + "" + b + "" + c + "" + d;

				Properties p = new Properties();
				p.put("mail.smtp.starttls.enable", "true");// here smtp donot get start security gets started
				p.put("mail.smtp.auth", "true");
				p.put("mail.smtp.host", "smtp.gmail.com");
				p.put("mail.smtp.port", "587");

				Session s = Session.getInstance(p, new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(DB_Connection.SENDERS_EMAILID,
								DB_Connection.SENDERS_PASSWORD);
					}
				});

				MimeMessage msg = new MimeMessage(s);// multipurpose internet mail extension mime
				msg.setFrom(new InternetAddress(DB_Connection.SENDERS_EMAILID));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(userid));// here type recipient email id
				msg.setSubject("Password reset");
				String m = "Your OTP for password reset is " + otp;
				msg.setContent(m, "text/html; charset=utf-8");
				Transport.send(msg);

				req.setAttribute("otp", otp);
				req.setAttribute("userid", userid);
				req.setAttribute("m", "Enter the OTP sent to your registered emailid");
				RequestDispatcher rd = req.getRequestDispatcher("forgotpassword1.jsp");
				rd.forward(req, res);

			} else {
				req.setAttribute("errormsg",
						"<div class='alert alert-danger' style='height:70px'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>The specified email id/userid is not registered with us.</div>");
				req.setAttribute("autofocus", "autofocus");

				RequestDispatcher rd = req.getRequestDispatcher("forgotpassword.jsp");
				rd.forward(req, res);
			}
		} catch (Exception e) {
			pw.println(e);
		}

	}
}