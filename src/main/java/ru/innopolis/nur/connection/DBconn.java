package ru.innopolis.nur.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by nur on 04.11.16.
 */
public class DBconn {

    private static Logger logger = Logger.getLogger(DBconn.class);

    /**
     * Connection conn
     */
    private static Connection conn;

    /**
     * Connects to database, and returns the connection
     * @return
     */
    public static Connection getConnection() {
        if( conn != null ) {
            return conn;
        }
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/testTommy";
            String user = "admin";
            String password = "mysqladmin";

            try {
                Class.forName(driver);
                logger.info("Драйвер БД загружен");
            } catch (Exception e) {
                logger.error("Ошибка загрузки БД драйвера" +e);
                e.printStackTrace();
            }

            conn = DriverManager.getConnection( url, user, password );
        } catch (SQLException e) {
            logger.error("Ошибка соединения к БД" +e);
            e.printStackTrace();
        }

        logger.info("Установлено соединение с БД");
        return conn;
    }

    /**
     * Closes the connection
     * @param toBeClosed
     */
    public static void closeConnection( Connection toBeClosed ) {
        if( toBeClosed == null )
            return;
        try {
            toBeClosed.close();
            logger.info("Cоединение с БД закрыто");
        } catch (SQLException e) {
            logger.error("Ошибка закрытия соединения с БД" + e);
            e.printStackTrace();
        } finally{
            try{
                if(toBeClosed!=null)
                    toBeClosed.close();
                logger.warn("Соединение с БД закрыто в файнал");
            }catch(SQLException se){
                logger.error("Ошибка закрытия соединения с БД в файнал" + se);
                se.printStackTrace();
            }
        }
    }

}