'use strict';

/* Controllers */

var fileUploadControllers = angular.module('fileUploadControllers', ['angularFileUpload']);


fileUploadControllers.controller('UploadRepositoryItemsController', ['$scope', '$upload', function ($scope, $upload) {
    $scope.$watch('files', function () {
        $scope.upload($scope.files);
    });

    $scope.upload = function (files) {
        if (files && files.length) {
            for (var i = 0; i < files.length; i++) {
                var file = files[i];
                $upload.upload({
                    url: '/services/' + $scope.repositoryName + '/upload',
                    fields: {},
                    file: file
                }).progress(function (evt) {
                    var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                    $scope.uploadStatus = '' + progressPercentage;
                    if(progressPercentage == 100) {
                    	$scope.uploadStatus = 'success'
                    }
                    console.log('progress: ' + progressPercentage + '% ' + evt.config.file.name);
                }).success(function (data, status, headers, config) {
                    console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
                    $scope.uploadStatus = 'success';
                });
            }
        }
    };
}]);