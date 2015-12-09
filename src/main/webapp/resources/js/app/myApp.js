var myApp = angular.module('myApp', [
    'ui.router',
    'uiGmapgoogle-maps',
    'perfect_scrollbar',
    'ui.bootstrap',
    'ngAnimate',
    'toastr'
]);

myApp.config(['$stateProvider', '$urlRouterProvider', 'uiGmapGoogleMapApiProvider', function ($stateProvider, $urlRouterProvider, uiGmapGoogleMapApiProvider) {


    $urlRouterProvider.otherwise("/");

    $stateProvider
        .state('elegirNegocio',
        {
            url: '/',
            templateUrl: "/views/elegirNegocio.html",
            controller: "NegocioCtrl"
        })
        .state('gestionarPedido', {
            url: "/gestionarPedido/:idNegocio",
            templateUrl: "/views/gestionarPedido.html",
            controller: "PedidoCtrl"
        })
        .state('map',{
            url: "/map",
            templateUrl: "views/maps.html",
            controller: "MapsCtrl"
        })
        .state('enviarPedido',{
            url: "/enviarPedido",
            templateUrl: "views/enviarPedido.html",
            controller: "EnviarPedidoCtrl"
        });

    uiGmapGoogleMapApiProvider.configure({
        v: '3.20', //defaults to latest 3.X anyhow            libraries: 'weather,geometry,visualization'
    });
    
    

}]);

myApp.run(['$anchorScroll', function($anchorScroll) {
      $anchorScroll.yOffset = 75;   // always scroll by 50 extra pixels
    }]);
