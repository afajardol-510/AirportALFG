/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.json;


import core.models.Location;
import core.models.storage.LocationStorage;
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
public class LocationReadJson {
    //leer locations.json y pasar a locationStorage
    public static void leerJson() throws IOException, JSONException {
        ArrayList<Location> locationsX = new ArrayList<>();
        
            LocationStorage locationStorage = LocationStorage.getInstance();
            String contenido = new String(Files.readAllBytes(Paths.get("src\\json\\locations.json")));
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
                locationsX.add(loc);
                
            }
            locationStorage.setLocations(locationsX);
    }
}
