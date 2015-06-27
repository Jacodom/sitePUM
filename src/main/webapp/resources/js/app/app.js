var app = angular.module('app', ['ngRoute','negocioController', 'pedidoController']);

app.config(function ($routeProvider) {
    $routeProvider
        .when('/',
        {
            templateUrl: "elegirNegocio.html",
            controlller: "negocioController"
        })
        .when('/gestionarPedido', {
            templateUrl: "gestionarPedido.html",
            controller: "pedidoControler"
        })
        .otherwise({
            redirectTo: "/"
        })
});
