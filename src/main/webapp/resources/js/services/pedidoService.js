var myApp = angular.module('myApp');


myApp.service('PedidoService', ['$http', '$q', function($http, $q){
    
    return {
        
        obtenerCategoriasNegocio : function(parametroIdNegocio){
        
            var deferred = $q.defer();

            var response = $http({
                    method: "GET",
                    url: "/gestionarPedido/obtenerCategoriasNegocio",
                    params: {
                        idNegocio : parametroIdNegocio
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
                              
        obtenerMenuActivo : function(parametroIdNegocio){
                              
            var deferred = $q.defer();

            $http({
                    method: "GET",
                    url: "/gestionarPedido/obtenerMenuActivo",
                    params: {
                        idNegocio: parametroIdNegocio
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
                              
        obtenerPlatosMenu : function(menuActivoParametro){
                              
            var deferred = $q.defer();                      
        
            $http({
                method: "GET",
                url: "/gestionarPedido/obtenerPlatosMenu",
                params: {
                    idMenu: menuActivoParametro.idMenu
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
        
        obtenerNegocio : function(parametroIdNegocio){
            
            var deferred = $q.defer();
            console.log(parametroIdNegocio + "  parametro Negocioooo")
            $http({
                method: "GET",
                url: "/gestionarPedido/obtenerNegocio",
                params:{
                    idNegocio: parametroIdNegocio
                }
            }).then(function (response) {
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