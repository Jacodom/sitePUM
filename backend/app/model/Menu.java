package app.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jacobo on 20/06/2015.
 */

@Entity
@Table(name = "menues")
@NamedQuery(name = "Menu.findAll",query = "SELECT m from Menu m")
public class Menu extends BaseModelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_menu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMenu;

    @Column(name = "nombre_menu")
    private String nombreMenu;

    @Column(name = "estado")
    private Boolean estadoMenu;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Negocio negocio;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "menu")
    private Set<Plato> platos = new HashSet<Plato>();

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public Boolean getEstadoMenu() {
        return estadoMenu;
    }

    public void setEstadoMenu(Boolean estadoMenu) {
        this.estadoMenu = estadoMenu;
    }


    public Negocio getNegocio() {
        return negocio;
    }

    public void setNegocio(Negocio negocio) {
        this.negocio = negocio;
    }

    public Set<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(Set<Plato> platos) {
        this.platos = platos;
    }

}
