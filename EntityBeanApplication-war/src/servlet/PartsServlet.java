package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packt.PartsBean;
import packt.PartsBeanFacade;

@WebServlet("/PartsServlet")
public class PartsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
    PartsBeanFacade partsFacade;
    
    PartsBean parts;
    PartsBean otherParts;
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            parts = new PartsBean();
            parts.setName("Traverse Gear");
            parts.setPartNumber(12345);
            parts.setWeight(2.54f);
            parts.setQuantity(2);
            partsFacade.create(parts);
            
            parts = new PartsBean();
            parts.setName("Differential Axle");
            parts.setPartNumber(90334);
            parts.setWeight(12.35f);
            parts.setQuantity(1);
            partsFacade.create(parts);
            otherParts = partsFacade.find(parts.getId());
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PartsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PartsServlet at " + request.getContextPath() + 
            		otherParts.getName() + " id: " + otherParts.getId()+ "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
	}

}
