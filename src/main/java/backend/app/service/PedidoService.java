package backend.app.service;

import backend.app.dao.DaoPedido;
import backend.app.model.Pedido;

import java.util.List;

/**
 * Created by Pablo on 20/06/2015.
 */
public class PedidoService {
    DaoPedido daoPedido;

    public List<Pedido> obtenerPedidos(){
        daoPedido = new DaoPedido();
        return daoPedido.obtener();
    }
    public boolean guardarPedido(Pedido pedido){
        daoPedido = new DaoPedido();
        return daoPedido.guardarPedido(pedido);
    }
    //falta obtener platos mandando el menu
}
