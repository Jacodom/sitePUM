var myApp = angular.module('myApp');


myApp.controller('EnviarPedidoCtrl', [
    '$scope',
    'PedidoService',
    '$state',
    function($scope, PedidoService, $location, $anchorScroll, $stateParams, $state){
        $scope.pedido = PedidoService.obtenerPedidoEnvio();
}]);
