/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.comboBox;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.comboBox.FlightLoadComboBox;
import core.models.comboBox.LocationLoadComboBox;
import core.models.comboBox.PassengerLoadComboBox;
import core.models.comboBox.PlaneLoadComboBox;
import javax.swing.JComboBox;

/**
 *
 * @author DELL
 */
public class LoadJsonComboBoxController {
    //para controlar la carga de json a los comboBox
    public static Response loadComboBox(JComboBox<String> jComboxUserSelect, JComboBox<String> jComboxPlaneFR, JComboBox<String> jComboxDLocationFR, JComboBox<String> jComboxALocationFR, JComboBox<String> jComboxSLocationFR, JComboBox<String> jComboxFlightATF, JComboBox<String> jComboxIDDF){
        try {
            PassengerLoadComboBox.cargarComboBox(jComboxUserSelect);
            PlaneLoadComboBox.cargarComboBox(jComboxPlaneFR);
            LocationLoadComboBox.cargarComboBox(jComboxDLocationFR);
            LocationLoadComboBox.cargarComboBox(jComboxALocationFR);
            LocationLoadComboBox.cargarComboBox(jComboxSLocationFR);
            FlightLoadComboBox.cargarComboBox(jComboxFlightATF);
            FlightLoadComboBox.cargarComboBox(jComboxIDDF);
            
            return new Response("File upload successfully to comboBoxs", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
           
}
