package backend.app.hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by Pablo on 20/06/2015.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    private static Configuration cfg;

    public static SessionFactory obtenerInstanciaSesion() {
        try {
            if (sessionFactory == null) {
                cfg = new Configuration();
                cfg.configure();
                serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();

                sessionFactory = cfg.buildSessionFactory(serviceRegistry);
            }else{
                return sessionFactory;
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }


        return sessionFactory;
    }
}
