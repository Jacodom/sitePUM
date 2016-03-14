package backend.app.controller;

import backend.app.dto.DtoCadete;
import backend.app.dto.DtoDetallePedido;
import backend.app.dto.DtoNewPedido;
import backend.app.dto.DtoRonda;
import backend.app.model.Cadete;
import backend.app.model.DetallePedido;
import backend.app.model.Pedido;
import backend.app.model.Ronda;
import backend.app.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jacobo on 13/03/2016.
 */
@Controller
@RequestMapping("/sede")
public class SedeController {

    private PedidoService pedidoService = new PedidoService();
    private CadeteService cadeteService = new CadeteService();
    private RondaService rondaService = new RondaService();
    private NegocioService negocioService = new NegocioService();


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerPedidos", method = RequestMethod.GET)
    public List<DtoNewPedido> obtenerPedidos(){
        List<Pedido> listaPedidos = pedidoService.obtenerPedidos();
        List<DtoNewPedido> listaPedidosDto = new ArrayList<DtoNewPedido>();
        PlatoService platoService = new PlatoService();
        List<DtoDetallePedido> listaDetallesDto = new ArrayList<DtoDetallePedido>();

        for(Pedido pedido : listaPedidos){
            DtoNewPedido dtoPedido = new DtoNewPedido();

            dtoPedido.setEstadoPedido(pedido.getEstadoPedido());
            dtoPedido.setIdUsuario(pedido.getUsuario().getIdUsuario());
            dtoPedido.setDireccionPedido(pedido.getDireccionPedido());
            dtoPedido.setFechaPedido(pedido.getFechaPedido());

            for(DetallePedido detallePedido : pedido.getDetallePedidos()){
                DtoDetallePedido detallePedidoDto = new DtoDetallePedido();
                detallePedido.setAclaracionDetalle(detallePedido.getAclaracionDetalle());
                detallePedido.setCantidadDetalle(detallePedido.getCantidadDetalle());
                detallePedido.setSubtotalDetalle(detallePedido.getSubtotalDetalle());
                detallePedido.setPlato(platoService.obtenerPlato(detallePedido.getIdDetalle()));
                //detallePedido.setPedido(pedido) ;
                listaDetallesDto.add(detallePedidoDto);
            }

            dtoPedido.setListaDetalles(listaDetallesDto);
            dtoPedido.setIdNegocio(pedido.getNegocio().getIdNegocio());
            dtoPedido.setPagaconPedido(pedido.getPagaconPedido());
            dtoPedido.setTotalPedido(pedido.getTotalPedido());
            dtoPedido.setIdPedido(pedido.getIdPedido());

            listaPedidosDto.add(dtoPedido);
        }

        return listaPedidosDto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerCadetes", method = RequestMethod.GET)
    public List<DtoCadete> obtenerCadetes(){
        List<DtoCadete> listaCadetesDto = new ArrayList<DtoCadete>();
        List<Cadete> listaCadetes = cadeteService.obtenerCadetes();

        for(Cadete cadete : listaCadetes){
            DtoCadete dtoCadete = new DtoCadete();

            dtoCadete.setApellido(cadete.getApellidoCadete());
            dtoCadete.setEstado(cadete.getEstadoCadete());
            dtoCadete.setIdCadete(cadete.getIdCadete());
            dtoCadete.setNombre(cadete.getNombreCadete());
            dtoCadete.setTelefono(cadete.getTelefonoCadete());

            listaCadetesDto.add(dtoCadete);
        }

        return listaCadetesDto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/modificarCadete",method = RequestMethod.POST)
    public Boolean modificarCadete(@RequestBody DtoCadete dtoCadete){
        Cadete cadete = new Cadete();

        cadete.setIdCadete(dtoCadete.getIdCadete());
        cadete.setApellidoCadete(dtoCadete.getApellido());
        cadete.setEstadoCadete(dtoCadete.getEstado());
        cadete.setNombreCadete(dtoCadete.getNombre());
        cadete.setTelefonoCadete(dtoCadete.getTelefono());

        if(cadeteService.modificarCadete(cadete)){
            return true;
        }else{
            return false;
        }
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/crearRonda",method = RequestMethod.POST)
    public Boolean guardarRonda(@RequestBody DtoRonda dtoRonda){
        Ronda ronda = new Ronda();
        Cadete cadete = new Cadete();
        Set<Pedido> listaPedidos = new HashSet<Pedido>();

        //cadete
        cadete.setTelefonoCadete(dtoRonda.getCadete().getTelefono());
        cadete.setNombreCadete(dtoRonda.getCadete().getNombre());
        cadete.setEstadoCadete(dtoRonda.getCadete().getEstado());
        cadete.setApellidoCadete(dtoRonda.getCadete().getApellido());
        cadete.setIdCadete(dtoRonda.getCadete().getIdCadete());

        ronda.setCadete(cadete);
        ronda.setRecaudacionRonda(dtoRonda.getRecaudacion());
        ronda.setVueltoRonda(dtoRonda.getVuelto());

        for(DtoNewPedido dtoPedido : dtoRonda.getPedidos() ){
            Pedido pedido = pedidoService.obtenerPedido(dtoPedido.getIdPedido());

            listaPedidos.add(pedido);
        }

        ronda.setPedidos(listaPedidos);

        if(rondaService.guardarRonda(ronda)){
            return true;
        }else{
            return false;
        }

    }
}
