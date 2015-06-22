package backend.app.controller;

import backend.app.dto.*;
import backend.app.model.*;
import backend.app.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private MenuService menuService = new MenuService();

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerCategoriasNegocio", method = RequestMethod.GET)
    public List<DtoCategoria> obtenerCategoriasNegocio(@RequestBody int idNegocio) throws Exception {
        List<Categoria> listaCategorias =  negocioService.obtenerCategoriasNegocio(negocioService.obtenerNegocio(idNegocio));
        List<DtoCategoria> listaCategoriasDTO = new ArrayList<DtoCategoria>();

        for(Categoria categoria : listaCategorias){
            DtoCategoria dtoCategoria = new DtoCategoria();
            dtoCategoria.setIdCategoria(categoria.getIdCategoria());
            dtoCategoria.setNombreCategoria(categoria.getNombreCategoria());
            listaCategoriasDTO.add(dtoCategoria);
        }

        return listaCategoriasDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerMenuActivo", method = RequestMethod.GET)
    public DtoMenu obtenerMenuActivo (@RequestBody int idNegocio){
        Menu menu = negocioService.obtenerMenuActivo(negocioService.obtenerNegocio(idNegocio));
        DtoMenu menuDto = new DtoMenu();

        menuDto.setIdMenu(menu.getIdMenu());
        menuDto.setNombreMenu(menu.getNombreMenu());
        menuDto.setEstadoMenu(menu.getEstadoMenu());

        return menuDto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerPlatosMenu", method = RequestMethod.GET)
    public List<DtoPlato> obtenerPlatosMenu(@RequestBody int idMenu){
        List<Plato> listaPlatos = menuService.obtenerPlatosMenu(menuService.obtenerMenu(idMenu));
        List<DtoPlato> listaPlatosDTO = new ArrayList<DtoPlato>();

        for(Plato plato : listaPlatos){
            DtoPlato dtoPlato = new DtoPlato();
            DtoCategoria dtoCategoria = new DtoCategoria();
            dtoPlato.setNombrePlato(plato.getNombrePlato());

            dtoCategoria.setIdCategoria(plato.getCategoria().getIdCategoria());
            dtoCategoria.setNombreCategoria(plato.getCategoria().getNombreCategoria());
            dtoPlato.setCategoria(dtoCategoria);

            dtoPlato.setCoccionPlato(plato.getCoccionPlato());
            dtoPlato.setDescPlato(plato.getDescPlato());
            dtoPlato.setIdPlato(plato.getIdPlato());
            dtoPlato.setPrecioPlato(plato.getPrecioPlato());

            listaPlatosDTO.add(dtoPlato);
        }

        return listaPlatosDTO;
    }

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


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/magia", method = RequestMethod.GET)
    public void guardar(){
        DtoNewPedido pedido = new DtoNewPedido();

        pedido.setEstadoPedido("activo");

        Pedido pedidoG = new Pedido();

        pedidoG.setEstadoPedido(pedido.getEstadoPedido());

        if(pedidoService.guardarPedido(pedidoG))
            System.out.println("Se guardo!!!");
    }

}
