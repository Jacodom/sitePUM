'use strict';

/**
 * @ngdoc function
 * @name
 * @description
 * # ChartCtrl
 * Controller of the dashboardApp
 */
angular.module('myApp')
  .controller('ChartCtrl', function ($scope) {
    // obtener Negocios para llenar el combo


    // Carga de datos para grafico de torta
    // obtener json Temp que contenga el nombre de las temporadas y la cantidad (por año por parametro a definir)
    $scope.torta = {};
      $scope.torta.labels = ["Invierno", "Verano", "Otoño","Primavera"];
      $scope.torta.data = [20, 60, 40,80];

    // Carga de datos para grafico de barras
    // obtener json Categorias que contenga el nombre de las Categorias y la cantidad (por año por parametro a definir)
    $scope.bar= {};
      $scope.bar.labels = ['Pastas', 'Parrilla', 'Gourmet', 'Ensaladas'];
      $scope.bar.data = [
        [29, 30, 40, 4]
      ];

      // Grafico de linas orientado a las ventas por negocio
      $scope.labels = ["La mira", "ILbaccino", "Bar Avenida", "Rocco", "La caleta"]; // recorrer los restaurantes
      $scope.data = [
       [65, 59, 80, 81,60]
     ];
      $scope.onClick = function (points, evt) {
      console.log(points, evt);
      };

});
