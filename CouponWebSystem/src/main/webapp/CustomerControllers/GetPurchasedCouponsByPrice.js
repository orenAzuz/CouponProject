/**
 * 
 */


    (function(){

	var module = angular.module("customerApp");
	
	module.controller("GetPurchasedCouponsByPriceCtrl",GetPurchasedCouponsByPriceCtor);
	
	
	
	 function GetPurchasedCouponsByPriceCtor(CustomerServicesAPI)
		{
		    var self = this;
			self.couponData;
			self.coupons;
			self.divMode = false;
			self.type;
			self.price;

	
		  
	this.getCouponByPrice = function()
		  {
		self.divMode = false;
		CustomerServicesAPI.getCouponByPrice(self.price)
		  .then(function(data){
				self.coupons = data;
				console.log(self.coupons);
		     });	  
		  }
		  
		 this.getCouponData = function(index) {
				
				self.couponData = self.coupons[index];
				
				self.divMode = true;
				
			}
		
		 
		 
		}
})();