/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package convoy.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Klassens formål: At forbinde til Convoy's MySQL database og kommunikere med denne
 * @author Jon
 */
public class Connecter {
    
    private final String
            server      = "mysql.carlend.net",  // database-serveren
            database    = "carlend_net_db";  //"jdbcdatabase", // navnet paa din database = dit studienummer
//            username    = "", // brugernavn
//            password    = ""; // password til databasen
    
    public final String tabelNavn   = "convoy_db"; // tabelnavn i databasen som indeholder data
    
    private final int port = 3306;
    private Connection conn;
    private Statement stm;
    
    /**
     * 
     * @param username Brugernavn til databasen
     * @param password Password til databasen
     * @return True hvis forbindelsen er oprettet korrekt
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException 
     */
    public boolean connect(String username, String password) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        conn	= this.connectToDatabase("jdbc:mysql://"+server+":"+port+"/"+database, username, password);
        stm	= conn.createStatement();
        return this.isConnected();
    }
    
    private Connection connectToDatabase(String url, String username, String password)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException, SQLException{
        // call the driver class' no argument constructor
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        // get Connection-object via DriverManager
        return (Connection) DriverManager.getConnection(url, username, password);
    }
    
    private boolean isConnected(){
        try {
            ResultSet rs = this.doQuery("SELECT * from " + tabelNavn);
            if(rs.next()){
                System.out.println("Forbindelse til database er oprettet. Tabel: " + tabelNavn + " indeholder data.");
                return true;
            } else {
                throw new SQLException();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Forbindelse til databasen fejlede.");
            return false;
        }
    }
    
    public ResultSet doQuery(String cmd) throws SQLException{
        return stm.executeQuery(cmd);
    }
    
    public int doUpdate(String cmd) throws SQLException {
        return stm.executeUpdate(cmd);
    }
    
}
