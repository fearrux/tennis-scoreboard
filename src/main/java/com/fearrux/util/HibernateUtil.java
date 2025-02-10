package com.fearrux.util;

import com.fearrux.entity.Player;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
    public static SessionFactory get() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Player.class);
        configuration.addAnnotatedClass(Math.class);
        configuration.configure();
        return configuration.buildSessionFactory();
    }
}
