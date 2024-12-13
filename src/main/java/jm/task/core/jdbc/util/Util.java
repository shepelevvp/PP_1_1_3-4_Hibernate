package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.ha.MultiHostMySQLConnection;

import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static SessionFactory sessionFactory;

    private static final String HOST = "jdbc:mysql://localhost:3306/newdbkata";
    private static final String HOSTHIBER = "jdbc:mysql://localhost:3306/newdbkata?useSSL=false&amp;serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Rootdbpas!1513";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            System.out.println("Соединение установлено");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка соединения");
        }
        return connection;
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, HOST);
                settings.put(Environment.USER, USERNAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Соединение hipernate установлено");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
