package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForgotPassword1 extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		try {
			String userid = req.getParameter("userid");

			String otpvalue = req.getParameter("txtotp1").trim();
			String otp = req.getParameter("otp");
			if (otp.equals(otpvalue)) {
				req.setAttribute("userid", userid);
				RequestDispatcher rd = req.getRequestDispatcher("forgotpassword2.jsp");
				rd.forward(req, res);

			} else {
				req.setAttribute("errormsg",
						"<div class='alert alert-danger' style='height:70px'><a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>OTP value does not match.Please try again.</div>");
				req.setAttribute("otp", otp);
				req.setAttribute("userid", userid);
				req.setAttribute("autofocus", "autofocus");
				RequestDispatcher rd = req.getRequestDispatcher("forgotpassword1.jsp");
				rd.forward(req, res);
			}

		} catch (Exception e) {
			pw.println(e);
		}

	}
}