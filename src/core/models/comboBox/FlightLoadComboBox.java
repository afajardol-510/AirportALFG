/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.comboBox;

import core.controllers.utils.Response;
import core.models.Flight;
import core.models.storage.FlightStorage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JComboBox;

/**
 *
 * @author DELL
 */
public class FlightLoadComboBox{
    //para cargar los vuelos al comboBox
    public static void cargarComboBox(JComboBox<String> comboBox) {
        FlightStorage flightStorage = FlightStorage.getInstance();
        ArrayList<Flight> flights = flightStorage.getFlights();
        comboBox.removeAllItems();
        Collections.sort(flights, Comparator.comparing(Flight::getId));
        comboBox.addItem("Flight");
        for (Flight flight : flights) {
            comboBox.addItem("" + flight.getId());
        }
    }
}
