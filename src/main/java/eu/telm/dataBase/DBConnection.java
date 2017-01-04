package eu.telm.dataBase;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by kasia on 10.12.16.
 */
public class DBConnection {

    Properties prop = new Properties();
    InputStream input = null;

    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String DB_URL;

    //  Database credentials
    String USER ;
    String PASS;
    Connection conn = null;
    Statement stmt = null;

    public DBConnection(){
        try {

            //input = new FileInputStream("application.properties");
            String filename = "application.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if(input==null){
                System.out.println("Brak pliku " + filename);
                return;
            }
            // load a properties file
            prop.load(input);
            System.out.println(prop.getProperty("spring.datasource.url"));
            System.out.println(prop.getProperty("spring.datasource.username"));
            System.out.println(prop.getProperty("spring.datasource.password"));

            DB_URL = prop.getProperty("spring.datasource.url");
            USER = prop.getProperty("spring.datasource.username");
            PASS = prop.getProperty("spring.datasource.password");



            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();



        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Statement getStmt(){
        return stmt;
    }
    public Connection getConn(){return conn;}


}

