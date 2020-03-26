/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.jsonwebtoken.Claims;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import models.Appointment;
import models.Dog;
import services.AppointmentService;
import services.DogService;
import services.JWT;

/**
 * REST Web Service
 *
 * @author 640195
 */
@Path("getappointments")
public class GetAppointments {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GetAppointments
     */
    public GetAppointments() {
    }

    /**
     * Retrieves representation of an instance of rest.GetAppointments
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("token") String token) {

        //return the token decoded
        Claims claims;
        try{
        claims = JWT.decodeJWT(token);
        } catch(Exception e){
            return null;
        }
        AppointmentService as = new AppointmentService();
        String username = claims.get("username", String.class);
        ArrayList<Appointment> aList = as.getAppointmentsByUsername(username); 
        DogService ds = new DogService();
        String dogNames = "";
        for (int x = 0; x < aList.size(); x++) {
            dogNames = "";
            String[] id = aList.get(x).getDogIdNumber().split(",");
            for (int i = 0; i < id.length; i++) {
                if (i != 0) {
                    dogNames += ",";
                }
                Dog dog = ds.getDogByID(Integer.parseInt(id[i]));
                dogNames += dog.getName();
                
            }
            aList.get(x).setDogNames(dogNames);
        }
       Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
       String gsonList = gson.toJson(aList);
        return gsonList;
    }

    /**
     * PUT method for updating or creating an instance of GetAppointments
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}