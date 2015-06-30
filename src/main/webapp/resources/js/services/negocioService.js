var myApp = angular.module('myApp');

myApp.service('NegocioService',['$http','$q',function($http,$q){
    return {
        
        obtenerCategorias : function(){
            var deferred = $q.defer();
            
            var response = $http({
                method: "GET",
                url:"/elegirNegocio/obtenerCategorias"           
            }).then(function(response){
               if(response.status == 200){
                   deferred.resolve(response.data);
               }else{
                   deferred.reject('Error');
               } 
            });
            return deferred.promise;
        },
        
        obtenerNegocios : function(){
            var deferred = $q.defer();
            $http({
                method: "GET",
                url: "/elegirNegocio/obtenerNegocios"
            }).then(function(response){
                if(response.status == 200){
                    deferred.resolve(response.data);
                }else{
                    deferred.reject('Error');
                }
            });
            return deferred.promise;
        }
        
       /* filtrarPorCategoria : function(){
            var deferred = $q.defer();
            $http({
                method: "GET",
                url: "/elegirNegocio/obtenerNegocios"
            }).then(function(response){
                if(response.status == 200){
                    deferred.resolve(response.data);
                }else{
                    deferred.reject('Error');
                }
            });
            return deferred.promise;
        }*/
    }
}]);