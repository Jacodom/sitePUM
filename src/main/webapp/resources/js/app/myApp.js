var myApp = angular.module('myApp', ['ngRoute']);

myApp.config(['$routeProvider', function ($routeProvider) {
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
        })
}]);

myApp.run(['$anchorScroll', function($anchorScroll) {
      $anchorScroll.yOffset = 75;   // always scroll by 50 extra pixels
    }]);


