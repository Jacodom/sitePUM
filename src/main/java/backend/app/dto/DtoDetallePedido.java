package backend.app.dto;

import backend.app.service.PlatoService;

import java.math.BigDecimal;

/**
 * Created by Jacobo on 22/06/2015.
 */
public class DtoDetallePedido {

    private String aclaracionDetalle;
    private int cantidadDetalle;
    private BigDecimal subtotalDetalle;
    private int idPlato;
    private String nombrePlato;

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getAclaracionDetalle() {
        return aclaracionDetalle;
    }

    public void setAclaracionDetalle(String aclaracionDetalle) {
        this.aclaracionDetalle = aclaracionDetalle;
    }

    public int getCantidadDetalle() {
        return cantidadDetalle;
    }

    public void setCantidadDetalle(int cantidadDetalle) {
        this.cantidadDetalle = cantidadDetalle;
    }

    public BigDecimal getSubtotalDetalle() {
        return subtotalDetalle;
    }

    public void setSubtotalDetalle(BigDecimal subtotalDetalle) {
        this.subtotalDetalle = subtotalDetalle;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }


}
