/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.flights.DelayFlight;
import core.models.storage.FlightStorage;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class DelayFlightController {

    public static Response delayFlight(String flightId, String hour, String minute) {
        //controla delayFlight
        FlightStorage flightStorage = FlightStorage.getInstance();
        ArrayList<Flight> flights = flightStorage.getFlights();
        Flight flight = null;
        int hour1, minute1;
        try {
            if (flights.isEmpty()) {
                return new Response("No flights available.", Status.NO_CONTENT);
            }
            if (flightId.equals("Flight")) {
                return new Response("Please select a flight.", Status.BAD_REQUEST);
            }
            if (hour.equals("Hour")) {
                return new Response("Please select hour.", Status.BAD_REQUEST);
            }
            if (minute.equals("Minute")) {
                return new Response("Please select minute.", Status.BAD_REQUEST);
            }
            
            for (Flight f : flights) {
                if (f.getId().equals(flightId)) {
                    flight = f;
                }
            }
            hour1 = Integer.parseInt(hour);
            minute1 = Integer.parseInt(minute);
            if(hour1 == 0 && minute1 == 0){
                return new Response("The delay time cannot be zero.", Status.BAD_REQUEST);
            }
            
            DelayFlight.delayFlight(flight, hour1, minute1);
            return new Response("Successful update.", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }
}
