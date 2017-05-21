/**
 * 
 */


      

(function(){

	var module = angular.module("adminApp");
	
	module.controller("GetCustomerByIDCtrl",GetCustomerByIDCtor);
	
	
	
	 function GetCustomerByIDCtor(AdminServicesAPI)
		{
		    var self = this;
			self.couponData;
			self.coupons;
			self.divMode = false;
			self.type;
			self.id;
			self.customerDiv = false;

	
		  
	this.getCustomerByID = function()
		  {
	      self.divMode = false;
	      AdminServicesAPI.GetCustomerByID(self.id)
		  .then(function(data){
			  self.customer = data;
				self.coupons = self.customer.coupon;
				self.customerDiv = true;
				console.log(self.coupons);
		     });	  
		  }
	

    this.toggleUpdateMode = function(){
    	
    	self.updateMode =! self.updateMode
    }

		  
		 this.getCouponData = function(index) {
				
				self.couponData = self.coupons[index];
			
				self.divMode = true;
				
			}

		 this.updateCustomer = function(){
			
			 this.toggleUpdateMode();
			 var saveUpdate = self.customer;
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
			        
			 
			
			 var deleteCustomer = self.customer;
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