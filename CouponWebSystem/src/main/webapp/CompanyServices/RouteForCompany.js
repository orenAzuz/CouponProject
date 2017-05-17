/**
 * 
 */

	var module = angular.module("companyApp");
	module.controller("RouteCtor",RouteCtor);

	module.config(['$locationProvider', function($locationProvider) {
	$locationProvider.hashPrefix('');
}]);

	module.config(function($routeProvider) {
	$routeProvider
	.when("/", {
		templateUrl : "CompanyHtmlFiles/defultShowCompany.html"
	})
	.when("/CreateCoupon",{
		templateUrl : "CompanyHtmlFiles/createCoupon.html"
	})
	.when("/myCouponCompany", {
		templateUrl : "CompanyHtmlFiles/myCouponsCompany.html" 
	})
	.when("/CouponBy", {
		templateUrl : "CompanyHtmlFiles/CouponBy.html" 
	})
	.when("/CouponByID", {
		templateUrl : "CompanyHtmlFiles/CouponByID.html" 

	});
});

function RouteCtor(){
	
}

