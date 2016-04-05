'use strict';

/* Directives */

var mlsDirectives = angular.module('mlsDirectives', []);

mlsDirectives.directive('topNav', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/navigation/top-nav-1.html'
	};
});

mlsDirectives.directive('leftSlideInMenu', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/menu/left-slide-in-menu-1.html',
		controller: 'SlideInMenuController'
	};
});

mlsDirectives.directive('leftSideMenu', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/menu/left-side-menu-3.html',
		controller: 'LeftSideMenuController'
	};
});

mlsDirectives.directive('mainFooter', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/footers/footer-1.html',
		controller: 'MainFooterController'
	};
});

mlsDirectives.directive('homePageCarousel', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/carousel/carousel-1.html',
		controller: 'HomepageCarouselController'
	};
});

mlsDirectives.directive('homePageBanner', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/banners/banner-1.html'
	};
});

mlsDirectives.directive('homePageProductList', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/featured-products/featured-products-1.html',
		controller: 'HomePageFeaturedProducts'
	};
});

mlsDirectives.directive('featuredCategory', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/featured-category/featured-category-1.html',
		controller: 'HomePageFeaturedCategory'
	};
});

mlsDirectives.directive('crudOperation', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/crud-operations/crud-operations-1.html',
		controller: 'CrudOperationsController'
	};
});

mlsDirectives.directive('listRepositoryItems', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/crud-operations/list-repository-items-1.html',
		controller: 'ListRepositoryItemsController'
	};
});

mlsDirectives.directive('uploadRepositoryItems', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/crud-operations/upload-repository-items-1.html',
		controller: 'UploadRepositoryItemsController'
	};
});

mlsDirectives.directive('listLinkItems', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/crud-operations/list-link-items-1.html'
	};
});

mlsDirectives.directive('linkAddExistingRepositoryItems', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/crud-operations/link-add-existing-repository-items-1.html'
	};
});

mlsDirectives.directive('searchRepositoryItems', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/crud-operations/search-repository-items-1.html',
		controller: 'SearchRepositoryItemsController'
	};
});


mlsDirectives.directive('custBindModel',function($compile){
    return{
        compile:function(tEl){
            tEl[0].removeAttribute('cust-bind-model');
            return function(scope, iEl, iAtr){
                iEl[0].setAttribute('ng-model',scope.$eval(iAtr.custBindModel));
                $compile(iEl[0])(scope);
                console.info('new compiled element:',tEl[0]);
            }
        }
    }
})

mlsDirectives.directive('custBindValue',function($compile){
    return{
        compile:function(tEl){
            tEl[0].removeAttribute('cust-bind-value');
            return function(scope, iEl, iAtr){
            	console.log(iEl[0]);
            	iEl[0] = scope.$eval(iAtr.custBindValue);
                $compile(iEl[0])(scope);
                console.info('new compiled element:',tEl[0]);
            }
        }
    }
})


