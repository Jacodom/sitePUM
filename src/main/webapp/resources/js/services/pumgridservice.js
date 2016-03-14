/**
 * Created by Pablo on 12/03/2016.
 */
'use strict';

/**
 * @ngdoc service
 * @name dashboardApp.pumGridService
 * @description
 * # pumGridService
 * Factory in the dashboardApp.asdaas
 */
angular.module('myApp')
    .factory('pumGridService', function ($http,$q) {
        // Service logic
        console.log("entro factory");
        return{
            obtenerPedidosPum : function(){
                return $http.get('/sede/obtenerPedidos')
                    .then(function(response) {
                        return response.data
                    }, function(response) {
                        return $q.reject(response.data);
                    });
            },
            obtenerCadetes : function(){
                return $http.get('/sede/obtenerCadetes') // -http://www.json-generator.com/api/json/get/bTrCcWhvAO?indent=2
                    .then(function(response) {
                        console.log(response);
                        return response.data
                    }, function(response) {
                        return $q.reject(response.data);
                    });
            },
            guardarRonda : function(ronda){
                return $http({
                    method: "POST",
                    //url: ; //urlBackend
                    data: ronda
                }).then(function(response){
                    return response.data
                }, function(response){
                    return $q.reject(response.data)
                });
            }
        }
    });