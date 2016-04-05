'use strict';

/* Controllers */

var mlsControllers = angular.module('mlsControllers', []);

mlsControllers.controller('MainBodyController', ['$scope', '$rootScope', 'AuthService',
    function($scope, $rootScope, AuthService) {
		var previousPage = {};
		$scope.$watch(AuthService.isLoggedIn, function(isLoggedIn) {
			$scope.isLoggedIn = isLoggedIn;
			$scope.currentUser = AuthService.currentUser();
			console.log('$scope.currentUser = ' + $scope.currentUser)
		});
		$rootScope.$on('$stateChangeSuccess', function (ev, to, toParams, from, fromParams) {
			if(from.name != 'signup') {
				previousPage.state = from;
				previousPage.stateParams = fromParams;
				$scope.previousPage = previousPage;
			}
		});
		$scope.image_server_url = '/catalog-media/';
	}
  ]);

mlsControllers.controller('PhoneListCtrl', ['$scope', 'Phone',
  function($scope, Phone) {
    $scope.phones = Phone.query();
    $scope.orderProp = 'age';
  }]);

mlsControllers.controller('PhoneDetailCtrl', ['$scope', '$routeParams', 'Phone',
  function($scope, $routeParams, Phone) {
    $scope.phone = Phone.get({phoneId: $routeParams.phoneId}, function(phone) {
      $scope.mainImageUrl = phone.images[0];
    });

    $scope.setImage = function(imageUrl) {
      $scope.mainImageUrl = imageUrl;
    }
  }]);

mlsControllers.controller('HomepageCarouselController', ['$scope', 'Slide',
	function($scope, Slide) {
	  $scope.slides = Slide.query();
	}
]);

mlsControllers.controller('HomePageFeaturedProducts', ['$scope', 'HomePageFeaturedProducts',
 	function($scope, HomePageFeaturedProducts) {
 	  $scope.featuredProducts = HomePageFeaturedProducts.query();
 	}
 ]);

mlsControllers.controller('HomePageFeaturedCategory', ['$scope', 'HomePageFeaturedCategory',
	function($scope, HomePageFeaturedCategory) {
	  $scope.featuredCategory = HomePageFeaturedCategory.query();
	}
]);

mlsControllers.controller('OfferCardsController', ['$scope', 'OfferCardsService',
   	function($scope, OfferCardsService) {
   	  $scope.offerCards = OfferCardsService.query();
   	}
   ]);

mlsControllers.controller('CatalogMenuController', ['$scope', '$state', '$stateParams', 'CatalogBrowseService', 
     function($scope, $state, $stateParams, CatalogBrowseService) {
	 CatalogBrowseService.query(function(CatalogBrowseServiceResponse) {
		  $scope.catalog = CatalogBrowseServiceResponse;
		  console.log('$scope.catalog = ' + $scope.catalog)
	  });	
}]);

mlsControllers.controller('ProductDetailController', ['$scope', '$state', '$stateParams', 'ProductDetailService',  
   function($scope, $state, $stateParams, ProductDetailService) {
	$scope.productDocumentId = $stateParams.productDocumentId;
    console.log('productDocumentId = ' + $scope.productDocumentId);
	ProductDetailService.query({productDocumentId : $stateParams.productDocumentId}, function(ProductDetailServiceResponse) {
		$scope.productDocument = ProductDetailServiceResponse;
	});
}]);

mlsControllers.controller('ProductDetailImagesController', ['$scope', '$state', '$stateParams', 
  function($scope, $state, $stateParams) {
    console.log('Ínside ProductDetailImagesController');
//    $scope.currentImage = $scope.$parent.productDocument.productImages[0];
    $scope.setCurrentImage = function(image) {
    	$scope.currentImage = image;
    }
}]);

mlsControllers.controller('ProductDetailAddToCartController', ['$scope', '$state', '$stateParams', 'ShoppingCartService', 
    function($scope, $state, $stateParams, ShoppingCartService) {
      console.log('Inside ProductDetailAddToCartController');
      
      $scope.addToCart = function() {
    	  console.log('Inside addToCart();');
    	  $scope.addToCartDTO.skuId = $stateParams.productDocumentId;
    	  ShoppingCartService.addToCart.post($scope.addToCartDTO);
      };
  }]);

mlsControllers.controller('ProductDetailDescriptionController', ['$scope', '$state', '$stateParams', 
 function($scope, $state, $stateParams) {
  console.log('Inside ProductDetailDescriptionController');
  }]);



