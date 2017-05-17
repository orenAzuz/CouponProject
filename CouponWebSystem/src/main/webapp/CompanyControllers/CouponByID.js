/**
 * 
 */


/**
 * 
 */

(function(){

	var module = angular.module("companyApp");
	
	module.controller("GetCponByIdCtrl",GetCponByIdCtor);
	
	
	
	 function GetCponByIdCtor(CompanyServicesAPI)
		{
		    var self = this;
			self.couponData;
			self.coupons;
			self.divMode = false;
			self.type;
			self.id;

	
		  
	this.getCouponById = function()
		  {
		
		  CompanyServicesAPI.GetCouponById(self.id)
		  .then(function(data){
				self.coupons = data;
				console.log(self.coupons);
		     });	  
		  }
		  
		 this.getCouponData = function(index) {
				
				self.coupons[index].endDate = new Date(self.coupons[index].endDate);
				self.coupons[index].startDate = new Date(self.coupons[index].startDate);
				self.couponData = self.coupons[index];
			
				
				self.divMode = true;
				
			}
		 this.updateMode = function(){
			 alert("xzdfsdfsdf");
		 }
		 
		 
		}
})();
