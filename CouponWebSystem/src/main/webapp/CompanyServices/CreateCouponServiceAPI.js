/**
 * 
 */

   (function(){
	   
		var module = angular.module("companyApp");

		  module.service("CreateCouponServiceAPI",
	   
	     function ($http){
		 
		 var self = this;
		 
		   
		   self.createCoupon = function(couponToSend)
		   {
			   
			  console.log(couponToSend);
			  return  $http.post("http://localhost:8080/CouponWebSystem/webapi/company/createCoupon",
		   			couponToSend);
		   }     
				   
	   }
	 
		  );  
	   
   })();