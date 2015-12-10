'use strict';

/**
 * @ngdoc function
 * @name
 * @description
 * # LoginCtrl
 */
angular.module('myApp')
  .controller('LoginCtrl',['$scope',function ($scope) {
    var u="admin";
    var p = "admin";
    $scope.user = {};

    $scope.login = function(user){
        if((user.username == u) && (user.password == p)){
          console.log("Entra log");
        }
        else {
          console.log("Error log");
        }
      };
  }]);
