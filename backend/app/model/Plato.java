package model;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pablo on 19/06/2015.
 */
@Entity
@Table(name = "platos")
@NamedQuery(name = "plato.findAll",query = "SELECT p from Plato p")
public class Plato implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_plato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlato;

    @Column(name = "nombre_plato")
    private String nombrePlato;

    @Column(name = "descripcion_plato")
    private String descPlato;

    @Column (name = "precio_plato")
    private BigDecimal precioPlato;

    @Column(name = "coccion_plato")
    private int coccionPlato;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Menu menu;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "plato")
    private Set<DetallePedido> detallePedidos = new HashSet<DetallePedido>();

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public String getDescPlato() {
        return descPlato;
    }

    public void setDescPlato(String descPlato) {
        this.descPlato = descPlato;
    }

    public BigDecimal getPrecioPlato() {
        return precioPlato;
    }

    public void setPrecioPlato(BigDecimal precioPlato) {
        this.precioPlato = precioPlato;
    }

    public int getCoccionPlato() {
        return coccionPlato;
    }

    public void setCoccionPlato(int coccionPlato) {
        this.coccionPlato = coccionPlato;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(Set<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plato plato = (Plato) o;

        if (!detallePedidos.equals(plato.detallePedidos)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return detallePedidos.hashCode();
    }
}
