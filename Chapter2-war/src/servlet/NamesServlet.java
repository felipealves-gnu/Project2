package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packt.NamesBean;

@WebServlet("/NamesServlet")
public class NamesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private NamesBean names;
	private List<String> list;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
	
		try {
			names.addName(request.getParameter("name"));
			list = names.getNames();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>NamesServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>Current List of Names</h3>");
			
			for(String name: list) {
				out.println(name + "<br>");
			}
			out.println("</body>");
			out.println("</html>");
		} finally {
			System.out.println("Error");
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
