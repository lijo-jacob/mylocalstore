'use strict';

/* App Module */

var mlsApp = angular.module('mlsApp', [
  'ngRoute',
  'ngResource',
  'ui.bootstrap',
  'ui.grid',
  'ui.router',
  'ui.sortable', 
  'ncy-angular-breadcrumb',
  'spring-data-rest',
  'ngTagsInput', 
  'brantwills.paging',
  'crudControllers', 
  'searchRepsoitoryItemsControllers',
  'listRepositoryItemsControllers', 
  'itemLinkControllers', 
  'fileUploadControllers',
  'mlsUtils',
  'mlsControllers',
  'mlsFilters',
  'mlsServices',
  'mlsDirectives'
//  'mlsMenu'
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
        
        .state('indexing', {
            url: '/indexing',
            templateUrl: 'partials/pages/indexing/indexing-1.html',
            ncyBreadcrumb: {
            	label: 'Indexing Admin'
            }, 
            controller: 'IndexingController'
        })
        
        .state('crud', {
            url: '/crud/:repositoryName',
            templateUrl: 'partials/pages/crud/crud-1.html',
            ncyBreadcrumb: {
            	label: '{{repositoryName | camelCaseToHuman}}'
            },
            controller: function ($stateParams, $scope) {
                $scope.repositoryName = $stateParams.repositoryName;
            }
        })
        
        .state('crud.search', {
            url: '/search',
            templateUrl: 'partials/pages/crud/search-1.html',
            ncyBreadcrumb: {
            	label: 'Search'
            }
        })
        
        .state('crud.create', {
            url: '/create',
            ncyBreadcrumb: {
            	label: 'Create'
            }, 
            views: {
            	'repositoryItemView@crud' : {
            		templateUrl: 'partials/pages/crud/create-1.html'
            	}
            }
        })
        .state('crud.edit', {
            url: '/:repositoryItemId',
    		ncyBreadcrumb: {
            	label: '{{repositoryItemId}}'
            }, 
            views: {
            	'repositoryItemView@crud' : {
            		templateUrl: 'partials/pages/crud/update-1.html',
                    controller: function ($stateParams, $scope) {
                        $scope.repositoryItemId = $stateParams.repositoryItemId;
                        console.log('repositoryItemView@crud : repositoryItemId = ' + $scope.repositoryItemId);
                    }
            	}
            }
        })
        
        .state('crud.upload', {
            url: '/upload',
            ncyBreadcrumb: {
            	label: 'Upload'
            },
            views: {
            	'repositoryItemView@crud' : {
            		templateUrl: 'partials/pages/crud/upload-1.html'
            	}
            }
        })
        
        .state('crud.list', {
            url: '/list',
            ncyBreadcrumb: {
            	label: 'List'
            }, 
            views: {
            	'repositoryItemListView@crud' : {
            		templateUrl: 'partials/pages/crud/listing-1.html'
            	}
            }
        })
        
        
        
        .state('crud.edit.link', {
            url: '/:linkName',
            ncyBreadcrumb: {
            	label: '{{linkName | camelCaseToHuman}}',
            }, 
            views: {
            	'linkListView@crud.edit' : {
            		templateUrl: 'partials/pages/crud/link-listing-1.html',
                    controller: function ($stateParams, $scope) {
                    	$scope.repositoryName = $stateParams.repositoryName;
                    	$scope.repositoryItemId = $stateParams.repositoryItemId;
                        $scope.linkName = $stateParams.linkName;
                        console.log($scope.repositoryName + ', ' + $scope.repositoryItemId + ', ' + $scope.linkName);
                    }
            	}
            }
        })
        
        // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
        .state('about', {
            // we'll get to this in a bit       
        });
        
});



mlsApp.config(function (SpringDataRestInterceptorProvider) {
    SpringDataRestInterceptorProvider.apply();
});


mlsApp.config(function (SpringDataRestAdapterProvider) {

    // set the links key to _myLinks
    SpringDataRestAdapterProvider.config({
        'linksKey': '_links'
    });
});

