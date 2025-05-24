/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.cargar;

import core.models.Location;
import core.models.storage.LocationStorage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */
public class LocationReadJSON {

    public void leerJson() {
        try {
            LocationStorage locationStorage = LocationStorage.getInstance();
            String contenido = new String(Files.readAllBytes(Paths.get("json//locations.json")));
            // Convertir a JSONArray
            JSONArray locations = new JSONArray(contenido);

            // Iterar sobre cada objeto
            for (int i = 0; i < locations.length(); i++) {
                JSONObject location = locations.getJSONObject(i);
                String airportId = location.getString("airportId");
                String airportName = location.getString("airportName");
                String airportCity = location.getString("airportCity");
                String airportCountry = location.getString("airportCountry");
                double airportLatitude = location.getDouble("airportLatitude");
                double airportLongitude = location.getDouble("airportLongitude");

                Location loc = new Location(airportId, airportName, airportCity, airportCountry, airportLatitude, airportLongitude);
                locationStorage.addItem(loc);
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (org.json.JSONException e) {
            System.out.println("Error al procesar el JSON: " + e.getMessage());
        }
    }
}
