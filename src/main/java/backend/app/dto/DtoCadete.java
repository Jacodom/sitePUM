package backend.app.dto;

/**
 * Created by Jacobo on 13/03/2016.
 */
public class DtoCadete {

    private int idCadete;
    private String estado;
    private String nombre;
    private String apellido;
    private String telefono;

    public int getIdCadete() {
        return idCadete;
    }

    public void setIdCadete(int idCadete) {
        this.idCadete = idCadete;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
