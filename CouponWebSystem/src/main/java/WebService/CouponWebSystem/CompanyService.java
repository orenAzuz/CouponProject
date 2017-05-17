package WebService.CouponWebSystem;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
import core.CouponType;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
import facade.ClientType;
import facade.CompanyFacade;
import facade.CouponSystem;

@Path("/company")
public class CompanyService {

	@Context HttpServletRequest req;
	@Context HttpServletResponse res;
	
	@GET
	@Path("/name")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getName(){
		HttpSession sess =  req.getSession();
		String name = (String) sess.getAttribute("name");
		System.out.println(name);
		CouponConductor coupon = new CouponConductor();
		coupon.setTitle(name);
		return  javax.ws.rs.core.Response.ok(coupon).status(200).build();
	}
	
	@GET
	@Path("/coupons")
	@Produces(MediaType.APPLICATION_JSON)
	public Response allCoupons(){
		
		HttpSession sess =  req.getSession();
		facade.CompanyFacade company = (CompanyFacade)sess.getAttribute("company");
		ArrayList<Coupon>allCoupons = new ArrayList<Coupon>();
		if(company!=null){
		try {
			allCoupons = company.getAllCoupon();
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.println(allCoupons);
		return  javax.ws.rs.core.Response.ok(allCoupons).status(200).build();
	}	
		return  javax.ws.rs.core.Response.ok("Pleas go back to the login ").status(500).build();
	}

	@POST
	@Path("/createCoupon")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void CreateCoupon(CouponConductor couponc) throws ClassNotFoundException, SQLException{
	
		HttpSession sess =  req.getSession();
		facade.CompanyFacade company = (CompanyFacade)sess.getAttribute("company");
		
		try 
		{
			company.creatCoupon(couponc.inject());
		} 
		catch (ClassNotFoundException | SQLException | InterruptedException | DuplicateException e)
		{
			 javax.ws.rs.core.Response.ok(e.getMessage())
					.status(500).build();
		
		}
		 javax.ws.rs.core.Response.ok(" The coupon "+ couponc.getTitle() + 
				" Successfully created").status(200).build();

		
	}			

	@DELETE
	@Path("/removeCoupon/{couponToRemove}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeCoupon(@PathParam("couponToRemove")String title){
		System.out.println(title);
		Coupon coupon = new Coupon();
		coupon.setTitle(title);
		HttpSession sess =  req.getSession();
		facade.CompanyFacade company = (CompanyFacade)sess.getAttribute("company");
		try {
			company.removeCoupon(coupon);
			javax.ws.rs.core.Response.ok(" The coupon "+ coupon.getTitle() + 
					" Successfully removed").status(200).build();

		} catch (ClassNotFoundException | SQLException | InterruptedException | NotAvailableException e) {
			
			javax.ws.rs.core.Response.ok(e.getMessage())
			.status(500).build();
		}
	}

	@GET
	@Path("/couponsByType/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCouponByType(@PathParam("type")String couponType){
		
		HttpSession sess =  req.getSession();
		facade.CompanyFacade company = (CompanyFacade)sess.getAttribute("company");
		ArrayList<Coupon> coupon = null;
		try {
			coupon = company.getCouponByType(CouponType.valueOf(couponType));
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			javax.ws.rs.core.Response.ok(e.getMessage())
			.status(500).build();
		}
		
		return javax.ws.rs.core.Response.ok(coupon).status(200).build();
	}

	@GET
	@Path("/couponById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getCouponById (@PathParam("id")long id){
  
		ArrayList<Coupon>couponList = new ArrayList<>();
		HttpSession sess =  req.getSession();
		facade.CompanyFacade company = (CompanyFacade)sess.getAttribute("company");

		try {
			couponList.add(company.getCouponById(id));
		} catch (ClassNotFoundException | SQLException | InterruptedException | NotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return javax.ws.rs.core.Response.ok(couponList).status(200).build();
		
	}

	@PUT
	@Path("/updateCoupon")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void update(CouponConductor coupon){

		HttpSession sess =  req.getSession();
		facade.CompanyFacade company = (CompanyFacade)sess.getAttribute("company");
		try {
			company.updateCoupon(coupon.inject());
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			javax.ws.rs.core.Response.ok(e.getMessage())
			.status(500).build();
		}
		
	}
}



