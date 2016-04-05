'use strict';

/* Controllers */

var listRepositoryItemsControllers = angular.module('listRepositoryItemsControllers', ['brantwills.paging']);

listRepositoryItemsControllers.controller('ListRepositoryItemsController', ['$scope', '$modal', '$resource', '$stateParams', 'RepositorySchemaService', 'RepositoryItem', 'RepositoryItemSearchService', 'SpringDataRestAdapter', 'MessageService', '$filter', 
	function($scope, $modal, $resource, $stateParams, RepositorySchemaService, RepositoryItem, RepositoryItemSearchService, SpringDataRestAdapter, MessageService, $filter) {
	
	  $scope.repositoryName = $stateParams.repositoryName;
	  $scope.currentPage = 1;
	  $scope.pageSize = 20;
	  $scope.repositorySchema = RepositorySchemaService.query({repositoryName : $stateParams.repositoryName});	
	  
	  $scope.load = function() {
		  RepositoryItem.query({repositoryName : $stateParams.repositoryName, repositoryItemId: $stateParams.repositoryItemId, linkName : $stateParams.linkName, page : $scope.currentPage - 1, size : '20', sort : 'displayName'}, function(RepositoryItemsResponse) {
			  $scope.repositoryItems = RepositoryItemsResponse._embeddedItems;
			  $scope.totalResults = RepositoryItemsResponse.page.totalElements; 
			  angular.forEach($scope.repositoryItems, function (repositoryItem) {
				  repositoryItem.id = repositoryItem._links.self.href.split(/[/]+/).pop();
				  console.log("Category name: " + repositoryItem.id);
			  });
		  });
	  }
	  
	  $scope.load();
	  
	  $scope.editRepositoryItem = function(repositoryItem) {
		  $scope.hrefValue = repositoryItem._links.self.href.split(/[/]+/).pop();
	  };
	  
	  $scope.search = function() {
	    	 console.info('routeParams.repositoryName:', $stateParams.repositoryName);
	    	 console.info('scope.words:', $scope.searchTerms);
	    	 var wordsArray = $scope.searchTerms.split(' ');
	    	 RepositoryItemSearchService.query({repositoryName : $stateParams.repositoryName, words : wordsArray}, function(RepositoryItemsResponse) {
			  $scope.repositoryItems = RepositoryItemsResponse._embeddedItems;
			  
			  angular.forEach($scope.repositoryItems, function (repositoryItem) {
				  repositoryItem.id = repositoryItem._links.self.href.split(/[/]+/).pop();
				  console.log("Category name: " + repositoryItem.id);
			  });
		  });
	  };
	  
	  
	  
	  $scope.removeItem = function(idx, repositoryItemId) {
		  console.log('idx = ' + idx + ', repositoryItemId = ' + repositoryItemId);
		  RepositoryItem.remove({repositoryName : $stateParams.repositoryName, repositoryItemId: repositoryItemId}, function(data) {
			  $scope.repositoryItems.splice(idx, 1);
			  MessageService.clearAlerts();
			  MessageService.pushAlert({type: 'success', msg: 'Deleted successfully'});
		  });
  	  };
  	  
	$scope.DoCtrlPagingAct = function(text, page){
		console.log(text, page);
		$scope.currentPage = page;
		$scope.load();
	};
	  
	  
	}]);