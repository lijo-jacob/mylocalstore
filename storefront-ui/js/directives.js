'use strict';

/* Directives */

var mlsDirectives = angular.module('mlsDirectives', []);

mlsDirectives.directive('topNav', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/navigation/top-nav-1.html'
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

mlsDirectives.directive('offerCards', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/offer-cards/offer-cards-1.html',
		controller: 'OfferCardsController'
	};
});


mlsDirectives.directive('catalogMenu', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/menu/catalog-menu-3.html',
		controller: 'CatalogMenuController'
	};
});

mlsDirectives.directive('storefrontAppMainMenu', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/menu/storefront-app-main-menu-1.html'
	};
});

mlsDirectives.directive('browseSearchProductListing', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/pages/product-listing/browse-search-product-listing-1.html',
		controller: 'BrowseSearchController'
	};
});

mlsDirectives.directive('facetNavigationMenu', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/menu/facet-navigation-menu-1.html'
	};
});

mlsDirectives.directive('productDetailImages', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/pages/product-detail/product-detail-images-1.html',
		controller: 'ProductDetailImagesController'
	};
});

mlsDirectives.directive('productDetailAddToCart', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/pages/product-detail/product-detail-add-to-cart-1.html',
		controller: 'ProductDetailAddToCartController'
	};
});

mlsDirectives.directive('productDetailDescription', function() {
	return {
		restrict: 'E',
		templateUrl: 'partials/pages/product-detail/product-detail-description-1.html',
		controller: 'ProductDetailDescriptionController'
	};
});

