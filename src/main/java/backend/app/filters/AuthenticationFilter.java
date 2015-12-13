package backend.app.filters;

import backend.app.dto.DtoUser;
import backend.app.model.Usuario;
import backend.app.security.TokenHandler;
import backend.app.service.UsuarioService;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by Jacobo on 13/12/2015.
 */
public class AuthenticationFilter extends GenericFilterBean {
    
    private  TokenHandler tokenHandler;
    private UsuarioService usuarioService;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        usuarioService = new UsuarioService();
        tokenHandler = new TokenHandler(usuarioService);

        String token = getToken((HttpServletRequest) request);

        //String token =( (HttpServletRequest) request).getHeader("tokenHeader");

        String username = tokenHandler.getUsernameFromToken(token);

        try {
            if(tokenHandler.validateToken(token, usuarioService.obtenerUsuarioUsername(username))) {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Unauthorized: Token validation failed", e);
        }


    }


    private String getToken(HttpServletRequest httpRequest) throws ServletException {
        String token = null;
        final String authorizationHeader = httpRequest.getHeader("authorization");
        if (authorizationHeader == null) {
            throw new ServletException("Unauthorized: No Authorization header was found");
        }

        String[] parts = authorizationHeader.split(" ");
        if (parts.length != 2) {
            throw new ServletException("Unauthorized: Format is Authorization: Bearer [token]");
        }

        String scheme = parts[0];
        String credentials = parts[1];

        Pattern pattern = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(scheme).matches()) {
            token = credentials;
        }
        return token;
    }
}
