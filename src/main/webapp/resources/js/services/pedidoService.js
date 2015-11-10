var myApp = angular.module('myApp');


myApp.factory('PedidoService', ['$http', '$q', function($http){
    
    return {
        
        obtenerCategoriasNegocio : function(parametroIdNegocio){
        
            return $http({
                    method: "GET",
                    url: "/gestionarPedido/obtenerCategoriasNegocio",
                    params: {
                        idNegocio : parametroIdNegocio
                    }
                }).then(function(response){ //response
                    if(response.status == 200){
                        return response.data;
                    }else{
                        return("error");
                    }
            });
        
        },
                              
        obtenerMenuActivo : function(parametroIdNegocio){
                              

            return $http({
                    method: "GET",
                    url: "/gestionarPedido/obtenerMenuActivo",
                    params: {
                        idNegocio: parametroIdNegocio
                    }
                }).then(function(response){
                    if(response.status == 200){
                        return response.data;
                    }else{
                        return("error");
                    }
                });
        },
                              
        obtenerPlatosMenu : function(menuActivoParametro){
                                                
        
            return $http({
                method: "GET",
                url: "/gestionarPedido/obtenerPlatosMenu",
                params: {
                    idMenu: menuActivoParametro.idMenu
                }
            }).then(function(response){
                if(response.status == 200){
                        response.data;
                }else{
                        return("error");
                }
            });

        },
        
        obtenerNegocio : function(parametroIdNegocio){
            

            return $http({
                method: "GET",
                url: "/gestionarPedido/obtenerNegocio",
                params:{
                    idNegocio: parametroIdNegocio
                }
            }).then(function (response) {
                if(response.status == 200){
                    return response.data;
                }else{
                    return response.error;
                }
            });
            
        }
    }    
}]);