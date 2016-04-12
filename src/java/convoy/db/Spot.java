/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package convoy.db;

import java.io.Serializable;

/*
Klassen funger som Data Transfer Objekt (DTO) for spots.
*/
public class Spot implements Serializable {
    
    private boolean addBlue, food, wc, bed, bath, fuel, roadtrain, deleted;
    private double longitude, latitude;
    private String name;
    private long lastUpdated;
    private int id;
    
    public Spot(){}
    
    public Spot(int id, boolean addBlue, boolean food, boolean wc, boolean bed, boolean bath, boolean roadtrain, double longitude, double latitude, String name, long lastUpdated, boolean deleted){
        this.id = id;
        this. addBlue = addBlue;
        this.food = food;
        this.wc = wc;
        this.bed = bed;
        this.bath = bath;
        this.roadtrain = roadtrain;
        this.longitude = longitude;
        this.latitude = latitude;
        this.lastUpdated = lastUpdated;
        this.deleted = deleted;
    }
    
    public boolean isDeleted(){
        return deleted;
    }
    
    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }
    
    public void setLastUpdated(long lastUpdated){
        this.lastUpdated = lastUpdated;
    }
    
    public long getLastUpdated(){
        return lastUpdated;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }

    public boolean isAddBlue() {
        return addBlue;
    }

    public void setAddBlue(boolean addBlue) {
        this.addBlue = addBlue;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isWc() {
        return wc;
    }

    public void setWc(boolean wc) {
        this.wc = wc;
    }

    public boolean isBed() {
        return bed;
    }

    public void setBed(boolean bed) {
        this.bed = bed;
    }

    public boolean isBath() {
        return bath;
    }

    public void setBath(boolean bath) {
        this.bath = bath;
    }

    public boolean isFuel() {
        return fuel;
    }

    public void setFuel(boolean fuel) {
        this.fuel = fuel;
    }

    public boolean isRoadtrain() {
        return roadtrain;
    }

    public void setRoadtrain(boolean roadtrain) {
        this.roadtrain = roadtrain;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatidude() {
        return latitude;
    }

    public void setLatidude(double latidude) {
        this.latitude = latidude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
