'use strict';

/* App Module */

var mlsApp = angular.module('mlsApp', [
   'ngRoute',
  'ngResource',
  'ui.bootstrap',
  'ui.router',
  'ncy-angular-breadcrumb',
  'infinite-scroll',
  'mlsControllers',
  'mlsBrowseSearchControllers', 
  'mlsUserProfileControllers', 
  'mlsFilters',
  'mlsServices',
  'mlsDirectives'
]);


mlsApp.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/home');
    
    $stateProvider
        
        .state('home', {
            url: '/home',
            templateUrl: 'partials/pages/home/home-1.html',
            ncyBreadcrumb: {
            	label: 'Home page'
            }
        })
        
        .state('signup', {
            url: '/signup',
            templateUrl: 'partials/pages/signup/signup-2.html',
            ncyBreadcrumb: {
            	label: 'Signup'
            }, 
            controller: 'UserProfileController'
        })
        
        .state('login', {
            url: '/login',
            templateUrl: 'partials/pages/login/login-2.html',
            ncyBreadcrumb: {
            	label: 'Login'
            }, 
            controller: 'UserProfileController'
        })
        
       .state('category', {
            url: '/category/:categoryId',
            templateUrl: 'partials/pages/product-listing/product-listing-1.html',
    		ncyBreadcrumb: {
            	label: '{{categoryId}}'
            }, 
            controller: function ($stateParams, $scope) {
                $scope.categoryId = $stateParams.categoryId;
                console.log('categoryId = ' + $scope.categoryId);
            }
        })
        
        .state('search', {
            url: '/search/:searchTerm',
            templateUrl: 'partials/pages/product-listing/product-listing-1.html',
    		ncyBreadcrumb: {
            	label: '{{searchTerm}}'
            }, 
            controller: function ($stateParams, $scope) {
                $scope.searchTerm = $stateParams.searchTerm;
                console.log('searchTerm = ' + $scope.searchTerm);
            }
        })
        
        .state('product', {
            url: '/product/:productDocumentId',
            templateUrl: 'partials/pages/product-detail/product-detail-1.html',
    		ncyBreadcrumb: {
            	label: '{{productDocumentId}}'
            }, 
            controller: 'ProductDetailController'
        })
        
        // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
        .state('about', {
            // we'll get to this in a bit       
        });
        
});


