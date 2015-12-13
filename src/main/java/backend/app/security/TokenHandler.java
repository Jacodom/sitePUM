package backend.app.security;

import backend.app.dto.DtoUser;
import backend.app.model.Usuario;
import backend.app.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Jacobo on 12/12/2015.
 */
public final class TokenHandler {
    private final String secret= "jacobo";
    private final UsuarioService userService;

    public TokenHandler(UsuarioService userService) {
        this.userService = userService;
    }



    public String createTokenForUser(DtoUser user) {
        return Jwts.builder()
        .setSubject(user.getUsernameUsuario())
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            username = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public boolean validateToken(String token, Usuario user) {
        final String username = getUsernameFromToken(token);
        return username.equals(user.getUsernameUsuario());
    }
}
