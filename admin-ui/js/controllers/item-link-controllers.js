'use strict';

/* Controllers */

var itemLinkControllers = angular.module('itemLinkControllers', []);

itemLinkControllers.controller('ListLinkItemsController', ['$scope', '$modal', '$resource', '$stateParams', 'RepositorySchemaService', 'RepositoryNameService', 'RepositoryItem', 'RepositoryItemSearchService', 'SpringDataRestAdapter', 'MessageService', 
   function($scope, $modal, $resource, $stateParams, RepositorySchemaService, RepositoryNameService, RepositoryItem, RepositoryItemSearchService, SpringDataRestAdapter, MessageService) {
	
	// Speed up calls to hasOwnProperty
	var hasOwnProperty = Object.prototype.hasOwnProperty;

	function isEmpty(obj) {

	    // null and undefined are "empty"
	    if (obj == null) return true;
	    
	    // Otherwise, does it have any properties of its own?
	    // Note that this doesn't handle
	    // toString and valueOf enumeration bugs in IE < 9
	    for (var key in obj) {
	        if (hasOwnProperty.call(obj, key)) return false;
	    }

	    // Assume if it has a length property with a non-zero value
	    // that that property is correct.
	    console.log('obj.length = ' + obj.length);
	    if(!obj.length)	return true;
	    if (obj.length > 0)    return false;
	    if (obj.length === 0)  return true;

	    

	    return true;
	}
	
	$scope.load = function() {
		$scope.linkName = $stateParams.linkName;
		RepositoryItem.query({repositoryName : $stateParams.repositoryName, repositoryItemId: $stateParams.repositoryItemId, linkName : $stateParams.linkName}, function(LinkRepositoryItemsResponse) {
  		  $scope.linkRepositoryItemsItemResponse = LinkRepositoryItemsResponse;
  		  
  		console.log('isEmpty(LinkRepositoryItemsResponse) = ' + isEmpty(LinkRepositoryItemsResponse));  
  		
  		if(LinkRepositoryItemsResponse._embeddedItems && LinkRepositoryItemsResponse._embeddedItems != null) {
  			$scope.linkRepositoryItems = LinkRepositoryItemsResponse._embeddedItems;
  			$scope.isLinkArray = true;
 		 }  else if(!isEmpty(LinkRepositoryItemsResponse)) {
 			$scope.linkRepositoryItems = [LinkRepositoryItemsResponse];
 			$scope.isLinkArray = false;
 		 }
  		  
  		  console.log("LinkRepositoryItemsResponse: " + JSON.stringify(LinkRepositoryItemsResponse));
  		  
  		  angular.forEach($scope.linkRepositoryItems, function (linkRepositoryItem) {
  			linkRepositoryItem.id = linkRepositoryItem._links.self.href.split(/[/]+/).pop();
  			  console.log("Category name: " + linkRepositoryItem.id);
  		  });
  	  }, function(err) {
  		$scope.linkRepositoryItems = null;
  	  });
	}
	
	$scope.load();
		
      RepositoryNameService.query({repositoryName : $stateParams.repositoryName, propertyName : $stateParams.linkName}, function(LinkRepositoryNameResponse) {
    	  $scope.linkRepositoryName = LinkRepositoryNameResponse.repositoryName;
    	  console.log("LinkRepositoryNameResponse = " + LinkRepositoryNameResponse);
		  console.log("$scope.linkRepositoryName = " + $scope.linkRepositoryName);
		  $scope.repositorySchema = RepositorySchemaService.query({repositoryName : $scope.linkRepositoryName});
	  });
	  
	  
  	  
  	  $scope.repositoryName = $stateParams.repositoryName;
  	  
  	$scope.removeItemFromList = function(idx, repositoryItemIdToRemove) {
		  console.log('idx = ' + idx + ', repositoryItemIdToRemove = ' + repositoryItemIdToRemove);
		  var putData = '';
		  angular.forEach($scope.linkRepositoryItems, function(linkRepositoryItem) {
			  console.log('linkRepositoryItem = ' + linkRepositoryItem._links.self.href);
			  if(linkRepositoryItem.id != repositoryItemIdToRemove) {
				  putData = putData + linkRepositoryItem._links.self.href + '\n';
			  }
		  });
		  console.log('putData = ' + '\'' + putData + '\'');
		  putData = putData.trim();
		  
		  console.log('$stateParams.repositoryName = ' + $stateParams.repositoryName + ' , $stateParams.repositoryItemId = ' + $stateParams.repositoryItemId + ' , $stateParams.linkName = ' + $stateParams.linkName);
		  
		  if(putData == '') {
			  putData = ' ';
		  }
		  
		  console.log('$scope.isLinkArray = ' + $scope.isLinkArray);
		  
		  if(!$scope.isLinkArray && putData == ' ') {
			  RepositoryItem.remove({repositoryName: $stateParams.repositoryName, repositoryItemId: $stateParams.repositoryItemId, linkName : $stateParams.linkName}, function(data) {
				  MessageService.clearAlerts();
				  MessageService.pushAlert({type: 'success', msg: 'Deleted successfully'});
				  $scope.load();
			  }); 
		  } else {
			  RepositoryItem.updateLink({repositoryName: $stateParams.repositoryName, repositoryItemId: $stateParams.repositoryItemId, linkName : $stateParams.linkName}, putData, function(data) {
				  MessageService.clearAlerts();
				  MessageService.pushAlert({type: 'success', msg: 'Deleted successfully'});
				  $scope.load();
			  });
		  }
	  };
  	  
  	  $scope.removeSelectedItemsFromLink = function() {
		  var putData = '';
		  angular.forEach($scope.linkRepositoryItems, function(linkRepositoryItem) {
			  console.log('linkRepositoryItem = ' + linkRepositoryItem._links.self.href);
			  if(linkRepositoryItem.removeFromList != 'true') {
				  putData = putData + linkRepositoryItem._links.self.href + '\n';
			  }
		  });
		  console.log('putData = ' + '\'' + putData + '\'');
		  putData = putData.trim();
		  
		  console.log('$stateParams.repositoryName = ' + $stateParams.repositoryName + ' , $stateParams.repositoryItemId = ' + $stateParams.repositoryItemId + ' , $stateParams.linkName = ' + $stateParams.linkName);
		  
		  if(putData == '') {
			  putData = ' ';
		  }
		  if(!$scope.isLinkArray && putData == ' ') {
			  RepositoryItem.remove({repositoryName: $stateParams.repositoryName, repositoryItemId: $stateParams.repositoryItemId, linkName : $stateParams.linkName}, function(data) {
				  $scope.load();
			  });
		  } else {
			  RepositoryItem.updateLink({repositoryName: $stateParams.repositoryName, repositoryItemId: $stateParams.repositoryItemId, linkName : $stateParams.linkName}, putData, function(data) {
				  $scope.load();
			  });
		  }
  		  
  	  };
  	  
  	  $scope.search = function() {
  	    	 console.info('routeParams.repositoryName:', $scope.linkRepositoryName);
  	    	 console.info('scope.words:', $scope.searchTerms);
  	    	 var wordsArray = [];
  	    	 if($scope.searchTerms) {
  	    		 wordsArray = $scope.searchTerms.split(' ');
  	    	 }
  	    	 var serviceToInvoke = null;
  	    	if(wordsArray.length > 0) {
  	    		 serviceToInvoke = RepositoryItemSearchService;
  	    	} else {
  	    		serviceToInvoke = RepositoryItem;
  	    	}
  	    	serviceToInvoke.query({repositoryName : $scope.linkRepositoryName, words : wordsArray}, function(RepositoryItemsSearchResponse) {
			 $scope.searchResultRepositoryItems = RepositoryItemsSearchResponse._embeddedItems;
  			  angular.forEach($scope.searchResultRepositoryItems, function (searchResultRepositoryItem) {
  				  searchResultRepositoryItem.id = searchResultRepositoryItem._links.self.href.split(/[/]+/).pop();
  				  console.log("Category name: " + searchResultRepositoryItem.id);
  			  });
  	    	 });
  	  };
  	  
  	$scope.addSelectedItemsToLink = function() {
		  var putData = '';
		  angular.forEach($scope.linkRepositoryItems, function(linkRepositoryItem) {
			  console.log('linkRepositoryItem = ' + linkRepositoryItem._links.self.href);
			  putData = putData + linkRepositoryItem._links.self.href + '\n';
		  });
		  angular.forEach($scope.searchResultRepositoryItems, function(searchResultRepositoryItem) {
			  console.log('repositoryItem.addToList = ' + searchResultRepositoryItem.addToList);
			  if(searchResultRepositoryItem.addToList == 'true') {
				  var itemToAdd = searchResultRepositoryItem._links.self.href;
		  		  putData = putData + itemToAdd + '\n';
			  }
		  });
		  console.log('putData = ' + putData);
		  RepositoryItem.updateLink({repositoryName: $stateParams.repositoryName, repositoryItemId: $stateParams.repositoryItemId, linkName : $stateParams.linkName}, putData, function(data) {
			  $scope.load();
		  });
		  
	  };
	  
  	}]);
