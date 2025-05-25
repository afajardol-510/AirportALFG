/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Flight;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
//almacenamiento de vuelos
public class FlightStorage extends Storage {
    private static FlightStorage instance; 
    private ArrayList<Flight> flights;

    private FlightStorage() {
        this.flights=new ArrayList<>();
    }
    
    public static FlightStorage getInstance(){
        if(instance==null){
            instance=new FlightStorage();
        }
        return instance;
    }   

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }
  
    @Override
    public void addItem(Object flight) {
        if(!this.flights.contains((Flight)flight)){
            this.flights.add((Flight)flight);
        }
    }
    
}
