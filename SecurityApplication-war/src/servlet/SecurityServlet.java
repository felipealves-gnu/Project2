package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packt.VoucherManager;

@WebServlet("/SecurityServlet")
public class SecurityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	VoucherManager voucherManager;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			try {
				voucherManager.createVoucher("Susan Billing", "SanFrancisco", BigDecimal.valueOf(2150.75));
				voucherManager.submit();
				boolean voucherApproved = voucherManager.approve();
				
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Servlet SecurityServlet</title>");
				out.println("</head>");
				out.println("<body>");
				//out.println("<h3>Voucher was submitted</h3>");
				voucherManager.submit();
				if(voucherApproved){
					out.println("<h3>Voucher was approved</h3>");
				}else{
					out.println("<h3>Voucher was not aproved</h3>");
				}
				out.println("<h3>Voucher name: " + voucherManager.getName() + "</h3>");
				out.println("</body>");
				out.println("</html>");				
			}catch(EJBAccessException e){
				System.out.println("Access exception");
			}finally {            
	            out.close();
			}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	
}
