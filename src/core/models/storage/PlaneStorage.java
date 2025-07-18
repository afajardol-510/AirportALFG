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
//almacenamiento de aviones
public class PlaneStorage extends Storage{
    private static PlaneStorage instance;
    private ArrayList<Plane> planes;

    private PlaneStorage() {
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

    public void setPlanes(ArrayList<Plane> planes) {
        this.planes = planes;
    }

    @Override
    public void addItem(Object plane) {
        if(!this.planes.contains((Plane)plane)){
            this.planes.add((Plane)plane);
        }
    }
    
}
