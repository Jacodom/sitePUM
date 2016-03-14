package backend.app.service;

import backend.app.dao.DaoPedido;
import backend.app.dto.DtoNewPedido;
import backend.app.model.Pedido;

import java.util.List;

/**
 * Created by Pablo on 20/06/2015.
 */
public class PedidoService {
    private DaoPedido daoPedido;

    public List<Pedido> obtenerPedidos(){
        daoPedido = new DaoPedido();
        return daoPedido.obtener();
    }
    public boolean guardarPedido(Pedido pedido){
        daoPedido = new DaoPedido();
        return daoPedido.guardarPedido(pedido);
    }
    public List<Pedido> obtenerPedidosTemporada(int anio){
        daoPedido = new DaoPedido();
        return daoPedido.obtenerPedidosTemporada();
    }

    public Pedido obtenerPedido(int idPedido){
        daoPedido = new DaoPedido();
        for(Pedido pedido: daoPedido.obtener())
            if(pedido.getIdPedido()==idPedido)
                return pedido;
        return null;
    }
}