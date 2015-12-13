'use strict';

/**
 * @ngdoc function
 * @name
 * @description
 * # ChartCtrl
 * Controller of the dashboardApp
 */
angular.module('myApp')
  .controller('ChartCtrl', function ($scope,chartService) {

    $scope.year = 2015;
    $scope.business = "todos";

  $scope.loadDefault = function(){
    $scope.cargaTorta();
    $scope.cargaBarra();
    $scope.ventas();
  }
  $scope.ventas = function(){
    chartService.obtenerVentasNegocio().then(function(negocios){
      $scope.neg = negocios;
      $scope.lineChart = {};
      $scope.lineChart.labels=[];
      $scope.lineChart.data=[[]];
      var sumAlm = 0;
      var sumCena = 0;
      var sumDesa = 0;
      var sumMeri = 0;
      angular.forEach($scope.neg,function(v,k){
        if(v.año == $scope.year && v.negocio == $scope.business){
          console.log(v.horario);
          $scope.lineChart.labels.push(v.horario);
          $scope.lineChart.data[0].push(v.cantidad);
          console.log($scope.lineChart.data[0]);
        }else {
          if($scope.business == "todos" && v.año == $scope.year)
          {
          switch (v.horario) {
            case "Almuerzo":
                sumAlm = sumAlm + v.cantidad
                console.log(sumAlm);
              break;
            case "Cena":
                sumCena = sumCena + v.cantidad
                break;
            case "Desayuno":
                sumDesa = sumDesa + v.cantidad
                break;
            case "Merienda":
                sumMeri = sumMeri + v.cantidad
              break;
            default:

          }
        }
      }
        resetDataLine();
      });
      $scope.lineChart.labels.push("Almuerzo");
      $scope.lineChart.data[0].push(sumAlm);
      $scope.lineChart.labels.push("Cena");
      $scope.lineChart.data[0].push(sumCena);
      $scope.lineChart.labels.push("Desayuno");
      $scope.lineChart.data[0].push(sumDesa);
      $scope.lineChart.labels.push("Merienda");
      $scope.lineChart.data[0].push(sumMeri);
    })
  }
  $scope.cargaBarra = function(){
    chartService.obtenerPedidosCategoria().then(function(categorias){
      $scope.cat = categorias;
      $scope.barra={};
      $scope.barra.labels=[];
      $scope.barra.data=[[]];

      var sumPas=0;
      var sumParr=0;
      var sumGour=0;
      var sumEns=0;

      angular.forEach($scope.cat, function(v,k){
        if(v.año == $scope.year && v.negocio == $scope.business){
            $scope.barra.labels.push(v.categoria);
            $scope.barra.data[0].push(v.cantidad);
        }else {
          if ($scope.business == "todos" && v.año == $scope.year) {
            switch (v.categoria) {
              case "Pastas":
                  sumPas = sumPas + v.cantidad;
                break;
              case "Parrilla":
                sumParr = sumParr + v.cantidad;
                break;
              case "Gourmet":
                  sumGour = sumGour + v.cantidad;
                break;
              case "Ensalada":
                  sumEns = sumEns + v.cantidad;
                break;
            }

          }
        }
      resetDatabar();
    });
    $scope.barra.labels.push("Pasta");
    $scope.barra.data[0].push(sumPas);
    $scope.barra.labels.push("Parrilla");
    $scope.barra.data[0].push(sumParr);
    $scope.barra.labels.push("Gourmet");
    $scope.barra.data[0].push(sumGour);
    $scope.barra.labels.push("Ensalada");
    $scope.barra.data[0].push(sumEns);
  });
}
  $scope.cargaTorta = function(){
    chartService.obtenerPedidosTemporada().then(function(temporadas){
      $scope.temp =temporadas;
      $scope.torta = {};
      $scope.torta.labels=[];
      $scope.torta.data=[];
      var sumPri=0;
      var sumVer=0;
      var sumOto=0;
      var sumInv=0;

          angular.forEach($scope.temp, function(v,k){
            if(v.año == $scope.year && v.negocio == $scope.business){
              $scope.torta.labels.push(v.temporada);
              $scope.torta.data.push(v.cantidad);
            }else {
              if ($scope.business == "todos" && v.año == $scope.year) {
                switch (v.temporada) {
                  case "Primavera":
                      sumPri = sumPri + v.cantidad;
                    break;
                  case "Verano":
                    sumVer = sumVer + v.cantidad;
                    break;
                  case "Otoño":
                      sumOto = sumOto + v.cantidad;
                    break;
                  case "Invierno":
                      sumInv = sumInv + v.cantidad;
                    break;
                }

              }
            }
          resetDataTorta();
          });
          $scope.torta.labels.push("Primavera");
          $scope.torta.data.push(sumPri);
          $scope.torta.labels.push("Verano");
          $scope.torta.data.push(sumVer);
          $scope.torta.labels.push("Otoño");
          $scope.torta.data.push(sumOto);
          $scope.torta.labels.push("Invierno");
          $scope.torta.data.push(sumInv);
});
}


function resetDataTorta() {
  angular.forEach($scope.temp, function(value,key){
    $scope.torta.labels.splice(value.temporada)
    $scope.torta.data.splice(value.cantidad)
  })
}
function resetDatabar() {
  angular.forEach($scope.cat, function(value,key){
    $scope.barra.labels.splice(value.categoria)
    $scope.barra.data[0].splice(value.cantidad)
  })
}
function resetDataLine() {
  angular.forEach($scope.neg, function(value,key){
    $scope.lineChart.labels.splice(value.horario)
    $scope.lineChart.data[0].splice(value.cantidad)
  })
}

  $scope.loadDefault();

//  http://www.json-generator.com/api/json/get/bPRdsJkZCG?indent=2 json si negocios

    $('#yearSelector').on('input',function(){
      $scope.loadDefault();
    })
    $('#negocioSelector').on('change',function(){
      $scope.loadDefault();
    })

});
