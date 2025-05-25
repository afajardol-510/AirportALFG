/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.flights;

import core.models.Flight;
import core.models.Plane;

/**
 *
 * @author DELL
 */
public class AddFlightToPlane {
    //para completar la relacion flight-plane
    public static void addFlight(Plane plane, Flight flight){
        plane.getFlights().add(flight);
    }
}
