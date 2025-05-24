/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Plane;
import core.models.storage.PlaneStorage;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class PlaneController {

    public static Response createPlane(String id, String brand, String model, String maxCapacity, String airline) {

        PlaneStorage planeStorage = PlaneStorage.getInstance();
        ArrayList<Plane> planes = planeStorage.getPlanes();
        try {
            //Validar que no haya campos vacios
            int maxCapacity1;
            if (id.equals("") || id.isBlank()) {
                return new Response("Id must be not empty.", Status.BAD_REQUEST);
            }
            if (brand.equals("") || brand.isBlank()) {
                return new Response("Brand must be not empty.", Status.BAD_REQUEST);
            }
            if (model.equals("") || model.isBlank()) {
                return new Response("Model must be not empty.", Status.BAD_REQUEST);
            }
            if (maxCapacity.equals("") || maxCapacity.isBlank()) {
                return new Response("MaxCapacity must be not empty.", Status.BAD_REQUEST);
            }
            if (airline.equals("") || airline.isBlank()) {
                return new Response("Airline must be not empty.", Status.BAD_REQUEST);
            }

            //validar id
            if (!id.matches("^[A-Z]{2}[0-9]{5}$")) {
                return new Response("Invalid ID. Must have 2 capital letters followed by 5 digits (format XXYYYYY)", Status.BAD_REQUEST);
            }
            for (Plane p : planes) {
                if (p.getId().equals(id)) {
                    return new Response("A plane with this ID is already registered.", Status.BAD_REQUEST);
                }
            }

            //validar maxCapacity
            try {
                maxCapacity1 = Integer.parseInt(maxCapacity);
            } catch (NumberFormatException e) {
                return new Response("MaxCapacity must be numeric", Status.BAD_REQUEST);

            }
            planeStorage.addItem(new Plane(id, brand, model, maxCapacity1, airline));
            return new Response("Plane added.", Status.CREATED);
        } catch (Exception e) {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);

        }

    }
}
