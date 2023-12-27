package java_mysql;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/projetlaye";

    private static final String DATABASE_USER = "root";


    private static final String DATABASE_PASSWORD = "";



    public static java.sql.Connection getConnection() {
        java.sql.Connection connection = null;
        try{
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("la connexion à la base de données est etablie : ");
        } catch (SQLException e) {
            System.out.println("la connexion à la base de données a echoué : " + e.getMessage());
        }
        return connection;
    }
}
