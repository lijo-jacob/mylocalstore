'use strict';

/* Filters */

var mlsFilters = angular.module('mlsFilters', []);

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

mlsFilters.filter('getImageType', function() {
	  return function(input, delimiter, imageType) {
		  if (input) {
			  console.log('Inside getImageType, input = ' + input + ', delimiter = ' + delimiter + ', imageType = '  + imageType)
		    var delimiter = delimiter || ', ';
		    var images = input.split(delimiter);
		    var imagePath = null;
		    images.forEach( function (image)
    		{
		    	console.log('image = ' + image);
		    	var currentImageType = image.split('=')[0];
		    	console.log('currentImageType = ' + currentImageType);
		    	console.log('currentImageType == imageType : ' + (currentImageType == imageType))
		    	if(currentImageType == imageType) {
		    		imagePath = image.split('=')[1];
		    		console.log('imagePath = ' + imagePath);
		    		return;
		    	}
    		});
		    return imagePath;
		  }
	  } 
	});