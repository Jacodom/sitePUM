angular.module('sitePumApp',[])
    .controller('pedidoController', ['$scope', '$http', function($scope, $http){
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
}]);