package config.root;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jacobo on 20/06/2015.
 */

@Configuration
@ComponentScan({"app.services","app.dao",})
public class RootContextConfig {
}
