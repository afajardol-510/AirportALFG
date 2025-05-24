/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Plane;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class PlaneStorage extends Storage{
    private static PlaneStorage instance;
    private ArrayList<Plane> planes;

    public PlaneStorage() {
        this.planes=new ArrayList<>();
    }
    
    public static PlaneStorage getInstance(){
        if(instance==null){
            instance=new PlaneStorage();
        }
        return instance;
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }

    @Override
    public void addItem(Object plane) {
        if(!this.planes.contains((Plane)plane)){
            this.planes.add((Plane)plane);
        }
    }
    
}
