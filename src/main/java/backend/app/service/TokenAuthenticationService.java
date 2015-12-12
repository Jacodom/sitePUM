package backend.app.service;

import backend.app.model.Usuario;
import backend.app.security.TokenHandler;
import backend.app.security.UserAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jacobo on 12/12/2015.
 */
public class TokenAuthenticationService {

        private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

        private final TokenHandler tokenHandler;

        public TokenAuthenticationService(String secret, UsuarioService userService) {
            tokenHandler = new TokenHandler(secret, userService);
        }

        public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final Usuario user = authentication.getDetails();
            response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
        }

        public UserAuthentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final Usuario user = tokenHandler.parseUserFromToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
            return null;
        }
}
