var myApp = angular.module('myApp');

myApp.controller('PedidoCtrl', ['$scope', 'PedidoService', '$location', '$anchorScroll', '$routeParams',  function($scope, PedidoService, $location, $anchorScroll, $routeParams){
        $scope.idNegocio = $routeParams.idNegocio;
        

        
        cargarPagina();
    
    
        function cargarPagina(){
            obtenerNegocio($scope.idNegocio);
            obtenerCategoriasNegocio($scope.idNegocio);
            obtenerMenuActivo($scope.idNegocio);
           // obtenerPlatosMenu($scope.menuActivo);
        }
    
        function obtenerNegocio(idNegocio){
            PedidoService.obtenerNegocio(idNegocio).then(function(negocio){
               $scope.negocio = negocio; 
            });
        }

        function obtenerCategoriasNegocio(idNegocio){
            PedidoService.obtenerCategoriasNegocio(idNegocio).then(function(categorias){
                $scope.listaCategorias = categorias;
            });
        }    
        
        function obtenerMenuActivo(idNegocio){
            PedidoService.obtenerMenuActivo(idNegocio).then(function(menu){
                $scope.menuActivo = menu;
                obtenerPlatosMenu(menu);
            });
        }

        function obtenerPlatosMenu(menuActivo){
            PedidoService.obtenerPlatosMenu(menuActivo).then(function(platos){
                $scope.platosMenu = platos;
            });
        }   
    
    $scope.scrollTo = function(id) {
        var old = $location.hash();
        $location.hash(id);
        $anchorScroll();
        //reset to old to keep any additional routing logic from kicking in
        $location.hash(old);
    }
    
    
    //modal
    $scope.listaDetallesPedido = [];
    $scope.agregarPlatoPedido = function(plato){
        var detalle = {};
        console.log(plato);
        detalle.nombrePlato = plato.nombrePlato;
        detalle.cantidadDetalle = 2;
        //detalle.subtotalDetalle = detalle.cantidadDetalle * plato.precioPlato;
        detalle.aclaracionDetalle = "nada de nada";
        
        
        console.log(detalle);
        $scope.listaDetallesPedido.push(detalle);
        
        console.log($scope.listaDetallesPedido);
    }
    
}]);

