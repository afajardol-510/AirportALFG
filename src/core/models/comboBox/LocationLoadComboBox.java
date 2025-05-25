/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.comboBox;

import core.models.Location;
import core.models.storage.LocationStorage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JComboBox;

/**
 *
 * @author DELL
 */
public class LocationLoadComboBox {
    //para cargar las locaciones al comboBox
    public static void cargarComboBox(JComboBox<String> comboBox) {
        LocationStorage locationStorage = LocationStorage.getInstance();
        ArrayList<Location> locations = locationStorage.getLocations();
        comboBox.removeAllItems();
        Collections.sort(locations, Comparator.comparing(Location::getAirportId));
        comboBox.addItem("Location");
        for (Location location : locations) {
            comboBox.addItem("" + location.getAirportId());
        }
    }
}
