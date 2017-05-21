package WebService.CouponWebSystem;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import core.Coupon;
import core.CouponType;
import customExceptions.DuplicateException;
import customExceptions.PurchaseFailedException;
import customExceptions.RanOutOfStockException;
import facade.CompanyFacade;
import facade.CustomerFacade;

@Path("/customer")
public class CustomerService {
	
	@Context HttpServletRequest req;
	
	@GET
	@Path("/coupons")
	@Produces(MediaType.APPLICATION_JSON)
	public Response allCoupons(){
		
		HttpSession sess =  req.getSession();
		facade.CustomerFacade customer = (CustomerFacade)sess.getAttribute("customer");
		ArrayList<Coupon>allCoupons = new ArrayList<Coupon>();
		if(customer!=null){
		try {
			allCoupons = customer.getAllPurchasedCoupon();
		
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			javax.ws.rs.core.Response.ok(e.getMessage())
			.status(500).build();
		}
		System.out.println(allCoupons);
		return javax.ws.rs.core.Response.ok(allCoupons).status(200).build();
	}
		System.out.println("THe CustomerService is null!");
		return javax.ws.rs.core.Response.ok(" Please go back to the login ")
				.status(500).build();
	}

	@PUT
	@Path("/purchaseCoupon/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public void purchaseCoupon(@PathParam("title")String title){
		
		HttpSession sess =  req.getSession();
		facade.CustomerFacade customer = (CustomerFacade)sess.getAttribute("customer");
		
		try {
			Coupon coupon = customer.getCouponByTitle(title);
			customer.purchaseCoupon(coupon);
		} catch (ClassNotFoundException | InterruptedException | SQLException | DuplicateException | RanOutOfStockException | PurchaseFailedException e) {
		
			javax.ws.rs.core.Response.ok(e.getMessage())
			.status(500).build();
		}
	}
	@GET
	@Path("/getPurchasedCouponsByPrice/{price}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response couponsByPrice (@PathParam("price")String price){
		
		ArrayList<Coupon>coupons = null;
		HttpSession sess =  req.getSession();
		facade.CustomerFacade customer = (CustomerFacade)sess.getAttribute("customer");
		try {
			coupons = customer.getAllPurchasedCouponByPrice(Double.parseDouble(price));
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
		
			javax.ws.rs.core.Response.ok(e.getMessage())
			.status(500).build();
		}
		return javax.ws.rs.core.Response.ok(coupons).status(200).build();
		
	}

	@GET
	@Path("/getPurchasedCouponsByType/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response couponsByType (@PathParam("type")String type){
		
		ArrayList<Coupon>coupons = null;
		HttpSession sess =  req.getSession();
		facade.CustomerFacade customer = (CustomerFacade)sess.getAttribute("customer");
		try {
			coupons = customer.getAllPurchasedCouponByType(CouponType.valueOf(type));
			
			
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			javax.ws.rs.core.Response.ok(e.getMessage())
			.status(500).build();
		}
		return javax.ws.rs.core.Response.ok(coupons).status(200).build();
	}
	
	@GET
	@Path("/allTheCouponsInTheSystem")
	@Produces(MediaType.APPLICATION_JSON)
	public Response allTheCouponsInTheSystem(){
		
		ArrayList<Coupon>coupons = null;
		HttpSession sess =  req.getSession();
		facade.CustomerFacade customer = (CustomerFacade)sess.getAttribute("customer");
		try {
			coupons = customer.seeAllCoupons();
			
			
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			 javax.ws.rs.core.Response.ok(e.getMessage())
				.status(500).build();
		}
		return  javax.ws.rs.core.Response.ok(coupons).status(200).build();
		
	}
	
}
