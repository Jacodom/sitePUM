var myApp = angular.module('myApp');

myApp.filter('negociosFiltrados',[function(){
    return function (negocios,categoriaSeleccionada){
        console.log(negocios + ' :Negocio');
        console.log(categoriaSeleccionada + ' :CategoriasSeleccionadas');
        if(!angular.isUndefined(negocios) && !angular.isUndefined(categoriaSeleccionada) && categoriaSeleccionada.length > 0){
            var listaTemporal = [];
            console.log("Pasa primer if")
            angular.forEach(categoriaSeleccionada, function(id){
                angular.forEach(negocios, function(valor,att){
                    angular.forEach(valor.listaCategorias,function(categoria,index){
                        if(angular.equals(categoria.idCategoria,id)){
                            //console.log(valor);
                           listaTemporal.push(valor);
                           }
                    });
                });
            });
            return listaTemporal;
        }else {
            return negocios;
        }
    };
}]); 
    
myApp.controller('NegocioController',['$scope','NegocioService',function($scope, NegocioService){
    
    $scope.categoriaSeleccionada = [];
    $scope.setCategoria = function(){
        var id = this.categoria.idCategoria;
        if(_.contains($scope.categoriaSeleccionada, id)){
            $scope.categoriaSeleccionada = _.without($scope.categoriaSeleccionada, id);
        }else{
            $scope.categoriaSeleccionada.push(id);
            console.log($scope.categoriaSeleccionada);
        }
        return false;
    };
    
    $scope.isChecked =function(id){
        if(_.contains($scope.categoriaSeleccionada, id)){
            return 'fa fa-check-circle';
        }
        return 'fa fa-search';
    };

    
    obtenerCategorias();
    obtenerNegocios();
    
    function obtenerCategorias(){
        NegocioService.obtenerCategorias().then(function(categorias){
            $scope.listaCategorias = categorias;
        });
    }
    
    function obtenerNegocios(){
        NegocioService.obtenerNegocios().then(function(negocios){
            $scope.listaNegocio = negocios;
        });
    }
    
}])

