package model;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Pablo on 19/06/2015.
 */

@Entity
@Table(name ="categorias")
@NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")

public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_categoria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;

    @Column(name = "nombre_categoria")
    private String nombreCategoria;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "categoria")
    private Set<Plato> platos = new HashSet<Plato>();

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
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

        Categoria categoria = (Categoria) o;

        if (platos != null ? !platos.equals(categoria.platos) : categoria.platos != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return platos != null ? platos.hashCode() : 0;
    }
}
