package backend.app.controller;

import backend.app.dto.DtoNewPedido;
import backend.app.model.Pedido;
import backend.app.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Pablo on 11/12/2015.
 */
@Controller
@RequestMapping("/reportes")
public class ReportesController {

    private PedidoService pedidoService = new PedidoService();

//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value = "/obtenerPedidoTemporada", method = RequestMethod.GET)
//    public List<DtoNewPedido> obtenerPedidosTemporada (@RequestParam int Ano){
//        List<Pedido> listaPedidos = pedidoService.obtenerPedidos();
//    }
}

