package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import databaseconnection.DB_Connection;

@MultipartConfig
public class ModifyProduct extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		InputStream is[];
		is = new InputStream[] { null };
		String values[] = new String[6];
		int j = 0;
		try {
			Collection<Part> p = req.getParts();
			Iterator i = p.iterator();
			while (i.hasNext()) {
				Part p1 = (Part) i.next();

				if (p1.getName().equalsIgnoreCase("txtpic")) {
					is[0] = p1.getInputStream();

				} else {
					InputStream i1 = p1.getInputStream();
					int ch;
					StringBuilder sb = new StringBuilder();
					while ((ch = i1.read()) != -1) {
						sb.append((char) ch);
					}
					values[j] = sb.toString();
					j++;
				}
			}

			Connection con = DB_Connection.get_DBConnection();
			if (values[5].equals("false")) {

				PreparedStatement pst1 = con.prepareStatement(
						"update products set pname=?,description=?,type=?,price=?,pic1=? where pcode=?");
				pst1.setString(1, values[1]);
				pst1.setString(2, values[2]);
				pst1.setString(3, values[3]);
				pst1.setInt(4, Integer.parseInt(values[4]));
				pst1.setBlob(5, is[0]);
				pst1.setString(6, values[0]);
				pst1.executeUpdate();

			} else {
				PreparedStatement pst1 = con
						.prepareStatement("update products set pname=?,description=?,type=?,price=? where pcode=?");
				pst1.setString(1, values[1]);
				pst1.setString(2, values[2]);
				pst1.setString(3, values[3]);
				pst1.setInt(4, Integer.parseInt(values[4]));
				pst1.setString(5, values[0]);
				pst1.executeUpdate();

				pst1.executeUpdate();
			}
			req.setAttribute("msg", "$('#modal-msg').modal('show');");
			RequestDispatcher rd = req.getRequestDispatcher("modifyproduct.jsp");
			rd.forward(req, res);

		} catch (Exception e) {
			pw.println(e);
		}
	}

}