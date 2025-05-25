/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tables;

import core.controllers.tables.*;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Location;
import core.models.storage.FlightStorage;
import core.models.tables.FlightsTable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class FlightsTableController {
    //para controlar la carga de los vuelos del Storage al Jtable
    public static Response updateFlightsTable(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            FlightStorage flightStorage = FlightStorage.getInstance();
            ArrayList<Flight> flights = flightStorage.getFlights();

            if (flights == null || flights.isEmpty()) {
                return new Response("The list is empty.", Status.NO_CONTENT, flights.clone());
            }
            FlightsTable.updateFlights(model, flights);
            return new Response("List updated succesfully.", Status.OK, flights.clone());
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
