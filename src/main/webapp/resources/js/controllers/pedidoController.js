var myApp = angular.module('myApp');

myApp.controller('PedidoCtrl', [
    '$scope',
    'PedidoService',
    '$location',
    '$anchorScroll',
    '$routeParams',
    function($scope, PedidoService, $location, $anchorScroll, $routeParams){
        $scope.idNegocio = $routeParams.idNegocio;
        $scope.listaDetallesPedido = [];
        $scope.platoModal = {};
        $scope.cantidades = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20];

        $scope.cargarPagina = function(){
            $scope.obtenerNegocio($scope.idNegocio);
            $scope.obtenerCategoriasNegocio($scope.idNegocio);
            $scope.obtenerMenuActivo($scope.idNegocio);
           // obtenerPlatosMenu($scope.menuActivo);
        }

        $scope.obtenerNegocio = function(idNegocio){
            PedidoService.obtenerNegocio(idNegocio).then(function(negocio){
               $scope.negocio = negocio;
            });
        }

        $scope.obtenerCategoriasNegocio = function(idNegocio){
            PedidoService.obtenerCategoriasNegocio(idNegocio).then(function(categorias){
                $scope.listaCategorias = categorias;
            });
        }

        $scope.obtenerMenuActivo = function(idNegocio){
            PedidoService.obtenerMenuActivo(idNegocio).then(function(menu){
                $scope.menuActivo = menu;
                $scope.obtenerPlatosMenu(menu);
            });
        }

        $scope.obtenerPlatosMenu = function(menuActivo){
            PedidoService.obtenerPlatosMenu(menuActivo).then(function(platos){
                $scope.platosMenu = platos;
            });
        }

        $scope.cargarPagina();

        $scope.scrollTo = function(id) {
            var old = $location.hash();
            $location.hash(id);
            $anchorScroll();
            //reset to old to keep any additional routing logic from kicking in
            $location.hash(old);
        }


        //modal
        $scope.abrirModal = function(plato){
            $scope.platoModal = {};
            $scope.platoModal = plato;
        }

        $scope.calcularSubtotal = function(pedidoModal){
            if(pedidoModal!==undefined){
                var r = eval(pedidoModal.cantidadDetalle * $scope.platoModal.precioPlato);
                if(!isNaN(r)){
                    return r;
                }else{
                    return 0;
                }
            }else{
                return 0;
            }
        }

        $scope.agregarPlatoPedido = function(pedidoModal){
            if(!pedidoModal){
                return;
            }
            $scope.e = false;
            $scope.listaDetallesPedido.forEach(function (detalle, index) {
                if(detalle.idPlato == $scope.platoModal.idPlato){

                    detalle.cantidadDetalle += parseInt(pedidoModal.cantidadDetalle);
                    detalle.subtotalDetalle = parseFloat($scope.platoModal.precioPlato*detalle.cantidadDetalle);
                    $scope.e = true;
                }
            });
            if($scope.e==false){
                var d = {};
                d.idPlato = parseInt($scope.platoModal.idPlato);
                d.nombrePlato = $scope.platoModal.nombrePlato;
                d.precioPlato = parseFloat($scope.platoModal.precioPlato);
                d.cantidadDetalle = parseInt(pedidoModal.cantidadDetalle);
                d.subtotalDetalle = parseFloat(d.cantidadDetalle * d.precioPlato);
                d.aclaracionDetalle = pedidoModal.aclaracionDetalle;
                $scope.listaDetallesPedido.push(d); 
            }
            
            $scope.pedidoModal = {};
            $('#myModal').modal('hide');
        }


}]);
