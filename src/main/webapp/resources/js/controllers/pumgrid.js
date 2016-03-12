/**
 * Created by Pablo on 12/03/2016.
 */
'use strict';

/**
 * @ngdoc function
 * @name dashboardApp.controller:PumgridCtrl
 * @description
 * # PumgridCtrl
 * Controller of the dashboardApp
 */
angular.module('myApp')
    .controller('PumgridCtrl', function ($scope,pumGridService,uiGridConstants) {
        //get para pedidos
        var flag = 0;
        var suma = 0;
        $scope.timerRunning = true;
        $scope.SumTot = 0;
        $scope.cantidaSel = 0;
        //$scope.mySelections = [];
        $scope.cadetes = {};
        $scope.ronda ={};
        $scope.sumVuelto = 0;
        $scope.recaudacion = 0;
        $scope.gridOptions = {
            enableSorting: true,
            enableRowSelection: true,
            enableSelectAll: true,
            showGridFooter:true,
            enableFiltering: true,

            onRegisterApi: function (gridApi) {
                $scope.gridApi = gridApi;
                gridApi.selection.on.rowSelectionChanged($scope,function(rows){
                    $scope.cantidaSel =gridApi.selection.getSelectedRows().length;
                    $scope.sumarTotales(rows.entity.Total,rows.entity.PagaCon);
                    //sumarTotales($scope.SumTot);
                });
                gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
                    if(rows > 0){ // select all
                        $scope.cantidaSel= rows.length;
                        //$scope.sumarTotales(rows.entity.Total)
                    }else {
                        $scope.SumTot=rows.columns[5].getAggregationValue()
                        $scope.cantidaSel =gridApi.selection.getSelectedRows().length;
                    }
                });
            }
        };

        $scope.sumarTotales = function(total,pagaCon){
            //console.log(value);
            if($scope.cantidaSel >flag ){
                $scope.SumTot = $scope.SumTot + total;
                $scope.sumVuelto += pagaCon- total;

                flag = $scope.cantidaSel;
            }else {
                flag =  $scope.cantidaSel -1;
                $scope.SumTot = $scope.SumTot - total;
                $scope.sumVuelto -= pagaCon- total;
            }
        }

        $scope.gridOptions.multiSelect = true;
        $scope.gridOptions.columnDefs = [
            { name:'Codigo', field: 'Codigo',width:'9%' },
            { name:'Direccion', field: 'Direccion' },
            { name:'Estado',field: 'Estado',width:'9%', filter:{
                type: uiGridConstants.filter.SELECT,
                selectOptions:[{value:'false',label:'False'},{value:'true',label:"True"}]}}, /// reemplazar por estado de pedidos, en calle finalizado etc
            { name:'Usuario', field: 'Usuario'},
            { name:'Telefono', field: 'Telefono',enableFiltering: false},
            { name:'Negocio', field: 'Negocio'},
            { name:'PagaCon', field: 'PagaCon',width:'11%',cellFilter: 'currency', enableFiltering: false,aggregationType: uiGridConstants.aggregationTypes.sum,},
            { name:'Total', field: 'Total',width:'10 %',cellFilter: 'currency',enableFiltering: false,aggregationType:uiGridConstants.aggregationTypes.sum},
        ];
        $scope.CargarGrilla = function(){
            pumGridService.obtenerPedidosPum()
                .then(function(data){
                    $scope.gridOptions.data = data;
                },function(error){ // no se cumple la promise
                    console.log("Error al obtener los pedidos");
                });
        }
        $scope.CargarCadetes = function(){
            pumGridService.obtenerCadetes()
                .then(function(data){
                    $scope.cadetes = data;
                },function(error){ // no se cumple la promise
                    console.log("Error al cargar Cadetes");
                });
        }

        $scope.select = function(){
            if($('#btnSelect').hasClass('fa-check-square-o')){
                console.log("Entra");
                $('#btnSelect').removeClass('fa-check-square-o').addClass('fa-check-square')
                $scope.selectAll();
                return;
            }else {
                $('#btnSelect').removeClass('fa-check-square animated tada').addClass('fa-check-square-o')
                $scope.clearAll();
                return;
            }
        }
        $scope.selectAll = function() {
            $scope.gridApi.selection.selectAllRows();
            $('#btnSelect').addClass('animated tada');
        };

        $scope.clearAll = function() {
            $scope.gridApi.selection.clearSelectedRows();
        };
        // functions para el timer
        $scope.timer = function(){
            $scope.startTimer = function (){
                $scope.$broadcast('timer-start');
                $scope.timerRunning = true;
            };

            $scope.stopTimer = function (){
                $scope.$broadcast('timer-stop');
                $scope.timerRunning = false;
            };

        }
        $scope
        // modal
        $scope.abrirModal = function(detalleRonda){
            $scope.saleCon = 0;
            $('#ipSaleCon').bind('input',function(){
                $scope.saleCon =$(this).val();
                if($scope.saleCon < $scope.sumVuelto){
                    $('#errorVuelto').removeClass('hide')
                }else {
                    $('#errorVuelto').addClass('hide')
                    $scope.recaudacion = $scope.SumTot + ($scope.saleCon - $scope.sumVuelto);
                }
            });
        };
        //calcula recaudacion

        $scope.crearRonda = function(ronda){
            if(ronda.cadete == undefined){
                $('#selectCadetes').removeClass('animated shake').addClass('animated shake').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
                    $(this).removeClass('animated shake');
                    return;
                });
                if(ronda.saleCon == undefined){
                    //agregar clases para verificar
                    $('#ipSaleCon').removeClass('animated shake').addClass('animated shake').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
                        $(this).removeClass('animated shake');
                        return;
                    });
                }
            }else {
                if(ronda.saleCon == undefined){
                    //agregar clases para verificar
                    $('#ipSaleCon').removeClass('animated shake').addClass('animated shake').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
                        $(this).removeClass('animated shake');
                        return;
                    });
                }else {
                    $scope.ronda.IdRonda = 1
                    $scope.ronda.total = $scope.SumTot
                    $scope.ronda.recaudacion =$scope.recaudacion

                    pumGridService.guardarRonda($scope.ronda)
                        .then(function(promise){
                            if(promise=true){
                                //toastr.success("Tu pedido se guardó con éxito!", "Atencion!");
                                console.log("True");
                            } else{
                                //toastr.error("Tu pedido no pudo guardarse!", "Atención!");
                                console.log("False");
                            }
                        });
                }
            }
        }

    });

