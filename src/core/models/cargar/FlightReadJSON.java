/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.cargar;

import core.models.Location;
import core.models.Plane;
import core.models.storage.FlightStorage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */
public class FlightReadJSON {

    public static void leerJson() {
        try {

            FlightStorage flightStorage = FlightStorage.getInstance();

            String contenido = new String(Files.readAllBytes(Paths.get("json//flights.json")));

            // Convertir a JSONArray
            JSONArray flights = new JSONArray(contenido);

            // Iterar sobre cada objeto
            for (int i = 0; i < flights.length(); i++) {
                JSONObject flight = flights.getJSONObject(i);

                Plane plane = (Plane) flight.get("plane");
                Location departureLocation = (Location) flight.get("departureLocation");
                Location scaleLocation = (Location) flight.get("scaleLocation");
                Location arrivalLocation = (Location) flight.get("arrivalLocation");
                LocalDateTime departureDate = (LocalDateTime) flight.get("departureDate");
                int hoursDurationArrival = flight.getInt("hoursDurationArrival");
//In process 
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (org.json.JSONException e) {
            System.out.println("Error al procesar el JSON: " + e.getMessage());
        }
    }
}
