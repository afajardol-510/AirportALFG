/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tables;

import core.controllers.tables.*;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Passenger;
import core.models.storage.FlightStorage;
import core.models.storage.PassengerStorage;
import core.models.tables.MyFlightsTable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class MyFlightsTableController {

    //para controlar la carga de los vuelos del respectivo pasajero al Jtable
    public static Response updateMyFlightsTable(DefaultTableModel model, String passengerId) {
        try {
            if(passengerId.equals("Select User")){
                return new Response("Please, select a passenger on administration.", Status.BAD_REQUEST);
            }
            model.setRowCount(0);
            PassengerStorage passengerStorage = PassengerStorage.getInstance();
            ArrayList<Passenger> passengers = passengerStorage.getPassengers();
            ArrayList<Flight> flights = new ArrayList<>();

            long passId = Long.parseLong(passengerId);
            for (Passenger passenger : passengers) {
                if (passenger.getId() == passId) {
                    flights = passenger.getFlights();
                }
            }
            
            if (flights == null || flights.isEmpty()) {
                return new Response("The list is empty.", Status.NO_CONTENT, flights.clone());
            }
            
            MyFlightsTable.updateFlights(model, flights);

            return new Response("List updated succesfully.", Status.OK, flights.clone());
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR, null);
        }
    }
}
