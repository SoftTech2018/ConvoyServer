/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package convoy.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klassens formål: At kommunikere med databasen,
 * oversætte ResultSet til Java objekter og Java metodekald til SQL statements
 * @author Jon
 */
public class SpotsDAO {
    
    private final Connecter con;
    
    public SpotsDAO(Connecter con){
        this.con = con;
    }
    
    /**
     * Henter alle spots i databasen
     * @return Alle spots der findes i DB
     * @throws SQLException
     */
    public ArrayList<Spot> getSpots() throws SQLException{
        ArrayList<Spot> spots = new ArrayList<>();
        ResultSet rs = con.doQuery("SELECT * from " + con.tabelNavn);
        while (rs.next()) {
            spots.add(new Spot(rs.getInt("id"), rs.getBoolean("adblue"), rs.getBoolean("food"), rs.getBoolean("wc"), rs.getBoolean("bed"), rs.getBoolean("bath"),rs.getBoolean("fuel"), rs.getBoolean("roadtrain"), Double.parseDouble(rs.getString("posLng")), Double.parseDouble(rs.getString("posLat")), rs.getString("description"), rs.getLong("lastUpdated"), rs.getBoolean("deleted")));
        }      
        return spots;
    }
    
    /**
     * Opret et givent spot
     * @param spot Det spot der skal oprettes. Spottets ID skal være null.
     * @return True hvis spottet blev oprettet. False hvis det ikke blev oprettet.
     * @throws SQLException
     */
    public boolean createSpot(Spot spot) throws SQLException{
        long time = System.currentTimeMillis();
        String cmd = "INSERT INTO " + con.tabelNavn +
                " (adblue, bath, bed, food, fuel, roadtrain, wc, description, posLat, posLng, lastUpdated, deleted)" +
                " VALUES (" + spot.isAddBlue() +
                ", " + spot.isBath() +
                ", " + spot.isBed() +
                ", " + spot.isFood() +
                ", " + spot.isFuel() +
                ", " + spot.isRoadtrain() +
                ", " + spot.isWc() +
                ", " + "\"" + spot.getName() + "\"" +
                ", " + spot.getLatidude() +
                ", " + spot.getLongitude() +
                ", " + time +
                ", " + false +
                ")";
        
        if(con.doUpdate(cmd) == 0 ){
            return false;
        }
        return true;
    }
    
    /**
     * Opdaterer et givent spot
     * @param spot Det spot der skal opdateres. Spottets ID bestemmer hvilket spot der opdateres i DB
     * @return True hvis et spot blev rettet. False hvis intet blev rettet
     * @throws SQLException
     */
    public boolean updateSpot(Spot spot) throws SQLException{
        String cmd = "UPDATE " + con.tabelNavn + 
                " SET adblue = " + spot.isAddBlue() + 
                ", bath = " + spot.isBath() +
                ", bed = " + spot.isBed() +
                ", food = " + spot.isBed() + 
                ", fuel = " + spot.isFuel() + 
                ", roadtrain = " + spot.isRoadtrain() +
                ", wc = " + spot.isWc() +
                ", description = " + "\"" + spot.getName() + "\"" +
                ", posLat = " + spot.getLatidude() +
                ", posLng = " + spot.getLongitude() + 
                ", lastUpdated = " + System.currentTimeMillis() +
                ", deleted = " + spot.isDeleted() +
                " WHERE id = " + spot.getId();
        if (con.doUpdate(cmd) == 0 ){
            return false;
        }
        return true;
    }
    
    /**
     * Slet et givent spot
     * @param spot Det spot der skal slettes. Spottets ID bestemmer hvilket spot der slettes i DB
     * @return True hvis et spot blev slettet. False hvis intet blev slettet
     * @throws SQLException
     */
    public boolean deleteSpot(Spot spot) throws SQLException{
        String cmd = "UPDATE " + con.tabelNavn + 
                " SET deleted = " + true + 
                " SET lastUpdated = " + System.currentTimeMillis() +
                " WHERE id = " + spot.getId();
        if (con.doUpdate(cmd) ==0 ){
            return false;
        }
        return true;
    }
    
    /**
     * Hent alle spots der er opdateret siden den defineret tid
     * @param time Tiden siden 01.01.1970 i ms
     * @return
     * @throws SQLException 
     */
    public ArrayList<Spot> getUpdatedSpots(long time) throws SQLException{
        ArrayList<Spot> spots = new ArrayList<>();
        
        ResultSet rs = con.doQuery("SELECT * from " + con.tabelNavn + " WHERE lastUpdated >= " + time);
        while (rs.next()) {
            spots.add(new Spot(rs.getInt("id"), rs.getBoolean("adblue"), rs.getBoolean("food"), rs.getBoolean("wc"), rs.getBoolean("bed"), rs.getBoolean("bath"),rs.getBoolean("fuel"), rs.getBoolean("roadtrain"), Double.parseDouble(rs.getString("posLng")), Double.parseDouble(rs.getString("posLat")), rs.getString("description"), rs.getLong("lastUpdated"), rs.getBoolean("deleted")));
        }   
        return spots;
    }
}
