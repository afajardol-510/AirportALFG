/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.cargar;

import core.models.Passenger;
import core.models.storage.PassengerStorage;
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
public class PassengerReadJSON {
    public void leerJson(){
        try {
            PassengerStorage passengerStorage = PassengerStorage.getInstance();
            String contenido = new String(Files.readAllBytes(Paths.get("json//passengers.json")));

            // Convertir a JSONArray
            JSONArray passengers = new JSONArray(contenido);

            // Iterar sobre cada objeto
            for (int i = 0; i < passengers.length(); i++) {
                JSONObject passenger = passengers.getJSONObject(i);
                
                long id = passenger.getLong("id");
                String firstname = passenger.getString("firstname");
                String lastname = passenger.getString("lastname");
                LocalDate birthDate = (LocalDate) passenger.get("birthDate");
                int countryPhoneCode = passenger.getInt("countryPhoneCode");
                long phone = passenger.getInt("phone");
                String country = passenger.getString("country");
                
                Passenger pas=new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
                passengerStorage.addItem(pas);
                
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (org.json.JSONException e) {
            System.out.println("Error al procesar el JSON: " + e.getMessage());
        }
    }
}
