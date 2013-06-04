package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.jms.BytesMessage;
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

@WebServlet("/PartsServlet")
public class PartsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(mappedName="jms/PartsFactory") //injecting a QueueConnectionFactory
	private QueueConnectionFactory queueConnectionFactory;
	
	@Resource(mappedName="jms/PartsQueue") //injecting a Queue
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
				
				BytesMessage bytesMessage = session.createBytesMessage();
				bytesMessage.writeInt(12345);
				bytesMessage.writeFloat(12.5f);
				bytesMessage.writeInt(50);
				messageProducer.send(bytesMessage);
				System.out.println("---> comment sent");
			} catch (JMSException ex) {
				Logger.getLogger(TextServlet.class.getName()).log(Level.SEVERE, null, ex);			
			}		
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet TextServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet PartsServlet at " + request.getContextPath () + "</h1>");
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
