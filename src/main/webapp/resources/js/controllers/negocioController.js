var myApp = angular.module('myApp');

myApp.controller('NegocioController',['$scope','NegocioService',function($scope, NegocioService){
    
    obtenerCategorias();
    obtenerNegocios();
    filtroPorCategoria();
    
    function obtenerCategorias(){
        NegocioService.obtenerCategorias().then(function(categorias){
            $scope.listaCategorias = categorias;
            console.log(categorias);
        });
    }
    
    function obtenerNegocios(){
        NegocioService.obtenerNegocios().then(function(negocios){
            $scope.listaNegocio = negocios;
        });
    }
}]);