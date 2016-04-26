/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package diverse;

import convoy.db.Spot;
import java.util.ArrayList;

/**
 *
 * @author Jon
 */
public class SpotsContainer {
    
    private ArrayList<Spot> spots;
    private long lastUpdated;
    
    public SpotsContainer(ArrayList<Spot> spots, long lastUpdated){
        this.spots = spots;
        this.lastUpdated = lastUpdated;
    }
    
    public ArrayList<Spot> getSpots() {
        return spots;
    }
    
    public void setSpots(ArrayList<Spot> spots) {
        this.spots = spots;
    }
    
    public long getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }    
}
