/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.comboBox;

import core.models.Passenger;
import core.models.storage.PassengerStorage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JComboBox;

/**
 *
 * @author DELL
 */
public class PassengerLoadComboBox {
    //para cargar los pasajeros al comboBox
    public static void cargarComboBox(JComboBox<String> comboBox){
        PassengerStorage passengerStorage = PassengerStorage.getInstance();
        ArrayList<Passenger> passengers = passengerStorage.getPassengers();
        comboBox.removeAllItems();
        Collections.sort(passengers, Comparator.comparing(Passenger::getId));
        comboBox.addItem("Select User");
        for (Passenger passenger : passengers) {
            comboBox.addItem("" + passenger.getId());
        }
    }
}
