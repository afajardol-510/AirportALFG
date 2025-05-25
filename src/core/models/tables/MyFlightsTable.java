/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.tables;

import core.models.Flight;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class MyFlightsTable {
    //pasar los vuelos del respectivo pasajero al JTable

    public static void updateFlights(DefaultTableModel model, ArrayList<Flight> flights) {
        flights.sort((obj1, obj2) -> (obj1.getId().compareTo(obj2.getId())));

        for (Flight flight : flights) {
            LocalDateTime arrivalDate = flight.getDepartureDate();

            // Calcular arrivalDate
            // Si no hay escala no hay problema ya que el tiempo ya esta como cero en el constructor 
            arrivalDate = arrivalDate
                    .plusHours(flight.getHoursDurationScale() + flight.getHoursDurationArrival())
                    .plusMinutes(flight.getMinutesDurationScale() + flight.getMinutesDurationArrival());

            model.addRow(new Object[]{flight.getId(), flight.getDepartureDate(), arrivalDate});

        }
    }
}
