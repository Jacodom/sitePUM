var myApp = angular.module('myApp');

myApp.controller('PedidoCtrl', ['$scope', 'PedidoService',  function($scope, PedidoService){

    obtenerCategoriasNegocio();
    obtenerMenuActivo();
    obtenerPlatosMenu();

        function obtenerCategoriasNegocio(){
            PedidoService.obtenerCategoriasNegocio().then(function(categorias){
                $scope.listaCategorias = categorias;
            });
        }    
        
        function obtenerMenuActivo(){
            PedidoService.obtenerMenuActivo().then(function(menu){
                $scope.menuActivo = menu;
            });
        }

        function obtenerPlatosMenu(){
            PedidoService.obtenerPlatosMenu().then(function(platos){
                $scope.platosMenu = platos;
            });
        }
}]);