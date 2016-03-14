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
    .controller('PumgridCtrl', function ($scope,pumGridService,uiGridConstants, toastr) {
        //get para pedidos
        $scope.flag = 0;
        var suma = 0;
        $scope.timerRunning = true;
        $scope.SumTot = 0;
        $scope.cantidaSel = 0;
        //$scope.mySelections = [];
        $scope.cadetes = {};
        $scope.ronda ={};
    
        $scope.ronda.cadete={};
        $scope.ronda.pedidos = [];    
    //ojo
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
                    $scope.sumarTotales(rows.entity.totalPedido,rows.entity.pagaconPedido,rows.entity);
                    $('#errorRonda').addClass('hide')
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

        $scope.sumarTotales = function(total,pagaCon,pedido){
            //console.log(value);
            if($scope.cantidaSel >$scope.flag ){
                $scope.SumTot = $scope.SumTot + total;
                $scope.sumVuelto += pagaCon- total;
                
                $scope.ronda.pedidos.push(pedido);
                
                $scope.flag = $scope.cantidaSel;
            }else {
                $scope.flag =  $scope.cantidaSel -1;
                $scope.SumTot = $scope.SumTot - total;
                $scope.sumVuelto -= pagaCon- total;
                
                angular.forEach($scope.ronda.pedidos, function(py, k){
                if(py.idPedido==pedido.idPedido){
                    $scope.ronda.pedidos.splice(k,1);
                }
                    
            });
                //$scope.ronda.pedidos.push(rows.entity);
            }
        }

        $scope.gridOptions.multiSelect = true;
        $scope.gridOptions.columnDefs = [
            { name:'Codigo', field: 'idPedido',width:'9%' },
            { name:'Direccion', field: 'direccionPedido' },
            { name:'Estado',field: 'estadoPedido',width:'9%', filter:{
                type: uiGridConstants.filter.SELECT,
                selectOptions:[{value:'Sede',label:'Sede'},{value:'Calle',label:"Calle"}]}}, /// reemplazar por estado de pedidos, en calle finalizado etc
            { name:'Usuario', field: 'nombreUsuario'},
            { name:'Telefono', field: 'telefonoUsuario',enableFiltering: false},
            { name:'Negocio', field: 'nombreNegocio'},
            { name:'PagaCon', field: 'pagaconPedido',width:'11%',cellFilter: 'currency', enableFiltering: false,aggregationType: uiGridConstants.aggregationTypes.sum,},
            { name:'Total', field: 'totalPedido',width:'10 %',cellFilter: 'currency',enableFiltering: false,aggregationType:uiGridConstants.aggregationTypes.sum},
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

        // modal
        $scope.abrirModal = function(detalleRonda){
            if($scope.cantidaSel>0){
                $scope.saleCon = 0;
                $scope.recaudacion = 0;
                $('#modalRonda').modal('show');
                /*$('#ipSaleCon').bind('input',function(){
                    $scope.saleCon =$(this).val();
                    if($scope.saleCon < $scope.sumVuelto){
                        $('#errorVuelto').removeClass('hide')
                    }else {
                        $('#errorVuelto').addClass('hide')
                        $scope.recaudacion = $scope.SumTot + ($scope.saleCon - $scope.sumVuelto);
                    }
                });*/
            }else {
                //toastr.error("Tu pedido no pudo guardarse!", "Atención!");
                $('#errorRonda').removeClass('hide')
                $('#btnCrearRonda').removeClass('animated shake').addClass('animated shake').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
                    $(this).removeClass('animated shake');
                    return;
                });
            }
        };
        //calcula recaudacion

        $('#ipSaleCon').bind('input',function(){
                    $scope.saleCon = $(this).val();                     
                    if($scope.saleCon < $scope.sumVuelto){
                        $('#errorVuelto').removeClass('hide');
                    }else {
                        $('#errorVuelto').addClass('hide');
                        $scope.recaudacion = $scope.SumTot + ($scope.saleCon - $scope.sumVuelto);
                    }
                    
                    if($scope.saleCon==""){
                        $('#errorVuelto').addClass('hide');
                        $scope.recaudacion = 0;
                    }
        });
    
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
                    //$scope.ronda.IdRonda = 1
                    $scope.ronda.total = $scope.SumTot;
                    $scope.ronda.recaudacion =$scope.recaudacion;
                    $scope.ronda.vuelto=$scope.sumVuelto;
                    
                    pumGridService.guardarRonda($scope.ronda)
                        .then(function(promise){
                            if(promise==true){
                                toastr.success("Ronda guardada con éxito!", "Atencion!");
                                
                                $scope.ronda.cadete.estado= "Calle";
                                pumGridService.modificarEstadoCadete($scope.ronda.cadete).then(function(promise){
                                    if(promise==true){
                                         $scope.ronda={};
                                $scope.CargarGrilla();
                                $scope.CargarCadetes();
                                $scope.SumTot = 0;
                                $scope.cantidaSel = 0;
                                $scope.sumVuelto = 0;
                                $scope.flag = 0;
                                 $('#modalRonda').modal('hide');
                                    }
                                });
                                
                               
                                
                                console.log("True");
                            } else{
                                toastr.error("La Ronda no pudo guardarse!", "Atención!");
                                console.log("False");
                            }
                        });
                }
            }
        }

    });

