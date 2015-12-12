package backend.app.service;

import backend.app.dao.DaoUsuario;
import backend.app.model.Pedido;
import backend.app.model.Usuario;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * Created by Pablo on 20/06/2015.
 */
public class UsuarioService {
    private DaoUsuario daoUsuario;

    public Usuario obtenerUsuario(int idUsuario){
        daoUsuario = new DaoUsuario();
        for(Usuario usuario: daoUsuario.obtener())
            if(usuario.getIdUsuario()==idUsuario)
                return usuario;
        return null;
    }
    public List<Usuario> obtenerUsuarios(){
        daoUsuario = new DaoUsuario();
        return daoUsuario.obtener();
    }
    public List<Pedido> obtenerPedidosUsuario(int idUsuario){
        daoUsuario = new DaoUsuario();
        return daoUsuario.obtenerPedidosUsuario(idUsuario);
    }


    public Usuario obtenerUsuarioUsername(String username) {
        daoUsuario = new DaoUsuario();
        for(Usuario usuario: daoUsuario.obtener())
            if(usuario.getUsernameUsuario()==username)
                return usuario;
        return null;
    }
}
