/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package take.complaintsclient.app;

import com.google.gson.Gson;
import java.util.Arrays;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import model.Complaint;

public class Main {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        String count
                = client.target("http://localhost:8080/Complaints/resources/complaints/count")
                        .request(MediaType.TEXT_PLAIN)
                        .get(String.class);

        System.out.println("\nCount: " + count);

        System.out.println("\nAll complaints: ");

        String sComplaints = client.target("http://localhost:8080/Complaints/resources/complaints")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        Gson gComplaints = new Gson();
        Complaint[] allComplaints = gComplaints.fromJson(sComplaints, Complaint[].class);

        Arrays.stream(allComplaints).forEach(System.out::println);

        System.out.println("\nFind complaint: ");

        String sComplaint = client.target("http://localhost:8080/Complaints/resources/complaints/2")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        Gson gComplaint = new Gson();
        Complaint foundComplaint = gComplaint.fromJson(sComplaint, Complaint.class);

        System.out.println(foundComplaint);

        System.out.println("\nChange status of found complaint: ");
        foundComplaint.setStatus("closed");

        Response response = client
                .target("http://localhost:8080/Complaints/resources/complaints")
                .path(foundComplaint.getId().toString())
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(foundComplaint, MediaType.APPLICATION_JSON));

        System.out.println(response.getStatus() == Status.NO_CONTENT.getStatusCode()
                ? "Updated"
                : "Error");

        sComplaint = client.target("http://localhost:8080/Complaints/resources/complaints/2")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        gComplaint = new Gson();
        foundComplaint = gComplaint.fromJson(sComplaint, Complaint.class);

        System.out.println(foundComplaint);

        System.out.println("\nAll open complaints: ");

        String sOpenComplaints = client.target("http://localhost:8080/Complaints/resources/complaints")
                .queryParam("status", "open")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        Complaint[] allOpenComplaints = (new Gson()).fromJson(sOpenComplaints, Complaint[].class);

        Arrays.stream(allOpenComplaints).forEach(System.out::println);

        client.close();
    }

}
