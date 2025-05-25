/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.json;

import core.models.Flight;
import core.models.Location;
import core.models.Plane;
import core.models.storage.FlightStorage;
import core.models.storage.LocationStorage;
import core.models.storage.PlaneStorage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JComboBox;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */
public class FlightReadJson {
    //leer flights.json y pasar al flightStorage
    public static void leerJson() throws IOException, JSONException {
        ArrayList<Flight> flightsX = new ArrayList<>();

        FlightStorage flightStorage = FlightStorage.getInstance();
        LocationStorage locationStorage = LocationStorage.getInstance();
        ArrayList<Location> locations = locationStorage.getLocations();
        PlaneStorage planeStorage = PlaneStorage.getInstance();
        ArrayList<Plane> planes = planeStorage.getPlanes();

        String contenido = new String(Files.readAllBytes(Paths.get("src\\json\\flights.json")));

        // Convertir a JSONArray
        JSONArray flights = new JSONArray(contenido);

        // Iterar sobre cada objeto
        for (int i = 0; i < flights.length(); i++) {
            JSONObject flight = flights.getJSONObject(i);

            Location departureLocation = null, arrivalLocation = null, scaleLocation = null;
            Plane plane = null;
            String id = flight.getString("id");
            String planeId = flight.getString("plane");
            String departureLocationId = flight.getString("departureLocation");
            String arrivalLocationId = flight.getString("arrivalLocation");
            //leer scaleLocation
            String scaleLocationId = null;
            if (flight.has("scaleLocation") && !flight.isNull("scaleLocation")) {
                scaleLocationId = flight.getString("scaleLocation");
            }
            //leer departureDate
            String fecha = flight.getString("departureDate");
            LocalDateTime departureDate = LocalDateTime.parse(fecha);
            int hoursDurationArrival = flight.getInt("hoursDurationArrival");
            int minutesDurationArrival = flight.getInt("minutesDurationArrival");
            int hoursDurationScale = flight.getInt("minutesDurationArrival");
            int minutesDurationScale = flight.getInt("minutesDurationArrival");

            for (Plane p : planes) {
                if (p.getId().equals(planeId)) {
                    plane = p;
                }
            }

            for (Location lo : locations) {
                if (lo.getAirportId().equals(departureLocationId)) {
                    departureLocation = lo;
                }
                if (lo.getAirportId().equals(arrivalLocationId)) {
                    arrivalLocation = lo;
                }
                if (scaleLocationId != null) {
                    if (lo.getAirportId().equals(scaleLocationId)) {
                        scaleLocation = lo;
                    }
                }
            }
            Flight f = new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale);
            
            plane.getFlights().add(f);

            flightsX.add(f);

        }
        flightStorage.setFlights(flightsX);

    }
}
