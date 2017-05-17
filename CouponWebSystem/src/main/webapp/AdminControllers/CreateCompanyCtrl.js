/**
 * 
 */
     (function(){
    	 var module = angular.module("adminApp");
    		
    		module.controller("CreateCompanyCtrl",CreateCompanyCtor);
    		
    		 function	CreateCompanyCtor (CreateCompanyService)
    		{
    			
    			var self = this;
    			
    			self.company ={
    				
    			
    			    "id" : "1",
					"copmName" : name,
					"password" : password,
					"email" : email
    				
    				
    			}
    			
    			self.createCompany = function(){
    				
    				
    				CreateCompanyService.create(self.company);
    				
    			}
    		}
    	 
    	 
     })();
     
     