/**
 * 
 */


  
    (function(){
    	
    	var module = angular.module("adminApp");
    	module.service("AdminServicesAPI",
    	
    			function($http){
    		
    		var self = this;
    		
    		self.GetCompanies = function () {
    			  
		    	var promise = $http.get("http://localhost:8080/CouponWebSystem/webapi/admin/allCompanies");
		    	var promise2 = promise.then(function(response){
								
								return response.data;
							});
		    	return promise2	
		       }
    		
    		
    		self.createCompany = function(company){
    			console.log(company);
    		return $http.post( "http://localhost:8080/CouponWebSystem/webapi/admin/createCompany",company);
    	     }
    		
    		self.updateCompany = function(company){
    			
    	    return $http.put( "http://localhost:8080/CouponWebSystem/webapi/admin/updateCompany",company);
    	    
    			
    		}
    		
    		self.removeCompany = function(company){
    			
    			  var promise = $http.delete("http://localhost:8080/CouponWebSystem/webapi/admin/removeCompany/"+ company.copmName);
            	 
            	  var promise2 = promise.then(function(response){
      				
    				  return response.data;
    			  });
    			  return promise2;
    		}
    		
    		self.GetCustomers = function(){
    			
    			var promise = $http.get("http://localhost:8080/CouponWebSystem/webapi/admin/customers");
		    	var promise2 = promise.then(function(response){
								
								return response.data;
							});
		    	return promise2	
    			
    		}
    		
    		self.updateCustomer = function(customer){
    			
        	    return $http.put( "http://localhost:8080/CouponWebSystem/webapi/admin/updateCustomer",customer);
        	    
        			
        		}
        		
        		self.removeCustomer = function(customer){
        			
        			  var promise = $http.delete("http://localhost:8080/CouponWebSystem/webapi/admin/removeCustomer/"+ customer.custName);
                	 
                	  var promise2 = promise.then(function(response){
          				
        				  return response.data;
        			  });
        			  return promise2;
        		}
        		
    		
    		self.createCustomer = function(customer){
    			
    			return $http.post( "http://localhost:8080/CouponWebSystem/webapi/admin/createCustomer",customer);
    			
    		}
    		
    		 self.GetCustomerByID = function(id){
   			  
   			  
   			  var promise = $http.get("http://localhost:8080/CouponWebSystem/webapi/admin/customerById/"+id);
   			  var promise2 = promise.then(function(response){
   				
   				  return response.data;
   			  });
   			  return promise2;
   		  }
    		
    		 self.GetCompanyByID = function(id){
      			  
      			  
      			  var promise = $http.get("http://localhost:8080/CouponWebSystem/webapi/admin/companyById/"+id);
      			  var promise2 = promise.then(function(response){
      				
      				  return response.data;
      			  });
      			  return promise2;
      		  }
    		
    		
    		
    		
    	}
    	
    	);
    })();