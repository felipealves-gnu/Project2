package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packt.SimulationBeanManaged;

@WebServlet("/Concurrencyservlet")
public class ConcurrencyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	SimulationBeanManaged simulationBeanManaged;
	
	private void processRequest(HttpServletRequest request,	HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			simulationBeanManaged.setState(SimulationBeanManaged.State.PAUSED);
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet ConcurrencyServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

	protected void doGet(HttpServletRequest request,	HttpServletResponse response) 
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
