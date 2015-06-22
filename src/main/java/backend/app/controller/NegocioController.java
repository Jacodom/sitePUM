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

    @Autowired
    private CategoriaService categoriaService;
    private NegocioService negocioService;
    private UsuarioService usuarioService;

    //obtener Categorias
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> obtenerCategorias(){
        List<Categoria> listaCategorias = categoriaService.obtenerCategorias();
        return listaCategorias;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<Negocio> obtenerMegocio(){
        List<Negocio> listaNegocio = negocioService.obtenerNegocios();
        return listaNegocio;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<Pedido> obtenerPedidosUsuario(@RequestBody int idUsuario){
        List<Pedido> listaPedidosUsuario = usuarioService.obtenerPedidosUsuario(idUsuario);
        return listaPedidosUsuario;
    }

}
