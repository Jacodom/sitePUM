var myApp = angular.module('myApp', [
    'ui.router',
    'uiGmapgoogle-maps',
    'perfect_scrollbar',
    'ui.bootstrap',
    'ngAnimate',
    'toastr',
    'chart.js',
    'angular-storage',
    'angular-jwt'
]);

myApp.config(function ($stateProvider, $urlRouterProvider, uiGmapGoogleMapApiProvider, toastrConfig, jwtInterceptorProvider, $httpProvider) {

      angular.extend(toastrConfig, {
        autoDismiss: false,
        containerId: 'toast-container',
        maxOpened: 0,
        newestOnTop: true,
        positionClass: 'toast-bottom-right',
        preventDuplicates: false,
        preventOpenDuplicates: false,
        target: 'body'
      });

    jwtInterceptorProvider.tokenGetter = function(store){
        return store.get('jwt');
    }
    
    $httpProvider.interceptors.push('jwtInterceptor');
    
    
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
        .state('Login',{
            url: "/login",
            templateUrl: "views/login.html",
            controller: "LoginCtrl"
        })
        .state('chart',{
            url: "/chart",
            templateUrl: "views/chart.html",
            controller: "ChartCtrl"
        })
        .state('enviarPedido',{
            url: "/enviarPedido",
            templateUrl: "views/enviarPedido.html",
            controller: "EnviarPedidoCtrl"
        });


    uiGmapGoogleMapApiProvider.configure({
        v: '3.20', //defaults to latest 3.X anyhow            libraries: 'weather,geometry,visualization'
    });




});

myApp.run(['$anchorScroll', function($anchorScroll) {
      $anchorScroll.yOffset = 75;   // always scroll by 50 extra pixels
    }]);
