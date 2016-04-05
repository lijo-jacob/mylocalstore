var myApp= {};
var app = angular.module('myApp', ['ui.bootstrap']) 

app.controller('CarouselDemoCtrl',['$scope', function ($scope) {
    
    
    $scope.slides = [
        { image: 'http://lorempixel.com/400/200/', text: 'blah' },    
        { image: 'http://lorempixel.com/400/200/', text: 'blah' },
        { image: 'http://lorempixel.com/400/200/', text: 'blah' }, 
    ]
        
}]);