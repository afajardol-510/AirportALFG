/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tableList;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.storage.PassengerStorage;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class PassengersListController {
    public static Response updatePassengersList(DefaultTableModel model){
        try {
            model.setRowCount(0);
            PassengerStorage passengerStorage = PassengerStorage.getInstance();
            ArrayList<Passenger> passengers = passengerStorage.getPassengers();
            
            if (passengers == null || passengers.isEmpty()) {
                return new Response("The list is empty.", Status.NO_CONTENT);
            }
            passengers.sort((a1,a2) -> Long.compare(a1.getId(),a2.getId()));
            
            for (Passenger passenger : passengers) {
                LocalDate birthDate = passenger.getBirthDate();
                LocalDate currentDate = LocalDate.now();
                Period age = Period.between(birthDate, currentDate);
                
                model.addRow(new Object[]{passenger.getId(), passenger.getFirstname() + " " + passenger.getLastname(), passenger.getBirthDate(), age.getYears(), passenger.getPhone(), passenger.getCountry(), passenger.getFlights().size()});
            }
            
            return new Response("List updated succesfully.", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
