/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.json;

import core.models.Passenger;
import core.models.storage.PassengerStorage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author DELL
 */
public class PassengerReadJson {
    //leer passengers.json y pasar a passengerStorage
    public static void leerJson() throws IOException, JSONException {
        ArrayList<Passenger> passengersX = new ArrayList<>();

        PassengerStorage passengerStorage = PassengerStorage.getInstance();
        String contenido = new String(Files.readAllBytes(Paths.get("src\\json\\passengers.json")));

        // Convertir a JSONArray
        JSONArray passengers = new JSONArray(contenido);
        // Iterar sobre cada objeto
        for (int i = 0; i < passengers.length(); i++) {
            JSONObject passenger = passengers.getJSONObject(i);

            long id = passenger.getLong("id");
            String firstname = passenger.getString("firstname");
            String lastname = passenger.getString("lastname");
            //leer birthDate
            String fecha = passenger.getString("birthDate");
            LocalDate birthDate = LocalDate.parse(fecha);

            int countryPhoneCode = passenger.getInt("countryPhoneCode");
            long phone = passenger.getInt("phone");
            String country = passenger.getString("country");

            Passenger pas = new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
            passengersX.add(pas);

        }
        passengerStorage.setPassengers(passengersX);

    }
}
