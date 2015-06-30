package backend.app.controller;


import backend.app.dto.DtoCategoria;
import backend.app.dto.DtoDetallePedido;
import backend.app.dto.DtoNegocio;
import backend.app.model.*;
import backend.app.service.CategoriaService;
import backend.app.service.MenuService;
import backend.app.service.NegocioService;
import backend.app.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacobo on 20/06/2015.
 */
@Controller
@RequestMapping("/elegirNegocio")
public class NegocioController {


    private CategoriaService categoriaService = new CategoriaService();
    private NegocioService negocioService = new NegocioService();
    private UsuarioService usuarioService = new UsuarioService();
    private MenuService menuService = new MenuService();
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerCategorias", method = RequestMethod.GET)
    public List<DtoCategoria> obtenerCategorias(){
        List<Categoria> listaCategorias = categoriaService.obtenerCategorias();
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
    @RequestMapping(value = "/obtenerNegocios", method = RequestMethod.GET)
    public List<DtoNegocio> obtenerNegocios() throws Exception {
        List<Negocio> listaNegocios = negocioService.obtenerNegocios();
        List<DtoNegocio> listaNegociosDTO = new ArrayList<DtoNegocio>();
        List<DtoCategoria> listaDtoCategoria = new ArrayList<DtoCategoria>();
        for(Negocio negocio : listaNegocios){
            DtoNegocio dtoNegocio = new DtoNegocio();
            dtoNegocio.setIdNegocio(negocio.getIdNegocio());
            dtoNegocio.setDomicilioNegocio(negocio.getDomicilioNegocio());
            dtoNegocio.setLogoNegocio(negocio.getLogoNegocio());
            dtoNegocio.setNombreNegocio(negocio.getNombreNegocio());
            dtoNegocio.setTelefonoNegocio(negocio.getTelefonoNegocio());
            for(Categoria categoria : negocioService.obtenerCategoriasNegocio(negocio)){
                DtoCategoria dtoCategoria = new DtoCategoria();
                dtoCategoria.setIdCategoria(categoria.getIdCategoria());
                dtoCategoria.setNombreCategoria(categoria.getNombreCategoria());
                listaDtoCategoria.add(dtoCategoria);

                dtoNegocio.setListaCategorias(listaDtoCategoria);
            }
            listaNegociosDTO.add(dtoNegocio);
        }

        return listaNegociosDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerPedidosUsuario", method = RequestMethod.GET)
    public List<DtoDetallePedido> obtenerPedidosUsuario(@RequestBody int idUsuario){
        List<Pedido> listaPedidosUsuario = usuarioService.obtenerPedidosUsuario(idUsuario);
        List<DtoDetallePedido> listaDetallesPedidoDto = new ArrayList<DtoDetallePedido>();

        for(Pedido pedido : listaPedidosUsuario){
            for(DetallePedido detalle : pedido.getDetallePedidos()){
                DtoDetallePedido detallePedidoDto = new DtoDetallePedido();
                detallePedidoDto.setNombrePlato(detalle.getPlato().getNombrePlato());
                detallePedidoDto.setCantidadDetalle(detalle.getCantidadDetalle());
                listaDetallesPedidoDto.add(detallePedidoDto);
            }
        }

        return listaDetallesPedidoDto;
    }

}
