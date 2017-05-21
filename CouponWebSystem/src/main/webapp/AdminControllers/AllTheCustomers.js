/**
 * 
 */

    
       
       (function(){
    		

    		var module = angular.module("adminApp");
    		
    		module.controller("AllTheCustomersCtrl",AllTheCusromersCtor);

    		 function AllTheCusromersCtor(AdminServicesAPI)
    		{
    	      
    			var self = this;
    			self.customersDivMode = false;
    			self.updateMode = false;
    			self.customersData;
    			self.coupons;
    			self.couponData;
    			self.couponDivMode = false;
    			
    			
    			AdminServicesAPI.GetCustomers()
    			.then(function(data){
    					self.custData = data;
    					});	
    			
    			
    			this.getCustData = function(index) {
   		
    				self.customersData = self.custData[index];
    			
    				self.coupons =  self.custData[index].coupon;
    				
    				self.customersDivMode = true;
    				
    			}
    			
    			this.getCouponData = function(index){
    				
    				self.couponData = self.coupons[index];
    				
    				self.couponDivMode = true;
    			}
    				
    				
  

    		    
    		    this.toggleUpdateMode = function(){
    		    	
    		    	self.updateMode =! self.updateMode
    		    }
    		
    		
    		 
    		 this.updateCustomer = function(){
    			
    			 this.toggleUpdateMode();
    			 var saveUpdate = self.customersData;
    			 alert(saveUpdate)
    			 AdminServicesAPI.updateCustomer(saveUpdate).then(function(response)
    		    		 {
    			  		
    			  		  console.log("response "+response);
    	 		 } ,function(error)
    	 		 {
    	 			dbg = error;
    	 			 console.log("error "+error);  
    	 		 });
    		 }
    		 
    		 this.deleteCustomer = function(){
    			 
    			 
    			    if (confirm("Are you sure you want to continue deleting!") == true) {
    			        
    			 
    			
    			 var deleteCustomer = self.customersData;
    			 AdminServicesAPI.removeCustomer(deleteCustomer).then(function(response)
    		    		 {
    				 
    		  		  console.log("response "+response);
    		  		// window.location.reload();
    		 } ,function(error)
    		 {
    			dbg = error;
    			 console.log("error "+error);  
    		 });
    			 
    			
    			    } else {
    				       
    			    }
    			    
    		 }
    		 
    		 
    		 
    		 
    		 
    		 
    		 
    		 
    		 
    		 
    		 }
    		})();