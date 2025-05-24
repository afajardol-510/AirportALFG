/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.storage.PassengerStorage;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class PassengerController {

    //paso 1. obtener datos
    //paso 2. mandar la response
    //paso 3. mandar la respuesta 
    public static Response registerPassenger(String id, String firstname, String lastname, String year, String month, String day, String countryPhoneCode, String phone, String country) {
        
        PassengerStorage passengerStorage = PassengerStorage.getInstance();
        ArrayList<Passenger> passengers = passengerStorage.getPassengers();
        
        long id1;
        int countryPhoneCode1, phone1, year1, month1, day1;
        LocalDate birthDate1;

        try {
            //Validar que no haya campos vacios
            if (id.equals("") || id.isBlank()) {
                return new Response("Id must be not empty.", Status.BAD_REQUEST);
            }
            if (firstname.equals("") || firstname.isBlank()) {
                return new Response("Firstname must be not empty.", Status.BAD_REQUEST);
            }
            if (lastname.equals("") || lastname.isBlank()) {
                return new Response("Lastname must be not empty.", Status.BAD_REQUEST);
            }
            
            if (year.equals("") || year.isBlank()) {
                return new Response("Year must be not empty.", Status.BAD_REQUEST);
            }
            if (countryPhoneCode.equals("") || countryPhoneCode.isBlank()) {
                return new Response("CountryPhoneCode must be not empty.", Status.BAD_REQUEST);
            }
            if (phone.equals("") || phone.isBlank()) {
                return new Response("Phone must be not empty.", Status.BAD_REQUEST);
            }
            if (country.equals("") || country.isBlank()) {
                return new Response("Country must be not empty.", Status.BAD_REQUEST);
            }

            //validaciones id
            try {
                id1 = Long.parseLong(id);
                if (id1 < 0) {
                    return new Response("The ID must be positive.", Status.BAD_REQUEST);
                }
                if (String.valueOf(id).length() > 15) {
                    return new Response("The ID must have a maximum of 15 digits.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            for (Passenger p : passengers) {
                if (p.getId() == id1) {
                    return new Response("A user with this ID is already registered.", Status.BAD_REQUEST);
                }
            }

            //Validaciones codigos telefonicos
            try {
                countryPhoneCode1 = Integer.parseInt(countryPhoneCode);
                if (countryPhoneCode1 < 0) {
                    return new Response("The countryPhoneCode must be positive.", Status.BAD_REQUEST);
                }
                if (String.valueOf(countryPhoneCode).length() > 3) {
                    return new Response("The countryPhoneCode must have a maximum of 3 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("CountryPhoneCode must be numeric", Status.BAD_REQUEST);
            }

            //Validaciones telefonos 
            try {
                phone1 = Integer.parseInt(phone);
                if (phone1 < 0) {
                    return new Response("The phone must be positive.", Status.BAD_REQUEST);
                }
                if (String.valueOf(phone).length() > 11) {
                    return new Response("The phone must have a maximum of 11 digits.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Phone must be numeric.", Status.BAD_REQUEST);
            }

            //Validaciones fecha de nacimiento
            try {
                year1 = Integer.parseInt(year);
                if (year1 <= 1900) {
                    return new Response("Invalid year.", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Year must be numeric.", Status.BAD_REQUEST);
            }

            try {
                month1 = Integer.parseInt(month);
            } catch (NumberFormatException e) {
                return new Response("Please choose a month.", Status.BAD_REQUEST);
            }

            try {
                day1 = Integer.parseInt(day);
            } catch (NumberFormatException e) {
                return new Response("Please choose a day.", Status.BAD_REQUEST);
            }
            try {
                birthDate1 = LocalDate.of(year1, month1, day1);
            } catch (DateTimeException e) {
                return new Response("Please select a valid date.", Status.BAD_REQUEST);
            }
            Passenger passenger = new Passenger(id1, firstname, lastname, birthDate1, countryPhoneCode1, phone1, country);
            passengerStorage.addItem(passenger);
            return new Response("Passenger added.", Status.CREATED);

        } catch (Exception e) {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
        }

    }
}
