package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


/**
 * Created by Pablo on 19/06/2015.
 */
@Entity
@Table(name = "menues")
@NamedQuery(name = "Menu.findAll",query = "SELECT m from Menu m")
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_menu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMenu;

    @Column(name = "nombre_menu")
    private String nombreMenu;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (platos != null ? !platos.equals(menu.platos) : menu.platos != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return platos != null ? platos.hashCode() : 0;
    }
}
