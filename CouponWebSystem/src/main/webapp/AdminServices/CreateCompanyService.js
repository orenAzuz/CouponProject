/**
 * 
 */
  
    (function(){
    	
    	var moule = angular.module("adminApp");
    	module.service("CreateCompanyService",
    	
    			function($http){
    		
    		var self = this;
    		self.create = function(company){
    		$http.post( "http://localhost:8080/CouponWebSystem/webapi/admin/createCompany",company)
    	     }
    		
    		
    	}
    	
    	);
    })();