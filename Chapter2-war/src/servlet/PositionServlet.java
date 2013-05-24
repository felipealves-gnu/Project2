package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packt.OrbitalElementsRemote;
import packt.PositionBeanRemote;

@WebServlet("/PositionServlet")
public class PositionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	PositionBeanRemote position; 
	@EJB
	OrbitalElementsRemote orbitalElements;
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
			
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet PositionServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>Eccentricity: " + position.getEccentricity() + "</h3>");
			out.println("<h3>Eccentricity: " + orbitalElements.getPosition().getEccentricity() + "</h3>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
