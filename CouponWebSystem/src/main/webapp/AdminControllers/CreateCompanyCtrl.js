/**
 * 
 */
     (function(){
    	 var module = angular.module("adminApp");
    		
    		module.controller("CreateCompanyCtrl",CreateCompanyCtor);
    		
    		Company = function (copmName,password,email){
    			
    			this.copmName = copmName;
    			this.password = password;
    			this.email = email;
    			
    			
    		} 
    		
    		 function	CreateCompanyCtor (AdminServicesAPI)
    		{
    			
    			var self = this;
    			
    			self.company = new Company();
    			
    			this.createCompany = function(){
    				alert("ghd");
    				
    				AdminServicesAPI.createCompany(self.company)
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
     
     