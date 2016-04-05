'use strict';

/* Controllers */

var mlsBrowseSearchControllers = angular.module('mlsBrowseSearchControllers', []);

mlsBrowseSearchControllers.controller('BrowseSearchController', ['$scope', '$state', '$stateParams', 'KeywordSearchService', 'CategoryProductListingService', 
           function($scope, $state, $stateParams, KeywordSearchService, CategoryProductListingService) {
	console.log('Inside BrowseSearchController');
   	
   	$scope.searchCriteria = {};	
   	$scope.searchCriteria.searchTerm = $stateParams.searchTerm;
   	$scope.searchCriteria.categoryId = $stateParams.categoryId;
   	$scope.searchCriteria.page = 0;
   	$scope.searchCriteria.size = 20;
   	
   	$scope.nextPage = function() {
   		console.log('Inside BrowseSearchController.nextPage()');
   		if ($scope.busy) return;
   		if($scope.searchResults && $scope.searchResults.documents && $scope.page >= $scope.searchResults.documents.totalPages) {
   			return;
   		}
   		$scope.busy = true;
   		console.log($scope.searchCriteria);
   		
   		var Service = null;
   		if($scope.searchCriteria.searchTerm) {
   			Service = KeywordSearchService;
   		} else {
   			Service = CategoryProductListingService;
   		}
   		
   		Service.post($scope.searchCriteria, function(ServiceResponse) {
   			if(ServiceResponse && ServiceResponse.documents && ServiceResponse.documents.results && ServiceResponse.documents.results.length > 0) {
   				  var items = ServiceResponse.documents.results;
   				  console.log('ServiceResponse.documents.results = ' + ServiceResponse.documents.results);
   				  if(!$scope.searchResults) {
   					  console.log('!$scope.searchResults');
   		    		  $scope.searchResults = ServiceResponse;
   		    	  } else {
   				      for (var i = 0; i < items.length; i++) {
   			    		  $scope.searchResults.documents.results.push(items[i]);
   				      }
   		    	  }
   				  $scope.searchCriteria.page = $scope.searchCriteria.page + 1;
   			}
   			$scope.busy = false;
   	   	  });	
   	}
   	
   	$scope.filterResults = function(filterName, filterValue) {
   		if(!$scope.searchCriteria.filterCriteria) {
   			$scope.searchCriteria.filterCriteria = {};
   			$scope.searchCriteria.filterCriteria.filters = [];
   		}
   		
   		var result = jQuery.grep($scope.searchCriteria.filterCriteria.filters, function(e){ return e.name == filterName; });
   		if (result.length == 1) {
   			result[0].value = filterValue;
   		} else {
   			var filter = {};
   			filter.name = filterName;
   			filter.value = filterValue;
   			$scope.searchCriteria.filterCriteria.filters.push(filter);
   		}
   		
   		$scope.refreshResults();
   	}
   	
   	$scope.refreshResults = function() {
   		$scope.searchResults = null;
   		$scope.searchCriteria.page = 0;
   		$scope.searchCriteria.size = 20;
   		$scope.nextPage();
   	}
   	
   	$scope.removeFilter = function(filter) {
   		console.log('Removing filter : ' + filter.name);
   		var index = -1;
   		for(var i = 0; i<$scope.searchCriteria.filterCriteria.filters.length; i++) {
   			var testFilter = $scope.searchCriteria.filterCriteria.filters[i];
   			if(testFilter.name == filter.name && testFilter.value == filter.value) {
   				index = i;
   			}
   		}
   		console.log('Removing filter from index : ' + index);
   		$scope.searchCriteria.filterCriteria.filters.splice(index, 1);
   		$scope.refreshResults();
   	/*
   		$scope.searchCriteria.filterCriteria.filters = $.grep($scope.searchCriteria.filterCriteria.filters, function(e) {
   		    return e.name != filterName; 
   		});
   		*/
   	}
   	
      }]);

