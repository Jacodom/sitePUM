var myApp = angular.module('myApp');

myApp.controller('PedidoCtrl', [
    '$scope',
    'PedidoService',
    '$location',
    '$anchorScroll',
    '$stateParams',
    '$state',
    'toastr',
    'store',
    function($scope, PedidoService, $location, $anchorScroll, $stateParams, $state, toastr, store){
        $scope.idNegocio = $stateParams.idNegocio;
        $scope.listaDetallesPedido = [];
        $scope.platoModal = {};
        $scope.cantidades = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20];
        $scope.cantDefault = 1;
        $scope.pedido = {};
        $scope.pedido.listaDetalles = [];
        $scope.tst = {};
        $scope.subMostrar = true;
        $scope.eliminarMostrar = false;
        $scope.empty = true;


        
        
        $scope.verificarPedido = function(){
            if($scope.listaDetallesPedido == null || $scope.listaDetallesPedido.length == 0 ){
                $scope.empty = true;
            }else{
                $scope.empty = false;
            }
        }
        
        //cuestiones de testeo
        $scope.usuario = store.get('user');

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
        
        $scope.hoverIn = function(detalle){
            detalle.subMostrar = false;  
            detalle.eliminarMostrar = true;
        }
        
        $scope.hoverOut = function(detalle){
            detalle.subMostrar = true;  
            detalle.eliminarMostrar = false;           
        }

        //modal
        $scope.abrirModal = function(plato, detalle){
            $scope.pedidoModal = {};
            if(detalle!=null){
                $scope.pedidoModal = detalle;
                $('.cantSelect').attr('disabled', true);
            }else{
                $('.cantSelect').attr('disabled', false);
            }
            $scope.platoModal = {};
            if(plato == null){
                angular.forEach($scope.platosMenu, function(p,k){
                   if(p.idPlato==$scope.pedidoModal.idPlato){
                       $scope.platoModal = p;
                   } 
                });
            }else{
                $scope.platoModal = plato;   
            }
        }
        
        $scope.confirmarBorrar = function(pedido){
            if( $scope.listaDetallesPedido==null || $scope.listaDetallesPedido.length==0){
                $('.emptyPedido').removeClass('animated shake').addClass('animated shake').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
                $(this).removeClass('animated shake');
                    return;
                });
            }else{
                $scope.pedidoModal = {};
                $scope.pedidoModal = pedido;
                $('#modalEliminar').modal('show');
            }
            
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
            
            if(isNaN(pedidoModal.cantidadDetalle)){
                      $('.cantSelect').removeClass('animated shake').addClass('animated shake').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
            $(this).removeClass('animated shake');
        });
                return;
            }
                    
            
            $scope.e = false;
            $scope.listaDetallesPedido.forEach(function (detalle, index) {
                if(detalle.idPlato == $scope.platoModal.idPlato){
                    if($('.cantSelect').prop("disabled")){
                        detalle.cantidadDetalle = parseInt(pedidoModal.cantidadDetalle);
                    }else{
                        detalle.cantidadDetalle += parseInt(pedidoModal.cantidadDetalle);                        
                    }

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
                d.subMostrar = true;
                d.eliminarMostrar = false;
                $scope.listaDetallesPedido.push(d); 
            }
            
            $scope.pedidoModal = {};
            $scope.verificarPedido();
            $('#myModal').modal('hide');
        }
        
        
        $scope.actualizarTotales = function (detalle){
            angular.forEach($scope.listaDetallesPedido, function(py, k){
                if(py.idPlato==detalle.idPlato){
                    py.subtotalDetalle = detalle.cantidadDetalle * detalle.precioPlato;
                }
            });
            $scope.calcularTotalPedido();
        }
        
        $scope.borrarTodos = function() {
            

            $scope.listaDetallesPedido = [];
            $scope.verificarPedido();
            $scope.calcularTotalPedido();
            $('#modalEliminar').modal('hide');
        }
        
        $scope.eliminarUno = function(detalle) {
            angular.forEach($scope.listaDetallesPedido, function(py, k){
                if(py.idPlato==detalle.idPlato){
                    $scope.listaDetallesPedido.splice(k,1);
                }
            });
            
            console.log($scope.listaDetallesPedido);
            $scope.verificarPedido();
            $scope.calcularTotalPedido();
        }
                    
        
        $scope.calcularTotalPedido = function(){
            var total = 0;
            if($scope.listaDetallesPedido!=null){
                $scope.listaDetallesPedido.forEach(function(detalle, index){
                    total += detalle.subtotalDetalle;
                });
            }
                        
            return total;
        }
        
        
        
        $scope.guardarPedido = function(listaDetallesPedido){
            
            if($scope.listaDetallesPedido==null || $scope.listaDetallesPedido.length==0){
                    $('.emptyPedido').removeClass('animated shake').addClass('animated shake').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
                        $(this).removeClass('animated shake');
                        return;
                });
            }else{
                if(!store.get('jwt')||!store.get('authUser')){
                    toastr.error("Debe estar loggeado para guardar el pedido!", "Atención!");
                }else{
                    $scope.authUser = {};
                    $scope.authUser = store.get('authUser');
                    
                    $scope.pedido.direccionPedido = "";
                    $scope.pedido.pagaconPedido = 0;
                    $scope.pedido.estadoPedido = "GUARDADO";
                    $scope.pedido.idUsuario = $scope.authUser.idUsuario;
                    $scope.pedido.idNegocio = $scope.idNegocio;
                    $scope.pedido.listaDetalles = listaDetallesPedido;
                    $scope.pedido.totalPedido = $scope.calcularTotalPedido();
                    PedidoService.guardarPedido($scope.pedido).then(function(prom){
                       if(prom=true){
                           toastr.success("Tu pedido se guardó con éxito!", "Atencion!");
                       } else{
                           toastr.error("Tu pedido no pudo guardarse!", "Atención!");
                       }
                    });
                    }
                }
                
            }
        
        $scope.enviarPedido = function(listaDetallesPedido){
            
            //etc
            //implementar JWT y en el caso que este OK --> enviarPedido
            //si está mal, enviar al estado LOGIN.
            

        
            if( $scope.listaDetallesPedido==null || $scope.listaDetallesPedido.length==0){
                    $('.emptyPedido').removeClass('animated shake').addClass('animated shake').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
                        $(this).removeClass('animated shake');
                        return;
                    });
                }
            else{
                if(!store.get('jwt')||!store.get('authUser')){
                    toastr.error("Debe estar loggeado para enviar el pedido!", "Atención!");
                }else{
                    $scope.authUser = {};
                    $scope.authUser = store.get('authUser');
                    
                    
                    $scope.pedido.direccionPedido = "";
                    $scope.pedido.pagaconPedido = 0;
                    $scope.pedido.estadoPedido = "POR_CONFIRMAR";
                    $scope.pedido.idUsuario = $scope.authUser.idUsuario;
                    $scope.pedido.idNegocio = $scope.idNegocio;
                    $scope.pedido.listaDetalles = listaDetallesPedido;
                    $scope.pedido.totalPedido = $scope.calcularTotalPedido();
                    
                    PedidoService.agregarPedidoEnvio($scope.pedido);
                    $state.go('enviarPedido');  
                }
            }
    }

}]);
