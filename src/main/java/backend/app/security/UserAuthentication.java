package backend.app.security;

import backend.app.model.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by Jacobo on 12/12/2015.
 */
public class UserAuthentication {

    private final Usuario user;
    private boolean authenticated = true;

    public UserAuthentication(Usuario user) {
        this.user = user;
    }

    public String getName() {
        return user.getNombreUsuario();
    }


    public Object getCredentials() {
        return user.getPasswordUsuario();
    }


    public Usuario getDetails() {
        return user;
    }


    public Object getPrincipal() {
        return user.getUsernameUsuario();
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
       this.authenticated = authenticated;
    }
}
