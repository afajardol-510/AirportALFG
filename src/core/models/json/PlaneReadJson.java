/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.json;

import core.models.Plane;
import core.models.storage.PlaneStorage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */
public class PlaneReadJson {
    //leer planes.json y pasar a planeStorage
    public static void leerJson() throws IOException, JSONException{
        ArrayList<Plane> planesX = new ArrayList<>();
      
            PlaneStorage planeStorage = PlaneStorage.getInstance();
            
            String contenido = new String(Files.readAllBytes(Paths.get("src\\json\\planes.json")));
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
                planesX.add(pla);
                
            }
            planeStorage.setPlanes(planesX);
            
        
    }
}
