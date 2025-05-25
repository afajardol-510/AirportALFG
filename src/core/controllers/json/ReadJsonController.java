/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.json;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.json.FlightReadJson;
import core.models.json.LocationReadJson;
import core.models.json.PassengerReadJson;
import core.models.json.PlaneReadJson;


/**
 *
 * @author DELL
 */
public class ReadJsonController {
    //para controlar la carga de json al Storage
    public static Response readJson(){
        try{
            PlaneReadJson.leerJson();
            LocationReadJson.leerJson();
            PassengerReadJson.leerJson();
            FlightReadJson.leerJson(); 
            return new Response("File upload successfully", Status.OK);   
        } catch (org.json.JSONException e) {
            return new Response("Error processing json.", Status.INTERNAL_SERVER_ERROR);
        }catch(Exception e){
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
        
    }
}
