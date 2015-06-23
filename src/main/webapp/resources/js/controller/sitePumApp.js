var app = angular.module('sitePumApp',[]);

app.config(function($routeProvider){
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