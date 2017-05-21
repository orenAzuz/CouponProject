/**
 * 
 */

      (function(){

	var module = angular.module("customerApp");
	
	module.controller("GetPurchasedCouponsByTypeCtrl",GetPurchasedCouponsByTypeCtor);
	
	
	
	 function GetPurchasedCouponsByTypeCtor(CustomerServicesAPI)
		{
		    var self = this;
			self.couponData;
			self.coupons;
			self.divMode = false;
			self.type;
			

	this.getCouponByType = function()
		 {
		self.divMode = false;
		CustomerServicesAPI.GetCouponByType(self.type)
			.then(function(data){
					self.coupons = data;		
			});	
		 }
		  
		 this.getCouponData = function(index) {
				
				self.couponData = self.coupons[index];
			
				
				self.divMode = true;
				
			}

		 
		 
		}
})();