package backend.config.appConfig;


import backend.app.filters.AuthenticationFilter;
import backend.app.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Created by Jacobo on 12/12/2015.
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

    private final UsuarioService userService;


    //@Autowired
    //@Qualifier("userDetailsService")
    //private UserDetailsService userDetailsService;

    public AppSecurityConfig() {
        super(true);
        this.userService = new UsuarioService();
    }

    //@Autowired
    //private EntryPointUnauthorizedHandler unauthorizedHandler;


    //@Autowired
    //public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    //    authenticationManagerBuilder
    //            .userDetailsService(this.userDetailsService)
     //           .passwordEncoder(passwordEncoder());
    //}

/*    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public StatelessAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        StatelessAuthenticationFilter authenticationTokenFilter = new StatelessAuthenticationFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }*/

    @Override
    public void configure(WebSecurity web) throws Exception {
        /*http
                .csrf().disable()
        .exceptionHandling().and()
        .anonymous().and()
        .servletApi().and()
        .headers().cacheControl().and()
        .authorizeRequests()*/
        web.ignoring()
        // Allow anonymous resource requests
        .antMatchers("/")
        .antMatchers("/favicon.ico")
        .antMatchers("**/*.html")
        .antMatchers("**/*.css")
        .antMatchers("**/*.js")

         // Allow anonymous logins
        .antMatchers("/auth/**")

        //Allow elegir negocio data
        .antMatchers("/elegirNegocio/**")

        //Allow some data from anonymous pedidos
        .antMatchers("/gestionarPedido/obtenerNegocio/**")
        .antMatchers("/gestionarPedido/obtenerCategoriasNegocio/**")
        .antMatchers("/gestionarPedido/obtenerMenuActivo/**")
        .antMatchers("/gestionarPedido/obtenerPlatosMenu/**")
        .antMatchers("/resources/css/**")
        .antMatchers("/resources/js/**")
        .antMatchers("/resources/**")
        .antMatchers("/views/**");

        // All other request need to be authenticated
        //.anyRequest().authenticated().and()

        //Custom Token based authentication based on the header previously given to the client
        //.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/gestionarPedido/pedido/**").permitAll()
                .and().anonymous().and()
                .addFilterBefore(new AuthenticationFilter(), BasicAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }




}
