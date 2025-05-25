/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.tables;

import core.models.Location;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class LocationsTable {
    //pasar locaciones del storage al JTable
    public static void updateLocations(DefaultTableModel model, ArrayList<Location> locations) {
        locations.sort((obj1, obj2) -> (obj1.getAirportId().compareTo(obj2.getAirportId())));

        for (Location location : locations) {
            model.addRow(new Object[]{location.getAirportId(), location.getAirportName(), location.getAirportCity(), location.getAirportCountry()});
        }
    }
}
