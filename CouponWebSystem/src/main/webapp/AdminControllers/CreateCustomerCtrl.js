/**
 * 
 */

      (function(){
    	 var module = angular.module("adminApp");
    		
    		module.controller("CreateCustomerCtrl",CreateCustomerCtor);
    		
    		Customer = function (custName,password){
    			
    			this.custName = custName;
    			this.password = password;
    	
    			
    			
    		} 
    		
    		 function	CreateCustomerCtor (AdminServicesAPI)
    		{
    			
    			var self = this;
    			
    			self.customer = new Customer();
    			
    			this.createCusrtomer = function(){
    				alert("ghd");
    				
    				AdminServicesAPI.createCustomer(self.customer)
    				.then(function(response)
    			    		 {
    					  		
    					  		  alert("response "+response);
    			    		 } ,function(error)
    			    		 {
    			    			 
    			    			 alert("error "+error);  
    			    		 });
    				
    			}
    		}
    	 
    	 
     })();
     