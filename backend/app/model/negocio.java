package model;
import java.io.Serializable;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
/**
 * Created by Pablo on 19/06/2015.
 */
@Entity
@Table(name = "negocios")
@NamedQuery(name = "Negocio.findall",query = "select n from Negocio n")
public class Negocio implements Serializable{
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

    @Column(name = "logo_negocio")
    private String logoNegocio;



    // relacion de negocio a menu, uno a muchos
    @OneToMany(cascade = CascadeType.ALL,fetch =FetchType.EAGER,mappedBy = "negocio")
    private Set<Menu> menues= new HashSet<Menu>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "negocio")
    private Set<Pedido> Pedidos = new HashSet<Pedido>();



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
        return Pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        Pedidos = pedidos;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Negocio negocio = (Negocio) o;

        if (Pedidos != null ? !Pedidos.equals(negocio.Pedidos) : negocio.Pedidos != null) return false;
        if (menues != null ? !menues.equals(negocio.menues) : negocio.menues != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = menues != null ? menues.hashCode() : 0;
        result = 31 * result + (Pedidos != null ? Pedidos.hashCode() : 0);
        return result;
    }
}
