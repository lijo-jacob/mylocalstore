'use strict';

/* Controllers */

var mlsControllers = angular.module('mlsControllers', ['ui.grid']);

mlsControllers.constant('image_server_url', '/catalog-media/');

mlsControllers.controller('MainBodyController', ['$scope', 'image_server_url', 
  	function($scope, image_server_url) {
 		$scope.image_server_url = image_server_url;
 		
 		$scope.openMenu = function () {
 			if($scope.menuOpen) {
 				$scope.menuOpen = false;
 			} else {
 				$scope.menuOpen = true;
 			}
 			
 		};
 		
  	}

  ]);

mlsControllers.controller('MainFooterController', ['$scope',
	function($scope) {
	  
	}]);

mlsControllers.controller('PhoneListCtrl', ['$scope', 'Phone',
  function($scope, Phone) {
    $scope.phones = Phone.query();
    $scope.orderProp = 'age';
  }]);

mlsControllers.controller('PhoneDetailCtrl', ['$scope', '$stateParams', 'Phone',
  function($scope, $stateParams, Phone) {
    $scope.phone = Phone.get({phoneId: $stateParams.phoneId}, function(phone) {
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

mlsControllers.controller('LeftSideMenuController', ['$scope', 'RepositoryService', 'SpringDataRestAdapter',
     function($scope, RepositoryService, SpringDataRestAdapter) {
	RepositoryService.query({}, function(RepositoryServiceResponse) {
		  $scope.repositories = RepositoryServiceResponse._links;
		  console.log('$scope.repositories = ' + $scope.repositories)
	  });	
	  
	  $scope.isActive = function (viewLocation) { 
	        return viewLocation === $location.path();
	  };
	  
	  
}]);

mlsControllers.controller('DatepickerCtrl', function ($scope) {
	  $scope.today = function() {
	    $scope.dt = new Date();
	  };
	  $scope.today();

	  $scope.clear = function () {
	    $scope.dt = null;
	  };

	  // Disable weekend selection
	  $scope.disabled = function(date, mode) {
	    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	  };

	  $scope.toggleMin = function() {
	    $scope.minDate = $scope.minDate ? null : new Date();
	  };
	  $scope.toggleMin();

	  $scope.open = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();

	    $scope.opened = true;
	  };

	  $scope.dateOptions = {
	    formatYear: 'yy',
	    startingDay: 1
	  };

	  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
	  $scope.format = $scope.formats[0];
	});

mlsControllers.controller('MessagesCtrl', function ($scope) {
	  $scope.alerts = [];

	  $scope.closeAlert = function(index) {
	    $scope.alerts.splice(index, 1);
	  };
	  
	  $scope.clearAlerts = function(index) {
		  $scope.alerts = [];
	  };
	});

mlsControllers.controller('IndexingController', ['$scope', 'IndexingService', function ($scope, IndexingService) {
	  $scope.triggerBaselineIndex = function() {
		  IndexingService.triggerBaslineIndex();	
	  };
	}]);

