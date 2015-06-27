var app = angular.module('app');

app.controller('pedidoController', ['$scope', 'pedidoService', function($scope, pedidoService){
    
    var cargarPagina = function(){
        var obtenerCategoriasNegocio = function(){
            $scope.listaCategorias = pedidoService.obtenerCategoriasNegocio();
        }
        
        var obtenerMenuActivo = function(){
            $scope.menuActivo = pedidoService.obtenerMenuActivo();
        }
        
        var obtenerPlatosMenu = function(){
            $scope.platosMenu = pedidoService.obtenerPlatosMenu();
        }
    }

}]);