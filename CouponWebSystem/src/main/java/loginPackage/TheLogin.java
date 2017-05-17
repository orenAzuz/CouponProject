package loginPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import facade.AdminFacade;
import facade.ClientType;
import facade.CompanyFacade;
import facade.CouponSystem;
import facade.CustomerFacade;

/**
 * Servlet implementation class TheLogin
 */
public class TheLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheLogin() {
        super();
    }
       
  	
  	/**
  	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  	 */
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		
  		PrintWriter pw = response.getWriter();
  		try {
  		
  		AdminFacade admin = null;

  		String user = request.getParameter("a");
  		String name = request.getParameter("username");
  		String password = request.getParameter("password");
  		
  		switch (user) {
  		case "admin":
  				
  				admin = (AdminFacade) CouponSystem.getInstance().login(name, password, ClientType.valueOf(user));


  				
  				if(admin==null)
  				{
  					pw.print("<h1>Login failed!!!</h1></br><b>please go back</b>");
  					
  					HttpSession sess = request.getSession();
  				  if (sess != null) {
  				    sess.invalidate();}
  				}
  				else{

  				
                  
      				HttpSession sess = request.getSession();
      				      				sess.setAttribute("admin",admin);
  					response.sendRedirect("http://localhost:8080/CouponWebSystem/AdminSPA.html");
  					response.getWriter().close();
                      System.out.println(admin);


  				}
  				
		break;

	case"company":
		CompanyFacade company = (CompanyFacade) CouponSystem.getInstance().login(name, password, ClientType.valueOf(user));
		if(company!=null){
		HttpSession sess = request.getSession();
			sess.setAttribute("company",company);
			sess.setAttribute("name",name );
			
			response.sendRedirect("http://localhost:8080/CouponWebSystem/CompanySPA.html");
            }
		else{
			pw.println("<h1>The Login failed</h1><br>go back</br>");
		}
		
		break;

	case"customer":
		CustomerFacade customer = (CustomerFacade) CouponSystem.getInstance().login(name, password, ClientType.valueOf(user));
		if(customer!=null){
		HttpSession session = request.getSession();
		session.setAttribute("customer",customer);
		response.sendRedirect("http://localhost:8080/CouponWebSystem/CustomerSPA.html");
        }
		else{
			pw.println("<h1>The Login failed</h1><br>go back</br>");
		}
		
		break;

	default:
		break;
	}
	
	//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		
	} catch (ClassNotFoundException | SQLException | InterruptedException e) {
		
		e.printStackTrace();}		
			
	
	
}
		
		
		
		
		
		
		
}
