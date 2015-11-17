var myApp = angular.module('myApp');

myApp.controller('modalCtrl', [
    '$scope',
    'PedidoService',
    '$state',
    function($scope, PedidoService, $location, $anchorScroll, $stateParams, $state){
      $scope.items = Items;

    	$scope.save = function (param)
    	{
    		console.log(param)
    	};

        $scope.cancel = function ()
        {
        	$modalInstance.dismiss('cancel');
        };
}]);
