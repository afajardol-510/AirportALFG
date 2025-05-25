/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Passenger;
import core.models.flights.AddFlight;
import core.models.storage.FlightStorage;
import core.models.storage.PassengerStorage;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */

public class AddFlightController {
    //controla addFlight
    public static Response addFlight(String pId, String flightId){
        PassengerStorage passengerStorage = PassengerStorage.getInstance();
        ArrayList<Passenger> passengers = passengerStorage.getPassengers();
        FlightStorage flightStorage = FlightStorage.getInstance();
        ArrayList<Flight> flights = flightStorage.getFlights();
        
        Passenger passenger = null;
        Flight flight = null;
        try {
            long passengerId;
            if (passengers.isEmpty()) {
                return new Response("No passengers available", Status.NO_CONTENT);
            }
            try {
                passengerId = Long.parseLong(pId);
            } catch (NumberFormatException ex) {
                return new Response("Please, select a passenger on administration.", Status.BAD_REQUEST);
            }
            
            if (flights.isEmpty()) {
                return new Response("No flights available", Status.NO_CONTENT);
            }
            if (flightId.equals("Flight")) {
                return new Response("Please, select a flight.", Status.BAD_REQUEST);
            }
            
            for (Passenger p : passengers) {
                if(passengerId==p.getId()){
                    passenger = p;
                }
            }
                      
            for(Flight f: flights){
                if(flightId.equals(f.getId())){
                    flight = f;
                }
            }
            
            ArrayList<Flight> flightsPassenger = passenger.getFlights();
            for(Flight f1:flightsPassenger){
                if(f1.equals(flight)){
                    return new Response("The selected flight is already registered.", Status.BAD_REQUEST);
                }
            }
            

            AddFlight.addFlight(passenger, flight);
            return new Response("Flight added successfully", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }   
    }
}
