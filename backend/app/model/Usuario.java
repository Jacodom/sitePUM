package app.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pablo on 19/06/2015.
 */

@Entity
@Table(name = "usuarios")
@NamedQuery(name = "Usuario.findAll",query = "SELECT u FROM Usuario u")
public class Usuario extends BaseModelEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(name = "username_usuario")
    private String usernameUsuario;

    @Column(name = "password_usuario")
    private String passwordUsuario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "apellido_usuario")
    private String apellidoUsuario;

    @Column(name = "direccion_usuario")
    private String direccionUsuario;

    @Column(name = "email_usuario")
    private String emailUsuario;

    @Column(name = "telefono_usuario")
    private String telefonoUsuario;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "usuario")
    private Set<Pedido> Pedidos = new HashSet<Pedido>();

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsernameUsuario() {
        return usernameUsuario;
    }

    public void setUsernameUsuario(String usernameUsuario) {
        this.usernameUsuario = usernameUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public Set<Pedido> getPedidos() {
        return Pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        Pedidos = pedidos;
    }
}
