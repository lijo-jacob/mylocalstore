'use strict';

/* Utils */

var mlsUtils = angular.module('mlsUtils', []);


mlsUtils.factory('mlsUtils', function(){
  return{
    compareStr: function(stra, strb){
      stra = ("" + stra).toLowerCase();
      strb = ("" + strb).toLowerCase();
      return stra.indexOf(strb) !== -1;
    }
  };
});