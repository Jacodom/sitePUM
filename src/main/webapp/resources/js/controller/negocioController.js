angular.module("sitePumApp",[])
    .controller("negocioController", function obtenerNegocio($scope,$http){
        var url = "/elegirNegocio/obtenerNegocios";
        $http.get(url).success( function(data){
            $scope.listaNegocio = data;  
        });
    })