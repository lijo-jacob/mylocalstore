'use strict';

/* Services */

var mlsServices = angular.module('mlsServices', ['ngResource']);

mlsServices.constant('services_url', '/services');

mlsServices.factory('Phone', ['$resource',
  function($resource){
    return $resource('mock/services/phones/:phoneId.json', {}, {
      query: {method:'GET', params:{phoneId:'phones'}, isArray:true}
    });
  }]);


mlsServices.factory('Slide', ['$resource',
  function($resource){
    return $resource('mock/services/slides/:slideId.json', {}, {
      query: {method:'GET', params:{slideId:'slides'}, isArray:true}
    });
  }]);

mlsServices.factory('HomePageFeaturedProducts', ['$resource',
  function($resource){
    return $resource('mock/services/featured-products/:featuredProductsListId.json', {}, {
      query: {method:'GET', params:{featuredProductsListId:'featured-products-1'}, isArray:false}
    });
  }]);


mlsServices.factory('HomePageFeaturedCategory', ['$resource',
 function($resource){
   return $resource('mock/services/featured-category/:featuredCategoryId.json', {}, {
     query: {method:'GET', params:{featuredCategoryId:'featured-category-1'}, isArray:false}
   });
 }]);

mlsServices.factory('OfferCardsService', ['$resource',
 function($resource){
   return $resource('mock/services/offer-cards/:offerCardsId.json', {}, {
     query: {method:'GET', params:{offerCardsId:'offer-cards-1'}, isArray:false}
   });
 }]);

mlsServices.factory('CatalogBrowseService', ['$resource', 'services_url', 
  function($resource, services_url){
    return $resource(services_url + '/defaultCatalog', {}, {
      query: {
 	 	method:'GET', 
 	 	params:{siteId: null}, 
 	 	isArray: false 
      }
    });
  }]);

mlsServices.factory('CategoryProductListingService', ['$resource', 'services_url', 
 function($resource, services_url){
   return $resource(services_url + '/category/browse', {}, {
     post: {
	 	method:'POST', 
	 	params:{searchCriteria: null}, 
	 	isArray: false 
     }
   });
 }]);

mlsServices.factory('KeywordSearchService', ['$resource', 'services_url', 
  function($resource, services_url){
    return $resource(services_url + '/search/keywords', {}, {
      post: {
 	 	method:'POST', 
 	 	params:{searchCriteria: null}, 
 	 	isArray: false 
      }
    });
  }]);


mlsServices.factory('ProductDetailService', ['$resource', 'services_url', 
 function($resource, services_url){
   return $resource(services_url + '/product/:productDocumentId', {}, {
     query: {
	 	method:'GET', 
	 	params:{categoryId: null}, 
	 	isArray: false 
     }
   });
 }]);

mlsServices.factory('SignupService', ['$resource', 'services_url', 
 function($resource, services_url){
   return $resource(services_url + '/signup', {}, {
     signup: {
	 	method:'POST', 
	 	params:{user: null}, 
	 	isArray: false 
     },
     
   });
 }]);

mlsServices.factory('UserProfileService', ['$resource', 'services_url', 
  function($resource, services_url){
    return {
    	signup: $resource(services_url + '/signup', {}, {post: {
	 	 	method:'POST', 
	 	 	params:{user: null}, 
	 	 	isArray: false 
	      }}),
	      login: $resource(services_url + '/login', {}, {post:{
		 	 	method:'POST', 
		 	 	params:{user: null}, 
		 	 	isArray: false 
		      }}),
		  logout: $resource(services_url + '/logout', {}, {post:{
	 	 	method:'POST', 
	 	 	params:{user: null}, 
	 	 	isArray: false 
	      }})
    }
  }]);

mlsServices.factory('ShoppingCartService', ['$resource', 'services_url', 
   function($resource, services_url){
     return {
     	addToCart: $resource(services_url + '/cart/add', {}, {post: {
 	 	 	method:'POST', 
 	 	 	params:{addToCartDTO: null}, 
 	 	 	isArray: false 
 	      }})
     }
   }]);

mlsServices.factory('AuthService', ['$resource', 'services_url', 'UserProfileService', 
  function($resource, services_url, UserProfileService){
	var currentUser;
	return {
		login: function(user) {
			console.log('user = ' + user.login);
			UserProfileService.login.post({}, user, function(UserProfileServiceResponse) {
				currentUser = UserProfileServiceResponse;
			});
		},
		signup: function(newUser) {
			console.log('newUser = ' + newUser.login);
			UserProfileService.signup.post({}, newUser, function(UserProfileServiceResponse) {
				currentUser = UserProfileServiceResponse;
			});
		},
		logout: function() {
			currentUser = null;
			UserProfileService.logout.post({}, user, function(UserProfileServiceResponse) {
				currentUser = null;
			});
		},
		isLoggedIn: function() {
			console.log('Inside isLoggedIn: ' + (currentUser != null));
			return (currentUser != null)
		},
		currentUser: function() {
			console.log('Inside currentUser');
			return currentUser;
		}
	}
  }]);

