package backend.config.appConfig;

import backend.app.filters.StatelessAuthenticationFilter;
import backend.app.service.TokenAuthenticationService;
import backend.app.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Jacobo on 12/12/2015.
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

    private final UsuarioService userService;
    private final TokenAuthenticationService tokenAuthenticationService;

    public AppSecurityConfig() {
        super(true);
        this.userService = new UsuarioService();
        tokenAuthenticationService = new TokenAuthenticationService("jacobo", userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .exceptionHandling().and()
        .anonymous().and()
        .servletApi().and()
        .headers().cacheControl().and()
        .authorizeRequests()

        // Allow anonymous resource requests
        .antMatchers("/").permitAll()
        .antMatchers("/favicon.ico").permitAll()
        .antMatchers("**/*.html").permitAll()
        .antMatchers("**/*.css").permitAll()
        .antMatchers("**/*.js").permitAll()

         // Allow anonymous logins
        .antMatchers("/auth/**").permitAll()

        //Allow elegir negocio data
        .antMatchers("/elegirNegocio/**").permitAll()

        //Allow some data from anonymous pedidos
        .antMatchers("/gestionarPedido/obtenerNegocio/**").permitAll()
        .antMatchers("/gestionarPedido/obtenerCategoriasNegocio/**").permitAll()
        .antMatchers("/gestionarPedido/obtenerMenuActivo/**").permitAll()
        .antMatchers("/gestionarPedido/obtenerPlatosMenu/**").permitAll()
        .antMatchers("/resources/css/**").permitAll()
        .antMatchers("/resources/js/**").permitAll()
        .antMatchers("/resources/**").permitAll()
        .antMatchers("/views/**").permitAll()

        // All other request need to be authenticated
        .anyRequest().authenticated().and()

        // Custom Token based authentication based on the header previously given to the client
        .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService),
                UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Bean
    public TokenAuthenticationService tokenAuthenticationService() {
       return tokenAuthenticationService;
    }

}
