package app.service;

import app.dao.DaoUsuario;
import app.model.Pedido;
import app.model.Usuario;

import java.util.List;

/**
 * Created by Pablo on 20/06/2015.
 */
public class UsuarioService {
    DaoUsuario daoUsuario;

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
}
