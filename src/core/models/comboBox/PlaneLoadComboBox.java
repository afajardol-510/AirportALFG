/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.comboBox;

import core.models.Plane;
import core.models.storage.PlaneStorage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JComboBox;

/**
 *
 * @author DELL
 */
public class PlaneLoadComboBox {
    //para cargar los aviones al comboBox
    public static void cargarComboBox(JComboBox<String> comboBox) {
        PlaneStorage planeStorage = PlaneStorage.getInstance();
        ArrayList<Plane> planes = planeStorage.getPlanes();
        comboBox.removeAllItems();
        Collections.sort(planes, Comparator.comparing(Plane::getId));
        comboBox.addItem("Plane");
        for (Plane plane : planes) {
            comboBox.addItem("" + plane.getId());
        }
    }
}
