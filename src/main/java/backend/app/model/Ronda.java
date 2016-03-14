package backend.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jacobo on 13/03/2016.
 */
@Entity
@Table(name = "rondas")
public class Ronda extends BaseModelEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_ronda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRonda;

    @Column(name = "vuelto_ronda")
    private float vueltoRonda;

    @Column(name = "recaudacion_ronda")
    private float recaudacionRonda;

    @OneToMany(mappedBy = "ronda", cascade = CascadeType.ALL)
    public Set<Pedido> pedidos = new HashSet<Pedido>();

    @ManyToOne()
    private Cadete cadete;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getIdRonda() {
        return idRonda;
    }

    public void setIdRonda(int idRonda) {
        this.idRonda = idRonda;
    }

    public float getVueltoRonda() {
        return vueltoRonda;
    }

    public void setVueltoRonda(float vueltoRonda) {
        this.vueltoRonda = vueltoRonda;
    }

    public float getRecaudacionRonda() {
        return recaudacionRonda;
    }

    public void setRecaudacionRonda(float recaudacionRonda) {
        this.recaudacionRonda = recaudacionRonda;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Cadete getCadete() {
        return cadete;
    }

    public void setCadete(Cadete cadete) {
        this.cadete = cadete;
    }
}
