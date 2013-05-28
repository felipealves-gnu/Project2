package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packt.PrintBean;

@WebServlet("/PrintServlet")
public class PrintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	PrintBean printBean;
	
	private void processRequest(HttpServletRequest request,	HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try{
			printBean.printAndForget();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet PrintServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>printAndForget executed</h3>");
			Future<String> futureResult = printBean.printAndCheckLater();
			String result = "";
			
			try {
				result = futureResult.get();
			} catch (InterruptedException ex) {
				Logger.getLogger(PrintServlet.class.getName()).	log(Level.SEVERE, null, ex);
			} catch (ExecutionException ex) {
				Logger.getLogger(PrintServlet.class.getName()).	log(Level.SEVERE, null, ex);
			}
			out.println("<h3>printAndCheckLater executed - Result: " +	result + "</h3>");
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
