/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Location;
import core.models.Plane;
import core.models.comboBox.FlightLoadComboBox;
import core.models.flights.AddFlightToPlane;
import core.models.storage.FlightStorage;
import core.models.storage.LocationStorage;
import core.models.storage.PlaneStorage;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author DELL
 */
public class FlightController {
    //controla create flight
    public static Response createFlight(String id, String planeId, String departureLocationId, String scaleLocationId, String arrivalLocationId, String year, String month, String day, String hour, String minutes, String hoursDurationArrival, String minutesDurationArrival, String hoursDurationScale, String minutesDurationScale, JComboBox<String> comboBox) {
        FlightStorage flightStorage = FlightStorage.getInstance();
        ArrayList<Flight> flights = flightStorage.getFlights();
        PlaneStorage planeStorage = PlaneStorage.getInstance();
        ArrayList<Plane> planes = planeStorage.getPlanes();
        LocationStorage locationStorage = LocationStorage.getInstance();
        ArrayList<Location> locations = locationStorage.getLocations();
        boolean planeIdBoo = false;
        LocalDateTime departureDate;
        int year1, month1, day1, hour1, minutes1, hoursDurationArrival1, minutesDurationArrival1, scaleLocationId1, hoursDurationScale1, minutesDurationScale1;
        boolean scale = true, hourScale = true, minuteScale = true, cont = false;
        Location departureLocation = null, scaleLocation = null, arrivalLocation = null;
        Plane plane = null;
        Flight flight;
        try {

            //Validar campos vacios 
            if (id.equals("")) {
                return new Response("Id must be not empty.", Status.BAD_REQUEST);
            }
            if (year.equals("")) {
                return new Response("Year must be not empty.", Status.BAD_REQUEST);
            }

            //Validar ID
            if (!id.matches("^[A-Z]{3}[0-9]{3}$")) {
                return new Response("Invalid ID. Must have 3 capital letters followed by 3 digits (format XXXYYY)", Status.BAD_REQUEST);
            }
            for (Flight f : flights) {
                if (f.getId().equals(id)) {
                    return new Response("A user with this ID is already registered.", Status.BAD_REQUEST);
                }
            }

            //Validar Avión
            if (planeId.equals("Plane")) {
                return new Response("You must select a plane.", Status.NOT_FOUND);
            }
            for (Plane p : planes) {
                if (p.getId().equals(planeId)) {
                    plane = p;
                }
            }
            if (plane == null) {
                return new Response("The plane ID entered is not registered", Status.NOT_FOUND);
            }

            //Validar que se hayan seleccionado las localizaciones de salida y llegada 
            if (departureLocationId.equals("Location")) {
                return new Response("You must select a departure location.", Status.NOT_FOUND);
            }
            if (arrivalLocationId.equals("Location")) {
                return new Response("You must select a arrival location.", Status.NOT_FOUND);
            }
            //Validar que las localizaciones seleccionadas existan 
            for (Location lo : locations) {
                if (lo.getAirportId().equals(departureLocationId)) {
                    departureLocation = lo;
                }
                if (lo.getAirportId().equals(arrivalLocationId)) {
                    arrivalLocation = lo;
                }
                if (lo.getAirportId().equals(scaleLocationId)) {
                    scaleLocation = lo;
                }
            }
            if (departureLocation == null) {
                return new Response("The selected departure airport is not registered.", Status.NOT_FOUND);
            }
            if (arrivalLocation == null) {
                return new Response("The selected arrival airport is not registered.", Status.NOT_FOUND);
            }

            //Validar campos de departureDate y arrivalDate
            try {
                year1 = Integer.parseInt(year);
                if (year1 < 0) {
                    return new Response("The phone must be positive.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Year must be numeric.", Status.BAD_REQUEST);
            }
            if (month.equals("Month")) {
                return new Response("Please choose the month of departure date.", Status.BAD_REQUEST);
            }
            if (day.equals("Day")) {
                return new Response("Please choose the day of departure date.", Status.BAD_REQUEST);
            }
            if (hour.equals("Hour")) {
                return new Response("Please choose the hour of departure date.", Status.BAD_REQUEST);
            }
            if (minutes.equals("Minute")) {
                return new Response("Please choose the minutes of departure date.", Status.BAD_REQUEST);
            }
            if (hoursDurationArrival.equals("Hour")) {
                return new Response("Please select the number of arrival times.", Status.BAD_REQUEST);
            }
            if (minutesDurationArrival.equals("Minute")) {
                return new Response("Please select the number of minutes of arrival.", Status.BAD_REQUEST);
            }
            //Validar departureDate
            try {
                month1 = Integer.parseInt(month);
                day1 = Integer.parseInt(day);
                hour1 = Integer.parseInt(hour);
                minutes1 = Integer.parseInt(minutes);
                departureDate = LocalDateTime.of(year1, month1, day1, hour1, minutes1);
                //validar que sea una fecha futura
                if (departureDate.isBefore(LocalDateTime.now())) {
                    return new Response("The departure date cannot be in the past.", Status.BAD_REQUEST);
                }
            } catch (DateTimeException e) {
                return new Response("Please select a valid date.", Status.BAD_REQUEST);
            }

            //Validar el tiempo de vuelo mayor a 00:00
            hoursDurationArrival1 = Integer.parseInt(hoursDurationArrival);
            minutesDurationArrival1 = Integer.parseInt(minutesDurationArrival);
            if (hoursDurationArrival1 == 0 && minutesDurationArrival1 == 0) {
                return new Response("The flight duration must be greater than 00:00.", Status.BAD_REQUEST);
            }
            //Validar ScaleLocation
            if (!scaleLocationId.equals("Location")) {
                if (scaleLocation == null) {
                    return new Response("The selected scale airport is not registered.", Status.NOT_FOUND);
                }
                if (hoursDurationScale.equals("Hour")) {
                    return new Response("Please select the hours duration of scale.", Status.BAD_REQUEST);
                }
                if (minutesDurationScale.equals("Minute")) {
                    return new Response("Please select the minutes duration of scale.", Status.BAD_REQUEST);
                }
                hoursDurationScale1 = Integer.parseInt(hoursDurationScale);
                minutesDurationScale1 = Integer.parseInt(minutesDurationScale);
                flight = new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hoursDurationArrival1, minutesDurationArrival1, hoursDurationScale1, minutesDurationScale1);

            } else {
                //Si no hay escala, colocar las horas y los minutos en cero. Por lo que entendí, es así. :D 
                //Modifique el constructor de flight que no recibe la eslala, para que pusiera scale en null y los tiempos en cero
                hoursDurationScale1 = 0;
                minutesDurationScale1 = 0;
                flight = new Flight(id, plane, departureLocation, arrivalLocation, departureDate, hoursDurationArrival1, minutesDurationArrival1);

            }
            //Completar relación flight-plane
            AddFlightToPlane.addFlight(plane, flight);
            //Actualizar storage
            flightStorage.addItem(flight);
            //actualizar comboBox
            FlightLoadComboBox.cargarComboBox(comboBox);
            return new Response("Flight added.", Status.CREATED);
        } catch (Exception e) {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);

        }
    }
}
