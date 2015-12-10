package backend.app.model;


import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Pablo on 19/06/2015.
 */

@Entity
@Table(name = "detallePedidos")
@NamedQuery(name = "DetallePedido.findAll",query = "SELECT d FROM DetallePedido d")
public class DetallePedido extends BaseModelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_detallePedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalle;

    @Column(name = "aclaracion_detallePedido")
    private String aclaracionDetalle;

    @Column(name = "cantidad_detallePedido")
    private int cantidadDetalle;

    @Column(name = "subtotal_detallePedido")
    private BigDecimal subtotalDetalle;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Plato plato;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pedido pedido;

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
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

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
