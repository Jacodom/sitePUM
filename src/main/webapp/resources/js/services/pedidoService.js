var app = angular.module('app');

app.service('pedidoService', ['$http', '$q', function($http, $q){
    
    return {
        
        this.obtenerCategoriasNegocio = function(){
        
            var deferred = $q.defer();

            $http({
                    method: "GET",
                    url: "/gestionarPedido/obtenerCategoriasNegocio",
                    params: {
                        idNegocio : 1
                    }
                }).success(function(response){
                console.log(response.data);
                deferred.resolve(response.data);
            });    
            return deferred.promise;
    }
                              
        this.obtenerMenuActivo = function(){
                              
            var deferred = $q.defer();

            $http({
                    method: "GET",
                    url: "/gestionarPedido/obtenerMenuActivo",
                    params: {
                        idNegocio: 1
                    }
                }).success(function(response){
                    console.log(response.data)
                    deferred.resolve(response.data);
                });

            return deferred.promise;
    }
                              
        this.obtenerPlatosMenu = function(){
                              
            var deferred = $q.defer();                      
        
            $http({
                method: "GET",
                url: "/gestionarPedido/obtenerPlatosMenu",
                params: {
                    idMenu: 1
                }
            }).success(function(response.data){
                console.log(response.data);
                deferred.resolve(response.data);
            });
        }                     
                              
    }    
}]);