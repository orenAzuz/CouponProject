/**
 * 
 */
 
 
 

 (function(){

 var module = angular.module("customerApp");
 	
 	module.controller("MyPurchaseCouponsCtrl",MyPurchaseCouponsCtor);

 	 function MyPurchaseCouponsCtor(CustomerServicesAPI)
 	{
 		 
 		 var self = this;
 		     self.divMode = false;
 			
 			 self.couponData;
 			

 		  CustomerServicesAPI.MyPurchaseCoupons()
 			.then(function(data){
 					self.coupons = data;
 					});	
 			 
 		 
 		  this.getCouponData = function(index) {
 
 				self.couponData = self.coupons[index];
 			
 				
 				self.divMode = true;
 				
 			}
 		  
 	
 	
 	
 	}
 })();
