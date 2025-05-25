/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tables;

import core.controllers.tables.*;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.storage.PassengerStorage;
import core.models.tables.PassengersTable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class PassengersTableController {
    //para controlar la carga de pasajeros del Storage al JTable
    public static Response updatePassengersTable(DefaultTableModel model){
        try {
            model.setRowCount(0);
            PassengerStorage passengerStorage = PassengerStorage.getInstance();
            ArrayList<Passenger> passengers = passengerStorage.getPassengers();
            
            if (passengers == null || passengers.isEmpty()) {
                return new Response("The list is empty.", Status.NO_CONTENT, passengers.clone());
            }
            PassengersTable.updatePassengers(model, passengers);
            
            return new Response("List updated succesfully.", Status.OK, passengers.clone());
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
