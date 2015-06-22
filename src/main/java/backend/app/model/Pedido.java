package backend.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jacobo on 20/06/2015.
 */

@Entity
@Table(name = "pedidos")
@NamedQuery(name = "Pedido.findAll",query = "select p FROM Pedido p")

public class Pedido extends BaseModelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    @Column(name = "pagacon_pedido")
    private BigDecimal pagaconPedido;

    @Column(name = "total_pedido")
    private BigDecimal totalPedido;

    @Column(name = "direccion_pedido")
    private String direccionPedido;

    @Column(name = "estado_pedido")
    private String estadoPedido;

    @OneToMany(mappedBy = "pedido")
    private Set<DetallePedido> detallePedidos = new HashSet<DetallePedido>();

    @ManyToOne()
    private Negocio negocio;

    @ManyToOne()
    private Usuario usuario;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public BigDecimal getPagaconPedido() {
        return pagaconPedido;
    }

    public void setPagaconPedido(BigDecimal pagaconPedido) {
        this.pagaconPedido = pagaconPedido;
    }

    public BigDecimal getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }

    public String getDireccionPedido() {
        return direccionPedido;
    }

    public void setDireccionPedido(String direccionPedido) {
        this.direccionPedido = direccionPedido;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Set<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(Set<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
