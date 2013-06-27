package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packt.SystemReportManager;

@WebServlet("/ReportsServlet")
public class ReportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SystemReportManager systemReportManager;

	private void processRequest(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet ReportsServlet</title>");
			out.println("</head>");
			out.println("<body>");
//			out.println("<h3>" + systemReportManager.getMemoryReport() + "</h3>");
			systemReportManager.createTimer(); 
//			systemReportManager.createCalendarTimer();
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
