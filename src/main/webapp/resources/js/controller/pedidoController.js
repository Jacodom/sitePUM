angular.module('sitePumApp',[])
    .controller('pedidoController', ['$scope', '$http', function($scope, $http){

       var obtenerCategoriasNegocio = function ($scope, $http) {
            $http({
                method: "GET",
                url: "/gestionarPedido/obtenerCategoriasNegocio",
                params: {
                    idNegocio : 1
                }
            }).success(function(data){
                console.log(data);
                $scope.listaCategorias = data;
            });
        }

        var obtenerMenuActivo = function ($scope, $http) {

            $http({
                method: "GET",
                url: "/gestionarPedido/obtenerMenuActivo",
                params: {
                    idNegocio: 1
                }
            }).success(function(data){
                console.log(data)
                $scope.menuActivo = data;
            });
        }

        var obtenerPlatosMenu = function ($scope, $http) {
            $http({
                method: "GET",
                url: "/gestionarPedido/obtenerPlatosMenu",
                params: {
                    idMenu: 1
                }
            }).success(function(data){
                console.log(data);
                $scope.platosMenu = data;
            });
        }

        obtenerCategoriasNegocio();
        obtenerMenuActivo();
        obtenerPlatosMenu();
}]);