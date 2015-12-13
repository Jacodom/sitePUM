

/**
 * @ngdoc service
 * @name dashboardApp.chart
 * @description
 * # chart
 * Factory in the dashboardApp.
 */
angular.module('myApp')
  .factory('chartService', function ($http) {
    // Service logic
    return{
      obtenerPedidosTemporada : function(){
        return $http({
          method:"GET",
            skipAuthorization: true,
          url:"http://www.json-generator.com/api/json/get/bIuaqAcrNe?indent=2"
        }).then(function(res){
          if(res.status == 200){
            //console.log(res.data);
            return res.data;
          }else{
            return res.error;
          }
        });
      },
      obtenerPedidosCategoria : function () {
        return $http({
          method:"GET",
            skipAuthorization: true,
          url:"http://www.json-generator.com/api/json/get/caXJOyRYEi?indent=2"
        }).then(function(res){
          if (res.status ==200) {
            return res.data;
          }else{
            return res.error;
          }
        });
      },
      obtenerVentasNegocio : function(){
        return $http({
          method:"GET",
            skipAuthorization: true,
          url:"http://www.json-generator.com/api/json/get/bSzDdcROWa?indent=2"
        }).then(function(res){
          if (res.status ==200) {
            return res.data;
          }else{
            return res.error;
          }
        });
      }
    }



  });
