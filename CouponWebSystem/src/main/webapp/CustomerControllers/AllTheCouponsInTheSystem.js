/**
 * 
 */
var dbg

(function(){

var module = angular.module("customerApp");
	
	module.controller("AllTheCouponsCtrl",AllTheCouponsCtor);

	 function AllTheCouponsCtor(CustomerServicesAPI)
	{
		 
		 var self = this;
		     self.divMode = false;
			
			 self.couponData;
			

		  CustomerServicesAPI.GetAllCouponsInSystem()
			.then(function(data){
					self.coupons = data;
					});	
			 
		 
		  this.getCouponData = function(index) {
				
				self.couponData = self.coupons[index];
		
				self.divMode = true;
				
			}
		  
		    this.purchase = function(){
		    	
		   	 var coupon = self.couponData;
			
		   	CustomerServicesAPI.purchaseCoupon(coupon).then(function(response)
		    		 {
			  		dbg = response;
			  		  console.log("success "+response);
			  		  alert("You Purchase a new Coupon name "+coupon.title);
	 		 } ,function(error)
	 		 {
	 			dbg = error;
	 			 console.log("error "+error);  
	 		 });
		    }
		
	
	
	
	}
})();

