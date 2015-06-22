package backend.app.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jacobo on 20/06/2015.
 */
@Entity
@Table(name = "negocios")
@NamedQuery(name = "Negocio.findall",query = "select n from Negocio n")
public class Negocio extends BaseModelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_negocio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNegocio;

    @Column(name = "nombre_negocio")
    private String nombreNegocio;

    @Column(name = "domicilio_negocio")
    private String domicilioNegocio;

    @Column(name = "telefono_negocio")
    private String telefonoNegocio;

    @Column (name ="logo_negocio")
    private String logoNegocio;

    @OneToMany(mappedBy = "negocio")
    private Set<Menu> menues = new HashSet<Menu>();

    @OneToMany(mappedBy = "negocio")
    private Set<Pedido> pedidos = new HashSet<Pedido>();

    public int getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(int idNegocio) {
        this.idNegocio = idNegocio;
    }

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public String getDomicilioNegocio() {
        return domicilioNegocio;
    }

    public void setDomicilioNegocio(String domicilioNegocio) {
        this.domicilioNegocio = domicilioNegocio;
    }

    public String getTelefonoNegocio() {
        return telefonoNegocio;
    }

    public void setTelefonoNegocio(String telefonoNegocio) {
        this.telefonoNegocio = telefonoNegocio;
    }

    public String getLogoNegocio() {
        return logoNegocio;
    }

    public void setLogoNegocio(String logoNegocio) {
        this.logoNegocio = logoNegocio;
    }

    public Set<Menu> getMenues() {
        return menues;
    }

    public void setMenues(Set<Menu> menues) {
        this.menues = menues;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
