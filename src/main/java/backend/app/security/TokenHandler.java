package backend.app.security;

import backend.app.model.Usuario;
import backend.app.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.User;

/**
 * Created by Jacobo on 12/12/2015.
 */
public final class TokenHandler {
    private final String secret;
    private final UsuarioService userService;

    public TokenHandler(String secret, UsuarioService userService) {
        this.secret = secret;
        this.userService = userService;
    }

    public Usuario parseUserFromToken(String token) {
        String username = Jwts.parser()
        .setSigningKey(secret)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
        return userService.obtenerUsuarioUsername(username);
    }

    public String createTokenForUser(Usuario user) {
        return Jwts.builder()
        .setSubject(user.getUsernameUsuario())
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
    }
}
