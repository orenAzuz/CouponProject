package WebService.CouponWebSystem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import core.Company;
import core.Coupon;
import core.Customer;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
import dbDAO.CompList;
import facade.AdminFacade;
import facade.ClientType;
import facade.CompanyFacade;
import facade.CouponSystem;
import loginPackage.TheLogin;

/**
 * Root resource (exposed at "admin" path)
 */


@Path("/admin")
public class AdminService {
	
	@Context HttpServletRequest req;
	@Context HttpServletResponse response;
	
	private void setResponse(int status,String msg) throws IOException{
		
		
		//response.setStatus(status);
		//response.getWriter().println( msg);
		//response.flushBuffer();
		//response.getWriter().close();
	}


   @GET
   @Path("/allCompanies")
   @Produces(MediaType.APPLICATION_JSON)
   public Response allCompanies() {

	ArrayList<Company>clist = null;
	HttpSession sess =  req.getSession();
	facade.AdminFacade admin = (AdminFacade)sess.getAttribute("admin");
	
	try {
		
		if(admin!=null){
	
		clist = (admin.getAllCompanies());
	
		}
		else{
			System.out.println("THe admin is null!!");}
	} catch (ClassNotFoundException | SQLException | InterruptedException e) {
		
		//System.err.println(e.getMessage());
		e.printStackTrace();
		
	}
	System.out.println(clist);
	
	return javax.ws.rs.core.Response.ok(clist).status(200).build();
}

    @GET
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Customer>allCustomers(){
    	ArrayList<Customer>allCust = null;
    	HttpSession sess =  req.getSession();
    	facade.AdminFacade admin = (AdminFacade)sess.getAttribute("admin");
    	
    	if(admin!=null){
    		
    		try {

			allCust = admin.getAllCustomer();
			System.out.println(allCust);
			return  admin.getAllCustomer();
			} catch (ClassNotFoundException | SQLException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    		
    	}
   
    	return null;
    }

 	@POST
 	@Path("/createCompany")
 	@Consumes(MediaType.APPLICATION_JSON)
    public Response createCompany( Company company) throws IOException{
 	
 		System.out.println(company);
 		HttpSession sess =  req.getSession();
    	facade.AdminFacade admin = (AdminFacade)sess.getAttribute("admin");
 	
    	try {
    	
			admin.createCompany(company);
			setResponse(201, "success!");
			System.out.println("hi");
		} catch (ClassNotFoundException | SQLException | InterruptedException | DuplicateException  e) {
		   
			javax.ws.rs.core.Response.ok(e.getMessage())
			.status(500).build();

		}
    	return javax.ws.rs.core.Response.ok(" The coupon "+ company.getCopmName() + 
				" Successfully created").status(200).build();

    	
    }
    
	@POST
 	@Path("/createCustomer")
 	@Consumes(MediaType.APPLICATION_JSON)
    public void createCustomer( Customer customer){
 		
 		HttpSession sess =  req.getSession();
    	facade.AdminFacade admin = (AdminFacade)sess.getAttribute("admin");
    	
    	try {
			admin.createCustomer(customer);
		} catch (ClassNotFoundException | SQLException | InterruptedException | DuplicateException e) {
			javax.ws.rs.core.Response.ok(e.getMessage())
			.status(500).build();

		}
    	
    	
    }
    @DELETE
    @Path("/removeCompany/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
	public void removeComapny(@PathParam("name")String name){
		
    	Company company = new Company();
    	company.setCopmName(name);
    	HttpSession sess =  req.getSession();
    	facade.AdminFacade admin = (AdminFacade)sess.getAttribute("admin");
    	try {
			admin.removeCompany(company);
		} catch (ClassNotFoundException | SQLException | InterruptedException | NotAvailableException e) {
			javax.ws.rs.core.Response.ok(e.getMessage())
			.status(500).build();

		}
		
	}
    @DELETE
    @Path("/removeCustomer/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
	public void removeCustomer(@PathParam("name")String name ){
		
    	Customer customer = new Customer();
    	customer.setCustName(name);
    	HttpSession sess =  req.getSession();
    	facade.AdminFacade admin = (AdminFacade)sess.getAttribute("admin");
    	try {
			admin.removeCustomer(customer);
		} catch (ClassNotFoundException | SQLException | InterruptedException | NotAvailableException e) {
			javax.ws.rs.core.Response.ok(e.getMessage())
			.status(500).build();

		}
		
	}
    @PUT
    @Path("/updateCompany")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateComopany(Company company)
    {
    	HttpSession sess =  req.getSession();
    	facade.AdminFacade admin = (AdminFacade)sess.getAttribute("admin");	
    	
    	try {
			admin.updateCompany(company);
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			javax.ws.rs.core.Response.ok(e.getMessage())
			.status(500).build();

		}
    }
    
    @PUT
    @Path("/updateCustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCustomer(Customer customer)
    {
    	HttpSession sess =  req.getSession();
    	facade.AdminFacade admin = (AdminFacade)sess.getAttribute("admin");	
    	
    	try {
			admin.updateCustomet(customer);
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			javax.ws.rs.core.Response.ok(e.getMessage())
			.status(500).build();

		}
    }
    @GET
    @Path("/customerById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id")Long id){
    	
        Customer customer = null;
    	HttpSession sess =  req.getSession();
    	facade.AdminFacade admin = (AdminFacade)sess.getAttribute("admin");	
    	try {
			customer = admin.getCustomer(id);
		} catch (ClassNotFoundException | SQLException | InterruptedException | NotAvailableException e) {
			javax.ws.rs.core.Response.ok(e.getMessage())
			.status(500).build();

		}
    	
    	return javax.ws.rs.core.Response.ok(customer).status(200).build();
    }
    @GET
    @Path("/companyById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompanyById(@PathParam("id")Long id){
    	
        Company company = null;
    	HttpSession sess =  req.getSession();
    	facade.AdminFacade admin = (AdminFacade)sess.getAttribute("admin");	
    	try {
			company = admin.getCompanyById(id);
		} catch (ClassNotFoundException | SQLException | InterruptedException | NotAvailableException e) {
		
			javax.ws.rs.core.Response.ok(e.getMessage())
			.status(500).build();

		}
    	
    	return javax.ws.rs.core.Response.ok(company).status(200).build();
    }
    
    
    
    
    

}