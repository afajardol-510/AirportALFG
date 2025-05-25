/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.tables;

import core.models.Passenger;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class PassengersTable {
    //pasar pasajeros del storage al JTable
    public static void updatePassengers(DefaultTableModel model, ArrayList<Passenger> passengers) {
        passengers.sort((a1, a2) -> Long.compare(a1.getId(), a2.getId()));

        for (Passenger passenger : passengers) {
            LocalDate birthDate = passenger.getBirthDate();
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(birthDate, currentDate);

            model.addRow(new Object[]{passenger.getId(), passenger.getFirstname() + " " + passenger.getLastname(), passenger.getBirthDate(), age.getYears(), passenger.getPhone(), passenger.getCountry(), passenger.getFlights().size()});
        }
    }
}
