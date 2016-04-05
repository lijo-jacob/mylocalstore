'use strict';

/* Filters */

var mlsFilters = angular.module('mlsFilters', ['mlsUtils']);

mlsFilters.filter('checkmark', function() {
  return function(input) {
    return input ? '\u2713' : '\u2718';
  };
});


mlsFilters.filter('split', function() {
  return function(input, delimiter) {
	  if (input) {
	    var delimiter = delimiter || ',';
	    return input.split(delimiter);
	  }
  } 
});

mlsFilters.filter('last', function() {
	  return function(input) {
		  if(input) {
			  return input[input.length - 1];
		  }
	  } 
	});

mlsFilters.filter('titleCase', function() {
    return function(input) {
        input = input || '';
        return input.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
      };
    })
    
mlsFilters.filter('camelCaseToHuman', function() {
  return function(input) {
    input = input || '';
    return input.charAt(0).toUpperCase() + input.substr(1).replace(/[A-Z]/g, ' $&');
  }
});

mlsFilters.filter('orderObjectBy', function(){
	 return function(input, attribute) {
	    if (!angular.isObject(input)) return input;

	    var array = [];
	    for(var objectKey in input) {
	        array.push(input[objectKey]);
	    }

	    array.sort(function(a, b){
	        a = parseInt(a[attribute]);
	        b = parseInt(b[attribute]);
	        return a - b;
	    });
	    return array;
	 }
	});

mlsFilters.filter('repositoryFilter', function(mlsUtils, $filter){
  return function(input, query){
    if(!query) return input;
    var result = {};

    angular.forEach(input, function(repository, repositoryName){
      if(mlsUtils.compareStr($filter('camelCaseToHuman')(repositoryName), query))
        result[repositoryName] = repository;          
    });
    return result;
  };
});