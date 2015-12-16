var myApp = angular.module('myApp');


myApp.controller('EnviarPedidoCtrl', [
    'factoryMaps',
    '$scope',
    'PedidoService',
    '$state',
    'store',
    'toastr',
    'PedidoService',
    function(factoryMaps,$scope, PedidoService, $state, store, toastr, PedidoService){
        $scope.pedido = store.get('pedidoUser');
        $scope.user = store.get('authUser');
        $scope.pagaCon = $scope.pedido.totalPedido;
        $scope.time;
        
        
        
        $scope.getTime = function(){
            var win={};
            win = $scope.pedido.listaDetalles[0];
            console.log(win);
            angular.forEach($scope.pedido.listaDetalles, function(v,k){
                if(parseInt(win.plato.coccionPlato)< parseInt(v.plato.coccionPlato))
                    {
                        win = v;
                    }
            });
            
            $scope.time = win.plato.coccionPlato;
        }
        
        $scope.getTime();
        
        $scope.map = {
            center: {
                latitude: -33.333333,
                longitude: -60.216667
            },
            zoom: 17,
            markers: [],
            control: {},
            options: {
                scrollwheel: true
            }
        };
        
        
        $scope.modificarDire = function(address) {
            $scope.pedido.direccionPedido=address;
        }

        $scope.abrirModal = function(direccion){
            $scope.direccionModal = {};
            $scope.direccionModal = direccion;
            $scope.addAddress();
            $scope.autocomplete();
        };
        
        
        $scope.calcularTotalPedido = function(){
            var total = 0;
            if($scope.pedido.listaDetalles!=null){
                $scope.pedido.listaDetalles.forEach(function(detalle, index){
                    total += detalle.subtotalDetalle;
                });
            }
                        
            return total;
        }
        


        
        $scope.autocomplete = function() {
            
            var input = document.getElementById('searchBox');
            console.log(input);
            var autocomplete = new google.maps.places.Autocomplete(input);

            google.maps.event.addListener(autocomplete, 'place_changed', function() {
                var dir = autocomplete.getPlace();
                //console.log(dir.formatted_address);
                $scope.direccionModal = dir.formatted_address;
            });
            
            //angular.element(".pac-container")[0].style.zIndex = 1100;
        }


 

        //$scope.map.markers.push($scope.PickupmealMarker);

        $scope.addCurrentLocation = function () {
          $scope.map.markers.length = [];
                factoryMaps.createByCurrentLocation(function (marker) {
                    marker.options.labelContent = 'Usted está aquí';
                    $scope.map.markers.push(marker);
                    $scope.refresh(marker.coords);
                    console.log(marker.coords + "coords");
                    factoryMaps.createFrom
                });
            };


        $scope.addAddress = function() {
              $scope.map.markers.length = 0;
                var address = $scope.direccionModal;
                console.log(address);
                if (address !== '') {
                    factoryMaps.createByAddress(address, function(marker) {
                        $scope.map.markers.push(marker);
                        $scope.refresh(marker.coords);
                    });
                }
                else {
                  alert("Escriba una direccion vállida")
                }
        };

        $scope.refresh = function(coords) {
            $scope.map.control.refresh({latitude: coords.latitude,
                longitude: coords.longitude});
        }
        
        $scope.calcularVuelto = function(){
            return eval($scope.pagaCon - $scope.pedido.totalPedido);
        }
        
        $scope.enviarPedido = function(){
            if($scope.calcularVuelto()<0){
                toastr.error("No puede pagar con menos de lo que sale el pedido!");
                return;
            }else{
                if($scope.pedido.direccionPedido == ''){
                    toastr.error("Debe especificar una direccion de envío");
                    return;
                }else{
                    $scope.pedido.estadoPedido = "ENVIADO_A_LOCAL";
                    if(PedidoService.guardarPedido($scope.pedido)){
                        store.remove('pedidoUser');
                        $state.go('elegirNegocio');
                    }
                                        
                }
            }
        }
    
        

}]);
