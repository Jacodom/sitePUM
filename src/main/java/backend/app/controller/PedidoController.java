package backend.app.controller;

import backend.app.dto.DtoDetallePedido;
import backend.app.dto.DtoNewPedido;
import backend.app.model.DetallePedido;
import backend.app.model.Pedido;
import backend.app.model.Plato;
import backend.app.service.NegocioService;
import backend.app.service.PedidoService;
import backend.app.service.PlatoService;
import backend.app.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pablo on 20/06/2015.
 */
@Controller
@RequestMapping("/gestionarPedido")
public class PedidoController {

    private PedidoService pedidoService = new PedidoService();
    private NegocioService negocioService = new NegocioService();
    private UsuarioService usuarioService = new UsuarioService();
    private PlatoService platoService = new PlatoService();

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/guardarPedido", method = RequestMethod.POST)
    public Boolean guardarPedido(@RequestBody DtoNewPedido newPedido){
        Pedido pedido = new Pedido();

        pedido.setNegocio(negocioService.obtenerNegocio(newPedido.getIdNegocio()));
        pedido.setUsuario(usuarioService.obtenerUsuario(newPedido.getIdUsuario()));
        pedido.setDireccionPedido(newPedido.getDireccionPedido());
        pedido.setTotalPedido(newPedido.getTotalPedido());
        pedido.setPagaconPedido(newPedido.getPagaconPedido());
        pedido.setEstadoPedido(newPedido.getEstadoPedido());

        Set<DetallePedido> listaDetallesPedido = new HashSet<DetallePedido>();

        for(DtoDetallePedido detalle : newPedido.getListaDetalles()){
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setAclaracionDetalle(detalle.getAclaracionDetalle());
            detallePedido.setCantidadDetalle(detalle.getCantidadDetalle());
            detallePedido.setSubtotalDetalle(detalle.getSubtotalDetalle());
            detallePedido.setPlato(platoService.obtenerPlato(detalle.getIdPlato()));
            listaDetallesPedido.add(detallePedido);
        }

        pedido.setDetallePedidos(listaDetallesPedido);

        if(pedidoService.guardarPedido(pedido)){
            return true;
        }else{
            return false;
        }
    }

    //agregar obtenerMenuActivo, categoriasNegocio y obtenerPlatosMenu
}
