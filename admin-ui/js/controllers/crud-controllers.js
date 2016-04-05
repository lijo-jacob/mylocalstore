'use strict';

/* Controllers */

var crudControllers = angular.module('crudControllers', []);

crudControllers.constant('image_server_url', '/catalog-media/');

crudControllers.controller('CrudOperationsController', ['$scope', '$modal', '$window', '$location', '$state', '$stateParams', 'RepositorySchemaService', 'RepositoryItem', 'MessageService', 
   function($scope, $modal, $window, $location, $state, $stateParams, RepositorySchemaService, RepositoryItem, MessageService) {
	 $scope.alerts = MessageService.getAlerts();
 	 $scope.repositorySchema = RepositorySchemaService.query({repositoryName : $stateParams.repositoryName});
	 
	 $scope.repositoryItem = new RepositoryItem();
	 if($stateParams.repositoryItemId) {
		 RepositoryItem.get({repositoryName: $stateParams.repositoryName, repositoryItemId: $stateParams.repositoryItemId}, function(RepositoryItemResponse) {
			 $scope.repositoryItem = RepositoryItemResponse;
			 $scope.repositoryItem.id = $scope.repositoryItem._links.self.href.split(/[/]+/).pop();
		 });
		 $scope.repositoryItemId = $stateParams.repositoryItemId;
		 console.log('Inside CrudOperationsController, repositoryItemId = ' + $scope.repositoryItemId);
	 }
	 
	 $scope.addTextBox = function(propertyName) {
		 console.log('propertyName = ' + propertyName);
		 if($scope.repositoryItem[propertyName] == null) {
			 $scope.repositoryItem[propertyName] = [];
		 }
		 $scope.repositoryItem[propertyName].push('');
		 console.log('$scope.repositoryItem[propertyName] = ' + $scope.repositoryItem[propertyName]); 
	 };
	 
	 $scope.deleteMultiValue = function(propertyName, index) {
		 console.log('Indisde removeItem(), (propertyName, index) = (' + propertyName + ', ' + index + ')' ); 
		 $scope.repositoryItem[propertyName].splice(index, 1);
	 };
	 
     $scope.save = function() {
    	 $scope.alerts = MessageService.clearAlerts();
    	 console.info('$stateParams.repositoryName:', $stateParams.repositoryName);
    	 console.info('$stateParams.repositoryItemId:', $stateParams.repositoryItemId);
    	 if($stateParams.repositoryItemId) {
    		 $scope.repositoryItem.$update({repositoryName: $stateParams.repositoryName, repositoryItemId: $stateParams.repositoryItemId}, function(data, headers) {
    			 $scope.alerts.push({type: 'success', msg: 'Saved successfully'});
			 }, function(err){
				 $scope.alerts.push({type: 'danger', msg: 'Operation failed'});
				 console.log('Operation failed');
			 });
    	 } else {
    		 $scope.repositoryItem.$save({repositoryName: $stateParams.repositoryName}, function(data, headers) {
    			 var newId = headers('Location').split(/[/]+/).pop();
    			 $location.path('/crud/' + $stateParams.repositoryName + '/' + newId);
    			 MessageService.clearAlerts();
    			 MessageService.pushAlert({type: 'success', msg: 'Saved successfully'});
			 }, function(err){
				 $scope.alerts.push({type: 'danger', msg: 'Operation failed'});
				 console.log('Operation failed');
			 });
    	 }
 	};
 	
 	$scope.deleteItem = function() {
   	 $scope.alerts = MessageService.clearAlerts();
   	 console.info('$stateParams.repositoryName:', $stateParams.repositoryName);
   	 console.info('$stateParams.repositoryItemId:', $stateParams.repositoryItemId);
   	 if($stateParams.repositoryItemId) {
   		 $scope.repositoryItem.$remove({repositoryName: $stateParams.repositoryName, repositoryItemId: $stateParams.repositoryItemId}, function(data, headers) {
   			 	$scope.alerts.push({type: 'success', msg: 'Deleted successfully'});
   			 	$state.go('^.list')
			 }, function(err){
				 $scope.alerts.push({type: 'danger', msg: 'Operation failed'});
				 console.log('Operation failed');
			 });
   	 }
	};
	
	$scope.openImageModal = function (size, propertyName, index) {
		
		 console.log('propertyName = ' + propertyName + " , index = " + index);
		 if($scope.repositoryItem[propertyName] == null) {
			 $scope.repositoryItem[propertyName] = [];
		 }
		 var image = null;
		 if(index == null) {
			 image = new Object();
		 } else {
			 image = $scope.repositoryItem[propertyName][index];
			 console.log('image.thumbnail = ' + image.thumbnail);
		 }
		 console.log('$scope.repositoryItem[propertyName] = ' + $scope.repositoryItem[propertyName]); 
	    var modalInstance = $modal.open({
	      templateUrl: 'partials/crud-operations/image-add-edit-modal.html',
	      controller: 'AddEditImageModalInstanceCtrl',
	      size: size,
	      resolve: {
	        image: function () {
	          return image;
	        }
	      }
	    });

	    modalInstance.result.then(function (addedImage) {
	      console.log('added image.thumbnail: ' + addedImage.thumbnail);
	      if(index == null) {
	    	  $scope.repositoryItem[propertyName].push(addedImage);
	      } else {
	    	  $scope.repositoryItem[propertyName][index] = addedImage;
	      }
	    }, function () {
	      console.log('Modal dismissed at: ' + new Date());
	    });
	  };
 	
   }]);


crudControllers.controller('AddEditImageModalInstanceCtrl', ['$scope', '$modalInstance', 'image', 'image_server_url', function ($scope, $modalInstance, image, image_server_url) {
	
	$scope.image_server_url = image_server_url;

	  $scope.image = image;

	  $scope.ok = function () {
	    $modalInstance.close($scope.image);
	  };

	  $scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	  };
	}]);
