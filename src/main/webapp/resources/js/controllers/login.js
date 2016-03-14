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
        $scope.fromPage = {};
        
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
                    $scope.fromPage = store.get('fromPage');
                    if($scope.fromPage.name==='gestionarPedido'){
                        $scope.fromPage.param = store.get('fromPageParam');
                        console.log($scope.fromPage.param.idNegocio);
                        $state.go($scope.fromPage.name, {idNegocio: $scope.fromPage.param.idNegocio});
                    }
                    if($scope.userAuth.usernameUsuario==="sede"){
                        $state.go("grid");
                    }else{
                        $state.go($scope.fromPage.name);
                    }
                    
                }
                else{
                    console.log("no che");
                }
            });
        }
        
  }]);
