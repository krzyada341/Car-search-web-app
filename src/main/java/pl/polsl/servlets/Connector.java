package pl.polsl.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;

/**
 * Class managing the connection with the database
 *
 * @author Krzysztof Adamczyk
 * @version 1.0
 */
public final class Connector {
    
    /**
     * Connection handle
     */
    private static Connection connection = null;
    
    /**
     * Creates the connection to the database
     * 
     * @param context is the server context with its parameters
     * @return true if succeeded, null if unable to connect
     */
    public static boolean setConnection(ServletContext context) {
        if (connection == null) {
            try {
                // loading the driver
                Class.forName(context.getInitParameter("driver"));
                // creating connection with the database
                connection = DriverManager.getConnection(context.getInitParameter("url"),
                        context.getInitParameter("login"), context.getInitParameter("password"));
            } catch (ClassNotFoundException | SQLException | NullPointerException e) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Gets the connection
     * 
     * @return the connection (may be null if didn't initialized)
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Disconnecting from database.
     */
    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
