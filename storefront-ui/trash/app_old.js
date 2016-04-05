(function() {
	var app = angular.module('store', []);
	app.controller('CategoryController', function($scope, $http) {
		$http.get('/commerce/category/featured').
        success(function(data) {
            $scope.featuredCategory = data;
        });
	});
	
	app.controller('SiteConfigurationController', function($scope, $http) {
		$http.get('/commerce/site/featured').
        success(function(data) {
            $scope.featuredCategory = data;
        });
	});
	
	app.directive('topNav', function() {
		return {
			restrict: 'E',
			templateUrl: '/partials/home/top-nav.html'
		};
	});
	
	app.directive('homePageCarousel', function() {
		return {
			restrict: 'E',
			templateUrl: '/partials/home/home-page-carousel.html'
		};
	});
	
	app.directive('homePageBanner', function() {
		return {
			restrict: 'E',
			templateUrl: '/partials/home/home-page-banner.html'
		};
	});
	
	app.directive('homePageProductList', function() {
		return {
			restrict: 'E',
			templateUrl: '/partials/home/home-page-product-list.html'
		};
	});
	
	app.directive('featuredCategory', function() {
		return {
			restrict: 'E',
			templateUrl: '/partials/home/featured-category.html'
		};
	});
	
})();