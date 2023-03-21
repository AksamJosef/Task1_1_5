package jm.task.core.jdbc.util;

//import java.lang.module.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Users";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "rootroot";


    // Hibernate

    public static SessionFactory getSession() {
        return new Configuration()
                .setProperty("hibernate.connection.url", DB_URL)
                .setProperty("hibernate.connection.driver_class", DB_DRIVER)
                .setProperty("hibernate.connection.username", DB_USERNAME)
                .setProperty("hibernate.connection.password", DB_PASSWORD)
//                .setProperty("hibernate.current_session_context_class", "tread")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.show_sql", "true")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }


    // JDBC ----------

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
