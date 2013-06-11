package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;

import packt.BeanManagedPopulationManager;
import packt.City;
import packt.CityFacade;
import packt.PopulationManager;

@WebServlet("/PopulationServlet")
public class PopulationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	CityFacade cityFacade;
	@EJB
	PopulationManager populationManager;
	@EJB
	BeanManagedPopulationManager bean;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(response);
	}

	public void processRequest(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			clearTables();
			populationManager.addCity("Tokyo", "Japan", 32450000);
//			populationManager.updatePopulation("Tokyo", -8888);			
			bean.changePopulation("Tokyo", 1000);
			
			List<City> cities = cityFacade.findAll();
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet PopulationServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("</body>");
			
 			for (City c : cities) {
 				out.println("<h5>" + c.getName() + " --- " + c.getPopulation() + "</h5>");
			}
			out.println("</body>");
			out.println("</html>");
		}catch(SystemException ex){
			System.out.println("SystemException");
			Logger.getLogger(PopulationServlet.class.getName()).log(Level.SEVERE, null, ex);
		}

		finally {
			out.close();
		}
	}
	
	private void clearTables(){
		List<City> cities = cityFacade.findAll();
		for (City city : cities) {
			cityFacade.remove(city);
		}
	}

}
