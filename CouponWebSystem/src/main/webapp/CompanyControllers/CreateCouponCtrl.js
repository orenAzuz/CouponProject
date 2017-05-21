/**
 * 
 */

var debug = 0;
   (function(){
   var module = angular.module("companyApp");
	
	module.controller("CreateCouponCtrl",CreateCouponCtor);
	
     
	CouponToSend = function(title,startDate,endDate,amount,type,price,image,massage){


		this.title = title;
		this.startDate =new Date (startDate);
		this.endDate =new Date(endDate);
		this.amount = amount;
		this.type = type;
		this.price = price;
		this.image = image;
		this.massage = massage;
	}

	function CreateCouponCtor(CreateCouponServiceAPI)
	{
		var self = this;
		self.couponToSend = new CouponToSend();
		
		  this.create = function ()
		  {
			  console.log(self.couponToSend)
			  CreateCouponServiceAPI.createCoupon(self.couponToSend)
			  	.then(function(response)
	    		 {
			  		debug = response;
			  		  alert("response "+response);
	    		 } ,function(error)
	    		 {
	    			 debug = error;
	    			 alert("error "+error);  
	    		 });
	
		  }
	     
	}

   })();