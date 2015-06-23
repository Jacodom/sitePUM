angular.module("sitePumApp",[])
    .controller('negocioController',function($scope,$http){
    $scope.listaNegocio=function(){
        var request = $http({
        method:'GET',
        url:'/elegirNegocio/obtenerNegocios',
        headers:{
            "Content-Type":"application/json",
            "Accept":"text/plain, application/json"    
        }
    });
};
});
        /*{nombre:'La mira',direccion:'mitre 213' },
        {nombre:'Negocio',direccion:'mitre 213' },
        {nombre:'Negocio 1',direccion:'nacion 45' },
        {nombre:'Negocio 2',direccion:'mitre 213' },
        {nombre:'Negocio 3',direccion:'mitre 213' },
    ];*/
