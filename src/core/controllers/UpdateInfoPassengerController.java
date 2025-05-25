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
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class UpdateInfoPassengerController {

    public static Response updateInfo(String id, String firstname, String lastname, String year, String month, String day, String countryPhoneCode, String phone, String country) {
        PassengerStorage passengerStorage = PassengerStorage.getInstance();
        ArrayList<Passenger> passengers = passengerStorage.getPassengers();

        long id2;
        int countryPhoneCode1, phone1, year1, month1, day1;
        LocalDate birthDate1;
        Passenger passenger = null;

        try {

            //Validar que no haya campos vacios
            if (id.equals("")) {
                return new Response("Select a passenger in administration.", Status.BAD_REQUEST);
            }
            if (firstname.equals("")) {
                return new Response("Firstname must be not empty.", Status.BAD_REQUEST);
            }
            if (lastname.equals("")) {
                return new Response("Lastname must be not empty.", Status.BAD_REQUEST);
            }
            if (year.equals("")) {
                return new Response("Year must be not empty.", Status.BAD_REQUEST);
            }
            if (countryPhoneCode.equals("")) {
                return new Response("CountryPhoneCode must be not empty.", Status.BAD_REQUEST);
            }
            if (phone.equals("")) {
                return new Response("Phone must be not empty.", Status.BAD_REQUEST);
            }
            if (country.equals("")) {
                return new Response("Country must be not empty.", Status.BAD_REQUEST);
            }
            id2 = Long.parseLong(id);
            for (Passenger p : passengers) {
                if (p.getId() == id2) {
                    passenger = p;
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
                if (phone1 == 0) {
                    return new Response("The phone can't be zero.", Status.BAD_REQUEST);
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
            if (birthDate1.isAfter(LocalDate.now())) {
                return new Response("The date of birth cannot be later than the current date. ", Status.BAD_REQUEST);
            }
            //actualizar pasajero
            passenger.setFirstname(firstname);
            passenger.setLastname(lastname);
            passenger.setBirthDate(birthDate1);
            passenger.setCountryPhoneCode(countryPhoneCode1);
            passenger.setPhone(phone1);
            passenger.setCountry(country);

            return new Response("User information modified succesfully.", Status.CREATED);

        } catch (Exception e) {
            return new Response("Unexpected error.", Status.INTERNAL_SERVER_ERROR);
        }

    }
}
