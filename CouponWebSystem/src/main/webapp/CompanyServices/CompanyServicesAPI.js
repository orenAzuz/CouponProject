/**
 * 
 */
   
   (function(){
	   
		var module = angular.module("companyApp");

		  module.service("CompanyServicesAPI",function($http){
				  
				var self = this;  
				  
		       self.GetCoupon = function () {
		  
		    	var promise = $http.get("http://localhost:8080/CouponWebSystem/webapi/company/coupons");
		    	var promise2 = promise.then(function(response){
								
								return response.data;
							});
		    	return promise2		
				
			}
		       self.getName = function(){
		        	  
		        	  var promise = $http.get("http://localhost:8080/CouponWebSystem/webapi/company/name");
				      var promise2 = promise.then(function(response){
										
										return response.data;
									});
				    	return promise2;		  
		                }
		
		  
		  self.couponByType = function(type){
			  
			  
			  var promise = $http.get("http://localhost:8080/CouponWebSystem/webapi/company/couponsByType/"+type);
			  var promise2 = promise.then(function(response){
				
				  return response.data;
			  });
			  return promise2;
		  }
		  
          self.GetCouponById = function(id){
			  
			  
			  var promise = $http.get("http://localhost:8080/CouponWebSystem/webapi/company/couponById/"+id);
			  var promise2 = promise.then(function(response){
				
				  return response.data;
			  });
			  return promise2;
		  }
          
           self.updateCoupon = function(coupon){
			  
			  console.log(coupon);
        	   return  $http.put("http://localhost:8080/CouponWebSystem/webapi/company/updateCoupon",coupon);
			
		  }
          self.removeCoupon = function(coupon){
        	  
        	  var promise = $http.delete("http://localhost:8080/CouponWebSystem/webapi/company/removeCoupon/"+ coupon.title);
        	  console.log(coupon);
        	  var promise2 = promise.then(function(response){
  				
				  return response.data;
			  });
			  return promise2;
          }
          
          self.GetCouponByType = function(type){
        	  alert(type);
        	  var promise = $http.get("http://localhost:8080/CouponWebSystem/webapi/company/couponsByType/"+
        			  type);
		      var promise2 = promise.then(function(response){
								
								return response.data;
							});
		    	return promise2;		  
                }
          
        	  
          
          
		  } );       
   })();
   
   
   
   
   