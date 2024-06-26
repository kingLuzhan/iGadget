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
public class AddNewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			String type = "";
			if (values[3].equalsIgnoreCase("others")) {
				type = values[4];
			} else {
				type = values[3];
			}

			Connection con = DB_Connection.get_DBConnection();
			PreparedStatement pst1 = con.prepareStatement("insert into products values(?,?,?,?,?,?)");
			pst1.setString(1, values[0]);
			pst1.setString(2, values[1]);
			pst1.setString(3, values[2]);
			pst1.setString(4, type);
			pst1.setInt(5, Integer.parseInt(values[5]));
			pst1.setBlob(6, is[0]);

			pst1.executeUpdate();
			req.setAttribute("msg", "$('#modal-msg').modal('show');");
			RequestDispatcher rd = req.getRequestDispatcher("addnewproduct.jsp");
			rd.forward(req, res);

		} catch (Exception e) {
			pw.println(e);
		}
	}

}