package backend.app.controller;


import backend.app.model.Categoria;
import backend.app.model.Negocio;
import backend.app.model.Pedido;
import backend.app.service.CategoriaService;
import backend.app.service.NegocioService;
import backend.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerCategorias", method = RequestMethod.GET)
    public List<Categoria> obtenerCategorias(){
        List<Categoria> listaCategorias = categoriaService.obtenerCategorias();
        return listaCategorias;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerNegocios", method = RequestMethod.GET)
    public List<Negocio> obtenerNegocios(){
        List<Negocio> listaNegocios = negocioService.obtenerNegocios();
        return listaNegocios;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/obtenerPedidosUsuario", method = RequestMethod.GET)
    public List<Pedido> obtenerPedidosUsuario(@RequestBody int idUsuario){
        List<Pedido> listaPedidosUsuario = usuarioService.obtenerPedidosUsuario(idUsuario);
        return listaPedidosUsuario;
    }

}
