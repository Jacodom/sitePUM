package backend.app.controller;

import backend.app.dto.DtoUser;
import backend.app.model.Usuario;
import backend.app.security.TokenHandler;
import backend.app.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Jacobo on 13/12/2015.
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthController {
    private UsuarioService usuarioService = new UsuarioService();
    private TokenHandler tokenUtils = new TokenHandler(usuarioService);


    @ResponseBody
    @RequestMapping(value = "/sessions/create", method = RequestMethod.POST)
    public
    DtoUser authenticationRequest(@RequestBody DtoUser user) {

        Usuario usuarioBD = usuarioService.obtenerUsuarioUsername(user.getUsernameUsuario());

        if (usuarioBD != null) {
            if (usuarioBD.getPasswordUsuario().equals(user.getPasswordUsuario())) {
                //setear todos los atributos
                user.setIdUsuario(usuarioBD.getIdUsuario());
                user.setUsernameUsuario(usuarioBD.getUsernameUsuario());
                user.setNombreUsuario(usuarioBD.getNombreUsuario()  );
                user.setApellidoUsuario(usuarioBD.getApellidoUsuario());
                user.setEmailUsuario(usuarioBD.getEmailUsuario());
                user.setTelefonoUsuario(usuarioBD.getTelefonoUsuario());
                user.setDireccionUsuario(usuarioBD.getDireccionUsuario());
                String token = tokenUtils.createTokenForUser(user);
                user.setToken(token);
            }
        }

        // Return the token
        return user;
    }
}
