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

mlsServices.factory('RepositoryItemService', ['$resource', 'services_url', 
 function($resource, services_url){
   return $resource(services_url + '/data/:repositoryName/:repositoryItemId', {}, {
     query: {
    	 method:'GET', 
    	 params:{
    		 repositoryName:'userProfiles',
    		 repositoryItemId:'1'
 		 }, 
    	 isArray:false
	 },
		update: {
			method: "POST"
		}
   });
 }]);

mlsServices.factory('EntityNamesService', ['$resource', 'services_url', 
  function($resource, services_url){
    return $resource(services_url + '/entities', {}, {
	  query: {
	 	 method:'GET', 
     	 isArray:false
 	 }
   });
 }]);

mlsServices.factory('RepositoryService', ['$resource', 'services_url', 
   function($resource, services_url){
     return $resource(services_url + '/data', {}, {
 	  query: {
 	 	 method:'GET', 
      	 isArray:false
  	 }
    });
  }]);


mlsServices.factory("RepositoryItem", ['$resource', 'services_url', 
   function ($resource, services_url) {
		var response = $resource(services_url + '/data/:repositoryName/:repositoryItemId/:linkName?page=:page&size=:size&sort=:sort', {repositoryItemId: "@id"}, {
			query: {
				method: 'GET', 
				isArray: false,
				params: {page : '0', sort : 'displayName', size : '20'}
			},
			update: {
				method: "PATCH"
			},
			updateLink: {
				method: "PUT",
				headers: {"Content-type" : "text/uri-list"}
			},
	        remove: {
	            method: "DELETE"
	        }
		});
		return response;
	}]);

mlsServices.factory('RepositorySchemaService', ['$resource', 'services_url', 
 function($resource, services_url){
   return $resource(services_url + '/data/:repositoryName/schema', {}, {
     query: {
	 	method:'GET', 
	 	params:{repositoryName:'userProfiles'}, 
	 	isArray:false,
	 	headers:{'Accept':'application/schema+json'} 
	}
   });
 }]);

mlsServices.factory('RepositoryNameService', ['$resource', 'services_url', 
    function($resource, services_url){
      return $resource(services_url + '/data/:repositoryName/getPropertyType?propertyName=:propertyName', {}, {
        query: {
   	 	method:'GET', 
   	 	params:{repositoryName:'userProfiles', propertyName:'fixedParentCategories'}, 
   	 	isArray:false,
   	 	headers:{'Accept':'application/schema+json'} 
   	}
      });
    }]);

mlsServices.factory('RepositoryItemSearchService', ['$resource', 'services_url', 
    function($resource, services_url){
	      return $resource(services_url + '/data/:repositoryName/search/findAllBy', {}, {
	        query: {
	   	 	method:'GET', 
	   	 	params:{repositoryName:'userProfiles', words:[]}, 
	   	 	isArray:false,
	   	}
      });
    }]);


mlsServices.factory('MessageService', function(){
	var alerts = [];
    return {
    	
    	closeAlert : function(index) {
    	    alerts.splice(index, 1);
    	    return alerts;
    	  },     	  
    	clearAlerts : function(index) {
    		  alerts = [];
    		  return alerts;
    	  }, 
    	  pushAlert : function(alert) {
    		  alerts.push(alert);
    		  return alerts;
    	  }, 
    	  getAlerts : function(alert) {
    		  return alerts;
    	  }
    	
        } 
    });

mlsServices.factory('IndexingService', ['$resource', 'services_url', 
      function($resource, services_url){
        return $resource(services_url + '/baselineIndex', {}, {
          triggerBaslineIndex: {
     	 	method:'POST', 
     	 	isArray: false 
          },
          
        });
      }]);


