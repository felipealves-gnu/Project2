package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packt.GameBean;

@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private GameBean game;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
//			player.setName(request.getParameter("name"));
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet GameServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>Name: " + game.getState() + "</h3>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

	protected void doGet(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
