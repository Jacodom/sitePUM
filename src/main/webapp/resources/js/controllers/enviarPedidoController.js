var myApp = angular.module('myApp');


myApp.controller('EnviarPedidoCtrl', [
    'factoryMaps',
    '$scope',
    'PedidoService',
    '$state',
    'store',
    function(factoryMaps,$scope, PedidoService, $state, store){
        $scope.pedido = store.get('pedidoUser');
        $scope.user = store.get('authUser');
        $scope.PickupmealMarker = {}; 
        
        
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
          console.log(address);

        }

        $scope.abrirModal = function(direccion){
            $scope.direccionModal = {};
            $scope.direccionModal = direccion;
            $scope.autocomplete();
        };
        
        
        factoryMaps.createByCoords(-33.333333, -60.216667, function (marker) {
            marker.options.labelContent = 'Pickupmeal';
            $scope.PickupmealMarker = marker;
        });

        
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


 

        $scope.map.markers.push($scope.PickupmealMarker);

        $scope.addCurrentLocation = function () {
          $scope.map.markers.length = [];
                factoryMaps.createByCurrentLocation(function (marker) {
                    marker.options.labelContent = 'Usted está aquí';
                    $scope.map.markers.push(marker);
                    $scope.refresh(marker.coords);
                    console.log(marker.coords + "coords");
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

}]);
