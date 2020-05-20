package it.javaboss.servlet;

import it.javaboss.jms.JBQReader;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/read")
public class ReadMessageServlet extends HttpServlet {

	@Inject
	JBQReader jbq;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String msg;
		msg = jbq.readMessage();
		
		response.setContentType("text/html"); 
		PrintWriter pw = response.getWriter(); 
		
		if (msg != null && !msg.isEmpty()) {
			pw.write( "Received message: " + msg ); 
		} else {
			pw.write( "Nothing to read ..." ); 
		}
		pw.close();
	}
}
