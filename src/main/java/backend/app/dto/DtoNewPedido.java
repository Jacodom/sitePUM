package backend.app.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Jacobo on 22/06/2015.
 */
public class DtoNewPedido {



    private int idPedido;
    private String direccionPedido;
    private BigDecimal totalPedido;
    private BigDecimal pagaconPedido;
    private String estadoPedido;
    private Date fechaPedido;
    private int idUsuario;
    private int idNegocio;
    private List<DtoDetallePedido> listaDetalles;
    private String nombreNegocio;
    private  String nombreUsuario;
    private String telefonoUsuario;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getDireccionPedido() {
        return direccionPedido;
    }

    public void setDireccionPedido(String direccionPedido) {
        this.direccionPedido = direccionPedido;
    }

    public BigDecimal getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }

    public BigDecimal getPagaconPedido() {
        return pagaconPedido;
    }

    public void setPagaconPedido(BigDecimal pagaconPedido) {
        this.pagaconPedido = pagaconPedido;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(int idNegocio) {
        this.idNegocio = idNegocio;
    }

    public List<DtoDetallePedido> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DtoDetallePedido> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }
}
