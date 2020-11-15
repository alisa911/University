package university.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateUtil {

    public static SessionFactory getSessionFactory() {
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder();
            registry.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = registry.build();

            return configuration.buildSessionFactory(serviceRegistry);
    }

    public static Session beginSession(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        return session;
    }
}
