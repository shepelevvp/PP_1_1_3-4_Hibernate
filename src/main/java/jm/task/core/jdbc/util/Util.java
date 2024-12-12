package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.ha.MultiHostMySQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String HOST = "jdbc:mysql://localhost:3306/newdbkata";
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

}
