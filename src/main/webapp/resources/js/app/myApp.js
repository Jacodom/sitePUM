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
            controller: "PedidoCtrl",
            params:{
                idNegocio: ""
            }
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
            controller: "ChartCtrl",
            data: {
                requiresLogin: true
            }
        })
        .state('enviarPedido',{
            url: "/enviarPedido",
            templateUrl: "views/enviarPedido.html",
            controller: "EnviarPedidoCtrl",
            data: {
                requiresLogin: true
            }
        });


    uiGmapGoogleMapApiProvider.configure({
        v: '3.20', //defaults to latest 3.X anyhow            libraries: 'weather,geometry,visualization'
    });




});

myApp.run(function($anchorScroll, $state, store, $rootScope) {
    $anchorScroll.yOffset = 75;   // always scroll by 50 extra pixels
    
    $rootScope.$on('$stateChangeStart', function(e, to, toP, from, fromP){
       if(to.data && to.data.requiresLogin){
           if(!store.get('jwt')){
               e.preventDefault();
               $state.go('Login');
           }
        }
        
        if(to.name === 'enviarPedido' && !store.get('pedidoUser')){
            $state.go('elegirNegocio');
        }
        
        if(fromP != {}&& to.name!=='gestionarPedido'){
           store.set('fromPageParam', fromP);
        }
        
        if(from.name==='elegirNegocio'&&to.name==='gestionarPedido'){
            if(store.get('pedidoUser')){
                store.remove('pedidoUser');
            }
        }
        console.log(to.param);
        store.set('fromPage', from);

    });
    
        
});
