package WebService.CouponWebSystem;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	public ArrayList<Coupon> allCoupons(){
		
		HttpSession sess =  req.getSession();
		facade.CustomerFacade customer = (CustomerFacade)sess.getAttribute("customer");
		ArrayList<Coupon>allCoupons = new ArrayList<Coupon>();
		if(customer!=null){
		try {
			allCoupons = customer.getAllPurchasedCoupon();
		
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.println(allCoupons);
		return allCoupons;
	}
		System.out.println("THe CustomerService is null!");
		return null;
	}

	@POST
	@Path("/purchaseCoupon/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public void purchaseCoupon(@PathParam("title")String title){
		
		HttpSession sess =  req.getSession();
		facade.CustomerFacade customer = (CustomerFacade)sess.getAttribute("customer");
		
		try {
			Coupon coupon = customer.getCouponByTitle(title);
			customer.purchaseCoupon(coupon);
		} catch (ClassNotFoundException | InterruptedException | SQLException | DuplicateException | RanOutOfStockException | PurchaseFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@GET
	@Path("/getPurchasedCouponsByPrice/{price}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Coupon> couponsByPrice (@PathParam("price")double price){
		
		ArrayList<Coupon>coupons = null;
		HttpSession sess =  req.getSession();
		facade.CustomerFacade customer = (CustomerFacade)sess.getAttribute("customer");
		try {
			coupons = customer.getAllPurchasedCouponByPrice(price);
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coupons;
		
	}

	@GET
	@Path("/getPurchasedCouponsByType/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Coupon> couponsByType (@PathParam("type")String type){
		
		ArrayList<Coupon>coupons = null;
		HttpSession sess =  req.getSession();
		facade.CustomerFacade customer = (CustomerFacade)sess.getAttribute("customer");
		try {
			coupons = customer.getAllPurchasedCouponByType(CouponType.valueOf(type));
			
			
		} catch (ClassNotFoundException | SQLException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coupons;
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
