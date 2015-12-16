var myApp = angular.module('myApp');


myApp.controller('NavCtrl', [
    '$scope',
    '$state',
    'store',
    'toastr',
    function($scope, $state, store, toastr){
        
        $scope.$on('$stateChangeStart', function() {
            console.log('escucho bien');
            if(store.get('authUser')){
                $scope.userLog = store.get('authUser');
                $scope.loggedin = true;
                $scope.loggedout = false;
        }else{
            $scope.loggedin = false;
            $scope.loggedout = true;
        }
        });
        
        $scope.logOut = function(){
            if(store.get('authUser')){
                store.remove('authUser');
                store.remove('jwt');
                $scope.loggedin=false;
                $scope.loggedout = true;
                $state.go('elegirNegocio');
            }
        }
        
            

        
        
}]);
