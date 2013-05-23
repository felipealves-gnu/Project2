package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packt.SphereBean;

@WebServlet("/SphereServlet")
public class SphereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	SphereBean sphere;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet VolumeServlet</title>");
			out.println("</head>");
			out.println("<body>");
//			sphere.setUnit("kilometers");
			out.printf("<h3>Volume: %6.2f %s </h3>", sphere.computeVolume(3.0), sphere.getUnit());
			out.println("</body>");
			out.println("</html>");
			out.flush();
		} finally {
			out.close();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
