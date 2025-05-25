/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.flights;

import core.models.Flight;
import core.models.Passenger;

/**
 *
 * @author DELL
 */
public class AddFlight {
    //para agregar un vuelo a un pasajero
    public static void addFlight(Passenger passenger, Flight flight){
        passenger.getFlights().add(flight);
        flight.getPassengers().add(passenger);
    }
}
