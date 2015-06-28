var myApp = angular.module('myApp');

myApp.controller('NegocioController',['$scope','NegocioService',function($scope, NegocioService){
    
    obtenerCategorias();
    obtenerNegocios();
    
    function obtenerCategorias(){
        NegocioService.obtenerCategorias().then(function(categorias){
            $scope.listaCategorias = categorias;
        });
    }
    function obtenerNegocios(){
        NegocioService.obtenerNegocios().then(function(){
            $scope.listaNegocio = negocios;
        });
    }
}]);