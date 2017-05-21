/**
 * 
 */

       
       
       (function(){
    		

    		var module = angular.module("adminApp");
    		
    		module.controller("AllTheCompaniesCtrl",AllTheCompaniesCtor);

    		 function AllTheCompaniesCtor(AdminServicesAPI)
    		{
    	      
    			var self = this;
    			self.companyDivMode = false;
    			self.updateMode = false;
    			self.companiesData;
    			self.coupons;
    			self.couponData;
    			self.couponDivMode = false;
    			
    			
    			AdminServicesAPI.GetCompanies()
    			.then(function(data){
    					self.compData = data;
    					});	
    			
    			
    			this.getCompData = function(index) {
   		
    				self.companiesData = self.compData[index];
    			
    				self.coupons =  self.compData[index].coupons;
    				
    				self.companyDivMode = true;
    				
    			}
    			
    			this.getCouponData = function(index){
    				
    				self.couponData = self.coupons[index];
    				
    				self.couponDivMode = true;
    			}
    				
    				
  

    		    
    		    this.toggleUpdateMode = function(){
    		    	
    		    	self.updateMode =! self.updateMode
    		    }
    		
    		
    		 
    		 this.updateCompany = function(){
    			
    			 this.toggleUpdateMode();
    			 var saveUpdate = self.companiesData;
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
    			        
    			 
    			
    			 var deleteCompany = self.companiesData;
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
    		