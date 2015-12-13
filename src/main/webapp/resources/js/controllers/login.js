'use strict';

/**
 * @ngdoc function
 * @name
 * @description
 * # LoginCtrl
 */
angular.module('myApp')
  .controller('LoginCtrl',[
    '$scope',
    '$state',
    'store',
    '$http',
    function ($scope, $state, store, $http) {
    
        $scope.user = {};
        $scope.userAuth = {};
        
        $scope.login = function (){
            $http({
                url: "/auth/sessions/create",
                method: 'POST',
                data: $scope.user
            }).then(function(response){
                if(response.data != null || response.data!= undefined){
                    store.set('jwt', response.data.token);
                    $scope.userAuth = response.data;
                    $scope.userAuth.passwordUsuario = "";
                    $scope.userAuth.token="";
                    store.set("authUser", $scope.userAuth);
                    $state.go('enviarPedido');
                }
                else{
                    console.log("no che");
                }
            });
        }
        
  }]);
