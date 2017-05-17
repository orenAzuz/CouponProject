package loginPackage;

import java.awt.PageAttributes.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.Company;
import facade.AdminFacade;
import facade.ClientType;
import facade.CouponSystem;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String result = "<h1> THE A D M I N IS </h1>";
			
      String resultCompany = "<!DOCTYPE html><html><head><meta charset=\"windows-1255\">	<title>Login System</title>"+
    			"</head><body><h1>CompanyService</h1></body></html>";
      
		String resultCustomer = "<!DOCTYPE html><html><head><meta charset=\"windows-1255\">	<title>Login System</title>"+
				"</head><body><h1>Customer</h1></body></html>";
		 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
					pw.print("<h1>failed!!!</h1></br><b>please go back</b>");
				}
				else{

					ServletContext sc = getServletContext();
                    System.out.println();
    				HttpSession sess = request.getSession(true);
    				//sess.setAttribute("test","test");
    				sc.setAttribute("admin",admin);
					//response.sendRedirect("http://localhost:8080/LoginSystem/NewFile.html");
                    System.out.println(admin);


				}
				
			//	URL url = new URL("http://localhost:8080/aaaaa/webapi/admin/"+admin.get(password));
			//	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//	conn.setRequestMethod("GET");
			//	conn.setRequestProperty("Accept","application/json");
//
			//	BufferedReader br = new BufferedReader(new InputStreamReader(
			//		(conn.getInputStream())));
			//	String output = "";
			//	while(br!=null){
			// output = br.readLine();
				
			//	output = br.readLine();
				
			//	pw.println(result+" "+output);
				
				
			
			
			break;

		case"company":
			CouponSystem.getInstance().login(name, password, ClientType.valueOf(user));
			
				
				pw.println(resultCompany);
			
			break;

		case"customer":
			CouponSystem.getInstance().login(name, password, ClientType.valueOf(user));
			
				
				pw.println(resultCustomer);
			
			break;

		default:
			break;
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
			
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			e.printStackTrace();}		
				
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
