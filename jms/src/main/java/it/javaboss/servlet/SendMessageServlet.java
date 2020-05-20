package it.javaboss.servlet;

import it.javaboss.jms.JBQWriter;
import it.javaboss.jms.JBTWriter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/send")
public class SendMessageServlet extends HttpServlet {

	@Inject
	JBQWriter jbq;
	
	@Inject
	JBTWriter jbt;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String msg = (String) request.getParameter("msg");
		String type = (String) request.getParameter("type");

		if (msg != null && !msg.isEmpty()) {
			send( msg, type );
			response.setContentType("text/html"); 
			PrintWriter pw = response.getWriter(); 
			pw.write( "Sent message: " + msg ); 
			pw.close(); 
		} else {
			response.sendError( HttpServletResponse.SC_BAD_REQUEST );
		}
	}
	
	private void send( String msg, String type ) {
		if ( type.endsWith("queue") ) {
			jbq.sendMessage(msg);
		} else if ( type.endsWith("topic1") ) {
			jbt.sendMessage(msg, 1);
		} else {
			jbt.sendMessage(msg, 2);
		}
	}
	
}
