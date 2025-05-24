/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Passenger;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class PassengerStorage extends Storage{
    private static PassengerStorage instance;
    private ArrayList<Passenger> passengers;

    public PassengerStorage() {
        this.passengers=new ArrayList<>();
    }
    
    public static PassengerStorage getInstance(){
        if(instance==null){
            instance=new PassengerStorage();
        }
        return instance;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
    

    @Override
    public void addItem(Object passenger) {
        if(!this.passengers.contains((Passenger)passenger)){
            this.passengers.add((Passenger)passenger);
        }
    }
    
}
