var myApp = angular.module('myApp');


myApp.controller('EnviarPedidoCtrl', [
    'factoryMaps',
    '$scope',
    'PedidoService',
    '$state',
    function(factoryMaps,$scope, PedidoService, $location, $anchorScroll, $stateParams, $state){
        $scope.pedido = PedidoService.obtenerPedidoEnvio();
        
        $scope.modificarDire = function(address) {
          console.log(address);

        }

        $scope.abrirModal = function(direccion){
            $scope.direccion = {};
            $scope.direccion = direccion;
            console.log(direccion);
        };
        // factory maps esta en service maps.js
        factoryMaps.createByAddress
      /*  factoryMaps.createByCoords(-33.333333, -60.216667, function (marker) {
            marker.options.labelContent = 'Pickupmeal';
            $scope.PickupmealMarker = marker;
        });*/

        $scope.address = '';
        function autocomplete() {
            var input = document.getElementById('searchBox');
            var autocomplete = new google.maps.places.Autocomplete(input);

            google.maps.event.addListener(autocomplete, 'place_changed', function() {
                      var dir = autocomplete.getPlace();
                      //console.log(dir.formatted_address);
                      $scope.address = dir.formatted_address;
                  });
      }
      autocomplete();


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

              $scope.map.markers.push($scope.PickupmealMarker);

              $scope.addCurrentLocation = function () {
                $scope.map.markers.length = [];
                      factoryMaps.createByCurrentLocation(function (marker) {
                          marker.options.labelContent = 'Usted está aquí';
                          $scope.map.markers.push(marker);
                          refresh(marker.coords);
                          console.log(marker.coords + "coords");
                      });
                  };


                  $scope.addAddress = function() {
                    $scope.map.markers.length = 0;
                      var address = $scope.address;
                      console.log(address);
                      if (address !== '') {
                          factoryMaps.createByAddress(address, function(marker) {
                              $scope.map.markers.push(marker);
                              refresh(marker.coords);
                          });
                      }
                      else {
                        alert("Escriba una direccion vállida")
                      }
                  };

                  function refresh(coords) {
                  $scope.map.control.refresh({latitude: coords.latitude,
                      longitude: coords.longitude});
              }


}]);
