'use strict';

/* Controllers */

var mlsUserProfileControllers = angular.module('mlsUserProfileControllers', []);

mlsControllers.controller('UserProfileController', ['$scope', '$state', '$location', '$stateParams', 'AuthService',
   function($scope, $state, $location, $stateParams, AuthService) {
		console.log('Inside UserProfileController');
		$scope.signupSuccess = false;
		$scope.newUser = {};
		$scope.login = function() {
			console.log($scope.user);
			console.log('Inside UserProfileController.login() ' + $scope.user);
			AuthService.login($scope.user);
			console.log($scope.previousPage.state.name);
			if($scope.previousPage.state.name == null || $scope.previousPage.state.name == '') {
				$state.go('home');
			} else {
				$state.go($scope.previousPage.state.name, $scope.previousPage.stateParams);
			}
		};
		$scope.register = function() {
			console.log($scope.newUser);
			console.log('Inside SignupController.register() ' + $scope.newUser);
			AuthService.signup($scope.newUser);
			$scope.signupSuccess = true;
			console.log('sign up success');
		};
		$scope.logout = function() {
			console.log($scope.user);
			console.log('Inside UserProfileController.logout() ' + $scope.user);
			AuthService.logout($scope.user);
			$state.go($scope.previousPage.state.name, $scope.previousPage.stateParams);
		};
  }]);