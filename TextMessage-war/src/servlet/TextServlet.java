package servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TextServlet")
public class TextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(mappedName="jms/TextFactoryPool") //injecting a QueueConnectionFactory
	private QueueConnectionFactory queueConnectionFactory;
	
	@Resource(mappedName="jms/TextQueue") //injecting a Queue
	private Queue queue;
	
	private void processRequest(HttpServletRequest request,	HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			Connection connection;
			try {
				connection = queueConnectionFactory.createConnection();
				Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				MessageProducer messageProducer = (MessageProducer)	session.createProducer(queue);
				// Create message
				// Initialize message
				TextMessage textMessage = session.createTextMessage();
				// Part number – 12345
				// Weight – 12.5f
				// Quantity - 50
				String message = "12345 12.5 50";
				textMessage.setText(message);
				messageProducer.send(textMessage);
				System.out.println("---> Text Message Sent");
			} catch (JMSException ex) {
				Logger.getLogger(TextServlet.class.getName()).log(Level.SEVERE, null, ex);			
			}		
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet TextServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet TextServlet at " +	request.getContextPath () + "</h1>");
			out.println("</body>");
			out.println("</html>");
		}finally {		// HTML output
			out.close();
		}
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
