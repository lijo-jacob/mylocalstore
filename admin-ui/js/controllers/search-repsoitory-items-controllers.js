'use strict';

/* Controllers */

var searchRepsoitoryItemsControllers = angular.module('searchRepsoitoryItemsControllers', []);

searchRepsoitoryItemsControllers.controller('SearchRepositoryItemsController', ['$scope', '$resource', '$stateParams', '$location', 'RepositorySchemaService', 'RepositoryItem', 'RepositoryItemSearchService', 'SpringDataRestAdapter',
	function($scope, $resource, $stateParams, $location, RepositorySchemaService, RepositoryItem, RepositoryItemSearchService, SpringDataRestAdapter) {
	  $scope.repositorySchema = RepositorySchemaService.query({repositoryName : $stateParams.repositoryName});	
	  
	  $scope.repositoryName = $stateParams.repositoryName;
	  $scope.orderProp = 'age';
	 
	  var searchTerms = $stateParams.searchTerms;
	  if(!searchTerms) {
		  var listItemsPath = $location.path().replace('/search', ''); 
		  $location.path(listItemsPath);
		  return;
	  }
	  console.log('searchTerms = ' + searchTerms)
	  if(searchTerms) {
		 var wordsArray = searchTerms.split(' ');
    	 RepositoryItemSearchService.query({repositoryName : $stateParams.repositoryName, words : wordsArray}, function(RepositoryItemsResponse) {
    		 console.log('Inside RepositoryItemsResponse');
		  $scope.repositoryItems = RepositoryItemsResponse._embeddedItems;
		  
		  angular.forEach($scope.repositoryItems, function (repositoryItem) {
			  repositoryItem.id = repositoryItem._links.self.href.split(/[/]+/).pop();
			  console.log("Category name: " + repositoryItem.id);
		  });
    	 });
	  }
	  
	  $scope.editRepositoryItem = function(repositoryItem) {
		  $scope.hrefValue = repositoryItem._links.self.href.split(/[/]+/).pop();
	  };
	  
	  $scope.search = function() {
    	 console.info('routeParams.repositoryName:', $stateParams.repositoryName);
    	 console.info('scope.words:', $scope.searchTerms);
    	 
    	 var relativeLinkPath = null;
    	 if($stateParams.searchTerms) {
    		 relativeLinkPath = $location.path().replace($stateParams.searchTerms, $scope.searchTerms);
    	 } else {
    		 relativeLinkPath = $location.path() + '/' + $scope.searchTerms;
    	 }
    	 
    	 $location.path(relativeLinkPath);
	  };
	  
	  
	}]);