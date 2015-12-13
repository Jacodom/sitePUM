package backend.config;



import backend.app.filters.AuthenticationFilter;
import backend.config.appConfig.AppSecurityConfig;
import backend.config.appConfig.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;


/**
 * Created by Jacobo on 20/06/2015.
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

/*    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new AuthenticationFilter()};
    }*/

}
