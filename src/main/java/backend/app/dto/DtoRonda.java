package backend.app.dto;

import java.util.List;

/**
 * Created by Jacobo on 13/03/2016.
 */
public class DtoRonda {

    private int idRonda;
    private float vuelto;
    private float recaudacion;
    private DtoCadete cadete;
    private List<DtoNewPedido> pedidos;

    public int getIdRonda() {
        return idRonda;
    }

    public void setIdRonda(int idRonda) {
        this.idRonda = idRonda;
    }

    public float getVuelto() {
        return vuelto;
    }

    public void setVuelto(float vuelto) {
        this.vuelto = vuelto;
    }

    public float getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(float recaudacion) {
        this.recaudacion = recaudacion;
    }

    public DtoCadete getCadete() {
        return cadete;
    }

    public void setCadete(DtoCadete cadete) {
        this.cadete = cadete;
    }

    public List<DtoNewPedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<DtoNewPedido> pedidos) {
        this.pedidos = pedidos;
    }
}
