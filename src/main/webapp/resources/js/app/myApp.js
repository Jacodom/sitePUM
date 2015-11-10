var myApp = angular.module('myApp', ['ngRoute','uiGmapgoogle-maps']);

myApp.config(['$routeProvider', 'uiGmapGoogleMapApiProvider', function ($routeProvider, uiGmapGoogleMapApiProvider) {
    $routeProvider
        .when('/',
        {
            templateUrl: "/views/elegirNegocio.html",
            controller: "NegocioCtrl"
        })
        .when('/gestionarPedido/:idNegocio', {
            templateUrl: "/views/gestionarPedido.html",
            controller: "PedidoCtrl"
        })
        .when('/map',{
            templateUrl: "views/maps.html",
            controller: "MapsCtrl"
        })
        .otherwise({
            redirectTo: "/"
        });
    
    uiGmapGoogleMapApiProvider.configure({
        v: '3.20', //defaults to latest 3.X anyhow            libraries: 'weather,geometry,visualization'
    });
}]);

myApp.run(['$anchorScroll', function($anchorScroll) {
      $anchorScroll.yOffset = 75;   // always scroll by 50 extra pixels
    }]);
