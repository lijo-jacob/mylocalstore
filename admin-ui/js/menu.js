var mlsMenu = angular.module("mlsMenu", []);

mlsMenu.controller("SlideInMenuController", function($scope, $rootScope) {
	$scope.leftVisible = false;
    $scope.rightVisible = false;
	
	$scope.close = function() {
        $scope.leftVisible = false;
		$scope.rightVisible = false;
	};
	
    $scope.showLeft = function(e) {
        $scope.leftVisible = true;
        e.stopPropagation();
    };
    
    $scope.showRight = function(e) {
        $scope.rightVisible = true;
        e.stopPropagation();
    }
    
    $rootScope.$on("documentClicked", _close);
	$rootScope.$on("escapePressed", _close);
    
    function _close() {
        $scope.$apply(function() {
            $scope.close(); 
        });
    }
});

mlsMenu.run(function($rootScope) {
	document.addEventListener("keyup", function(e) {
		if (e.keyCode === 27)
			$rootScope.$broadcast("escapePressed", e.target);
	});
    
    document.addEventListener("click", function(e) {
        $rootScope.$broadcast("documentClicked", e.target);
    });
});

mlsMenu.directive("menu", function() {
	return {
		restrict: "E",
		template: "<div ng-class='{ show: visible, left: alignment === \"left\", right: alignment === \"right\" }' ng-transclude></div>",
		transclude: true,
        scope: {
            visible: "=",
            alignment: "@"
        }
	};
});

mlsMenu.directive("menuItem", function() {
     return {
         restrict: "E",
         template: "<div><a ng-href='{{hash}}' ng-transclude></a></div>",
         transclude: true,
         scope: {
             hash: "@"
         }
     }
});