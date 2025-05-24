/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.cargar;

import core.models.Plane;
import core.models.storage.PlaneStorage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */
public class PlaneReadJSON {

    public void leerJson() {
        try {
            PlaneStorage planeStorage = PlaneStorage.getInstance();
            String contenido = new String(Files.readAllBytes(Paths.get("json//planes.json")));
            // Convertir a JSONArray
            JSONArray planes = new JSONArray(contenido);

            // Iterar sobre cada objeto
            for (int i = 0; i < planes.length(); i++) {
                JSONObject plane = planes.getJSONObject(i);
                String id = plane.getString("id");
                String brand = plane.getString("brand");
                String model = plane.getString("model");
                int maxCapacity = plane.getInt("maxCapacity");
                String airline = plane.getString("airline");
                
                Plane pla=new Plane(id, brand, model, maxCapacity, airline);
                planeStorage.addItem(pla);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (org.json.JSONException e) {
            System.out.println("Error al procesar el JSON: " + e.getMessage());
        }
    }
}
