/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Location;
import core.models.storage.LocationStorage;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class LocationController {

    public static Response createLocation(String id, String name, String city, String country, String latitude, String longitude) {
        LocationStorage locationStorage = LocationStorage.getInstance();
        ArrayList<Location> locations = locationStorage.getLocations();
        double longitude1 = 0, latitude1 = 0;
        try {
            //Validar campos vacios
            if (id.equals("") || id.isBlank()) {
                return new Response("Id must be not empty.", Status.BAD_REQUEST);
            }
            if (name.equals("") || name.isBlank()) {
                return new Response("Name must be not empty.", Status.BAD_REQUEST);
            }
            if (city.equals("") || city.isBlank()) {
                return new Response("City must be not empty.", Status.BAD_REQUEST);
            }
            if (country.equals("") || country.isBlank()) {
                return new Response("Country must be not empty.", Status.BAD_REQUEST);
            }
            if (latitude.equals("") || latitude.isBlank()) {
                return new Response("Latitude must be not empty.", Status.BAD_REQUEST);
            }
            if (longitude.equals("") || longitude.isBlank()) {
                return new Response("Longitude must be not empty.", Status.BAD_REQUEST);
            }

            //Validar id
            if (!id.matches("^[A-Z]{3}$")) {
                return new Response("Invalid ID. Must contain exactly 3 capital letters.", Status.BAD_REQUEST);
            }
            for (Location l : locations) {
                if (l.getAirportId().equals(id)) {
                    return new Response("A airport with this ID is already registered.", Status.BAD_REQUEST);
                }
            }
            //Validar latitud
            try {
                latitude1 = Double.parseDouble(latitude);
            } catch (NumberFormatException e) {
                return new Response("Latitude must be numeric.", Status.BAD_REQUEST);
            }

            if (latitude1 < -90 || latitude1 > 90) {
                return new Response("Invalid latitude. Must be in the range [-90, 90].", Status.BAD_REQUEST);
            }
            if (latitude1 * 10000 != Math.floor(latitude1 * 10000)) {
                return new Response("Invalid latitude. Must have a maximum of 4 decimal places", Status.BAD_REQUEST);
            }

            //Validar longitud
            try {
                longitude1 = Double.parseDouble(longitude);
            } catch (NumberFormatException e) {
                return new Response("Longitude must be numeric.", Status.BAD_REQUEST);
            }
            if (longitude1 < -180 || longitude1 > 180) {
                return new Response("Invalid longitude. Must be in the range [-90, 90].", Status.BAD_REQUEST);
            }
            if (longitude1 * 10000 != Math.floor(longitude1 * 10000)) {
                return new Response("Invalid longitude. Must have a maximum of 4 decimal places.", Status.BAD_REQUEST);
            }
            
            locationStorage.addItem(new Location(id, name, city, country, latitude1, longitude1));
            return new Response("Airport added.", Status.CREATED);

        } catch (Exception e) {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);

        }
    }
}
