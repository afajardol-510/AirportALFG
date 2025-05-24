/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Location;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class LocationStorage extends Storage{
    private static LocationStorage instance; 
    private ArrayList<Location> locations;

    public LocationStorage() {
        this.locations=new ArrayList<>();
    }
    
    public static LocationStorage getInstance(){
        if(instance==null){
            instance=new LocationStorage();
        }
        return instance;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
    

    @Override
    public void addItem(Object location) {
        if(!this.locations.contains((Location)location)){
            this.locations.add((Location)location);
        }
    }
    
}
