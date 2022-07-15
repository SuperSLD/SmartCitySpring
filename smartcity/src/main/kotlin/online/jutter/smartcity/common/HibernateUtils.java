package online.jutter.smartcity.common;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import static org.hibernate.boot.registry.StandardServiceRegistryBuilder.destroy;

public class HibernateUtils {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            registry = new StandardServiceRegistryBuilder().configure().build();

            MetadataSources sources = new MetadataSources(registry);
            Metadata metadata = sources.getMetadataBuilder().build();

            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }

        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) destroy(registry);
    }
}
