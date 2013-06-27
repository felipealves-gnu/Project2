package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packt.ApplicationStatistics;
import packt.Attendee;
import packt.RegistrationManager;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	RegistrationManager registrationManager;
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)	throws ServletException, IOException {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			ApplicationStatistics applicationStatus = ApplicationStatistics.getInstance();
			
			try {
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Servlet RegistrationServlet</title>");
				out.println("</head>");
				out.println("<body>");
				
				Attendee attendee = registrationManager.register("Bill Schroder ", "Manager", "Acme Software");				
				out.println("<h3>" + attendee.getName() + " has been registered</h3>");
				out.println("<h3>" + "Number of attendees: " + applicationStatus.getCount() + "</h3>");
				out.println("<h3>Total Time: " + applicationStatus.getTotalTime() +	"</h3>");
//				String names[] = {"John", "Paul", "Karen"};
//				String titles[] = {"Lead", "Programmer", "Adminsitrator"};
//				String company = "Acme Software";
//				registrationManager.bulkRegister(names, titles, company);
				
				
				out.println("</body>");
				out.println("</html>");
			} finally {
				out.close();
			}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
