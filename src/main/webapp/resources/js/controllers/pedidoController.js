var myApp = angular.module('myApp');

myApp.controller('PedidoCtrl', [
    '$scope',
    'PedidoService',
    '$location',
    '$anchorScroll',
    '$stateParams',
    '$state',
    'toastr',
    function($scope, PedidoService, $location, $anchorScroll, $stateParams, $state, toastr){
        $scope.idNegocio = $stateParams.idNegocio;
        $scope.listaDetallesPedido = [];
        $scope.platoModal = {};
        $scope.cantidades = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20];
        $scope.pedido = {};
        $scope.pedido.listaDetalles = [];
        
        //cuestiones de testeo
        $scope.usuario = {
            idUsuario : 1,
            username : "pepito"
        }
        

        $scope.cargarPagina = function(){
            $scope.obtenerNegocio($scope.idNegocio);
            $scope.obtenerCategoriasNegocio($scope.idNegocio);
            $scope.obtenerMenuActivo($scope.idNegocio);
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
        
        $scope.calcularTotalPedido = function(){
            var total = 0;
            $scope.listaDetallesPedido.forEach(function(detalle, index){
                total += detalle.subtotalDetalle;
            });
            
            return total;
        }
        
        $scope.guardarPedido = function(listaDetallesPedido){
            $scope.pedido.direccionPedido = "";
            $scope.pedido.pagaconPedido = 0;
            $scope.pedido.estadoPedido = "GUARDADO";
            $scope.pedido.idUsuario = $scope.usuario.idUsuario;
            $scope.pedido.idNegocio = $scope.idNegocio;
            $scope.pedido.listaDetalles = listaDetallesPedido;
            $scope.pedido.totalPedido = $scope.calcularTotalPedido();
            if(PedidoService.guardarPedido($scope.pedido)){
                toastr.success('Atencion!', "Tu pedido se guardó con éxito!");
            }else{
                toastr.error('Atencion!', "tu pedido no pudo guardarse!");
            }
        }

        
        $scope.enviarPedido = function(listaDetallesPedido){
            
            //etc
            //implementar JWT y en el caso que este OK --> enviarPedido
            //si está mal, enviar al estado LOGIN.
            var pedido = {};
            pedido.listaDetallesPedido = listaDetallesPedido;
            PedidoService.agregarPedidoEnvio(pedido);
            $state.go('enviarPedido');
        }

}]);
