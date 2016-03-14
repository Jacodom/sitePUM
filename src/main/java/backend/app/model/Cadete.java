package backend.app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jacobo on 13/03/2016.
 */
@Entity
@Table(name = "cadetes")
public class Cadete extends BaseModelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_cadete")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCadete;

    @Column(name = "nombre_cadete")
    private String nombreCadete;

    @Column(name = "apellido_cadete")
    private String apellidoCadete;

    @Column(name = "estado_cadete")
    private String estadoCadete;

    @Column(name = "telefono")
    private String telefonoCadete;

    @OneToMany(mappedBy = "cadete", cascade = CascadeType.ALL)
    public Set<Ronda> rondas = new HashSet<Ronda>();

    public Set<Ronda> getRondas() {
        return rondas;
    }

    public void setRondas(Set<Ronda> rondas) {
        this.rondas = rondas;
    }

    public int getIdCadete() {
        return idCadete;
    }

    public void setIdCadete(int idCadete) {
        this.idCadete = idCadete;
    }

    public String getNombreCadete() {
        return nombreCadete;
    }

    public void setNombreCadete(String nombreCadete) {
        this.nombreCadete = nombreCadete;
    }

    public String getApellidoCadete() {
        return apellidoCadete;
    }

    public void setApellidoCadete(String apellidoCadete) {
        this.apellidoCadete = apellidoCadete;
    }

    public String getEstadoCadete() {
        return estadoCadete;
    }

    public void setEstadoCadete(String estadoCadete) {
        this.estadoCadete = estadoCadete;
    }

    public String getTelefonoCadete() {
        return telefonoCadete;
    }

    public void setTelefonoCadete(String telefonoCadete) {
        this.telefonoCadete = telefonoCadete;
    }
}
