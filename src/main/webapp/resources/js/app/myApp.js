var myApp = angular.module('myApp', ['ngRoute','uiGmapgoogle-maps']);

myApp.config(['$routeProvider', function ($routeProvider,uiGmapgoogle-maps) {
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
        .otherwise({
            redirectTo: "/"
        });
        uiGmapGoogleMapApiProvider.configure({
            v: '3.20', //defaults to latest 3.X anyhow
            libraries: 'weather,geometry,visualization'
        });

}]);

myApp.run(['$anchorScroll', function($anchorScroll) {
      $anchorScroll.yOffset = 75;   // always scroll by 50 extra pixels
    }]);
