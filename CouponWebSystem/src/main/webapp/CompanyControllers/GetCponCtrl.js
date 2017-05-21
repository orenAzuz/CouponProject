/**
 * 
 */

(function(){
	

	var module = angular.module("companyApp");
	
	module.controller("GetCponCtrl",GetCponCtor);

	 function GetCponCtor(CompanyServicesAPI)
	{
      
		var self = this;
		self.divMode = false;
		self.updateMode = false;
		self.couponData;
		
		
		
		
		
		CompanyServicesAPI.GetCoupon()
		.then(function(data){
				self.arr = data;
				});	
		
		
		this.getCouponData = function(index) {
			
			
			
			self.arr[index].endDate = new Date(self.arr[index].endDate);
			self.arr[index].startDate = new Date(self.arr[index].startDate);
			self.couponData = self.arr[index];
		
			
			self.divMode = true;
			
		}

	    
	    this.toggleUpdateMode = function(){
	    	
	    	self.updateMode =! self.updateMode
	    }
	
	
	 
	 this.updateCoupon = function(){
		
		 this.toggleUpdateMode();
		 var saveUpdate = self.couponData;
		 alert(saveUpdate)
		 CompanyServicesAPI.updateCoupon(saveUpdate).then(function(response)
	    		 {
		  		
		  		  console.log("response "+response);
 		 } ,function(error)
 		 {
 			dbg = error;
 			 console.log("error "+error);  
 		 });
	 }
	 
	 this.deleteCoupon = function(){
		 
		 var txt;
		    if (confirm("Are you sure you want to continue deleting!") == true) {
		        txt = "You pressed OK!";
		 
		
		 var deleteCouopon = self.couponData;
		 CompanyServicesAPI.removeCoupon(deleteCouopon).then(function(response)
	    		 {
		
			 self.couponData = undefined;
	  		  console.log("response "+response);
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
	