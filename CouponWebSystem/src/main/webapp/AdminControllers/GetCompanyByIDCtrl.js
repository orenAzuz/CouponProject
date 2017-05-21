/**
 * 
 */


         

(function(){

	var module = angular.module("adminApp");
	
	module.controller("GetCompanyByIDCtrl",GetCompanyByIDCtor);
	
	
	
	 function GetCompanyByIDCtor(AdminServicesAPI)
		{
		    var self = this;
			self.couponData;
			self.coupons;
			self.divMode = false;
			self.type;
			self.id;
			self.companyDiv = false;

	
		  
	this.getCompanyByID = function()
		  {
	      self.divMode = false;
	      
	      AdminServicesAPI.GetCompanyByID(self.id)
		  .then(function(data)
			{
			  self.company = data;
				self.coupons = self.company.coupons;
				self.companyDiv = true;
				console.log(self.coupons);
		     });	  
		  }
		  
		 this.getCouponData = function(index) {
				
				self.couponData = self.coupons[index];
			
				self.divMode = true;
				
			}


		    
		    this.toggleUpdateMode = function(){
		    	
		    	self.updateMode =! self.updateMode
		    }
		
		
		 
		 this.updateCompany = function(){
			
			 this.toggleUpdateMode();
			 var saveUpdate = self.company;
			 alert(saveUpdate)
			 AdminServicesAPI.updateCompany(saveUpdate).then(function(response)
		    		 {
			  		
			  		  console.log("response "+response);
	 		 } ,function(error)
	 		 {
	 			dbg = error;
	 			 console.log("error "+error);  
	 		 });
		 }
		 
		 this.deleteCompany = function(){
			 
			 
			    if (confirm("Are you sure you want to continue deleting!") == true) {
			        
			 
			
			 var deleteCompany = self.company;
			 AdminServicesAPI.removeCompany(deleteCompany).then(function(response)
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