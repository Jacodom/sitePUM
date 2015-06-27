var myApp = angular.module('myApp');


myApp.service('PedidoService', ['$http', '$q', function($http, $q){
    
    return {
        
        obtenerCategoriasNegocio : function(){
        
            var deferred = $q.defer();

            var response = $http({
                    method: "GET",
                    url: "/gestionarPedido/obtenerCategoriasNegocio",
                    params: {
                        idNegocio : 1
                    }
                }).then(function(response){ //response
                    if(response.status == 200){
                        deferred.resolve(response.data);
                    }else{
                        deferred.reject('Error');
                    }
            });
            return deferred.promise;
        },
                              
        obtenerMenuActivo : function(){
                              
            var deferred = $q.defer();

            $http({
                    method: "GET",
                    url: "/gestionarPedido/obtenerMenuActivo",
                    params: {
                        idNegocio: 1
                    }
                }).then(function(response){
                    if(response.status == 200){
                        deferred.resolve(response.data);
                    }else{
                        deferred.reject('Error');
                    }
                });

            return deferred.promise;
    },
                              
        obtenerPlatosMenu : function(){
                              
            var deferred = $q.defer();                      
        
            $http({
                method: "GET",
                url: "/gestionarPedido/obtenerPlatosMenu",
                params: {
                    idMenu: 1
                }
            }).then(function(response){
                if(response.status == 200){
                        deferred.resolve(response.data);
                }else{
                        deferred.reject('Error');
                }
            });
            
            return deferred.promise;
        }                                   
    }    
}]);