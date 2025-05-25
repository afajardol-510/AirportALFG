/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.tables;

import core.models.Plane;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class PlanesTable {
    //pasar aviones del Storage al JTable
    public static void updatePlanes(DefaultTableModel model, ArrayList<Plane> planes) {
        planes.sort((obj1, obj2) -> (obj1.getId().compareTo(obj2.getId())));

        for (Plane plane : planes) {

            model.addRow(new Object[]{plane.getId(), plane.getBrand(), plane.getModel(), plane.getMaxCapacity(), plane.getAirline(), plane.getFlights().size()});
        }
    }
}
