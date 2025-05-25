/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tables;

import core.controllers.tables.*;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.LocationStorage;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import core.models.Location;
import core.models.tables.LocationsTable;

/**
 *
 * @author DELL
 */
public class LocationsTableController {
    //para controlar la carga de las locaciones del Storage al Jtable
    public static Response updateLocationsTable(DefaultTableModel model) {
        try {
            model.setRowCount(0);
            LocationStorage locationStorage = LocationStorage.getInstance();
            ArrayList<Location> locations = locationStorage.getLocations();

            if (locations == null || locations.isEmpty()) {
                return new Response("The list is empty.", Status.NO_CONTENT, locations.clone());
            }
            LocationsTable.updateLocations(model, locations);

            return new Response("List updated succesfully.", Status.OK, locations.clone());
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
