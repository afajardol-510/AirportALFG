/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.tables;

import core.controllers.tables.*;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;
import core.models.storage.PlaneStorage;
import core.models.tables.PlanesTable;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class PlanesTableController {
    //para controlar la carga de los aviones al JTable
    public static Response updatePlanesTable(DefaultTableModel model){
        try {
            model.setRowCount(0);
            PlaneStorage planeStorage = PlaneStorage.getInstance();
            ArrayList<Plane> planes = planeStorage.getPlanes();
            
            if (planes == null || planes.isEmpty()) {
                return new Response("The list is empty.", Status.NO_CONTENT, planes.clone());
            }
            PlanesTable.updatePlanes(model, planes);
            
            return new Response("List updated succesfully.", Status.OK, planes.clone());
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
