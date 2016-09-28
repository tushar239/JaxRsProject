package com.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


//Example: http://www.mkyong.com/webservices/jax-rs/restful-java-client-with-jersey-client/
public class JsonGetClient {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {

            Client client = Client.create();

            // WebResource is an encapsulation of a Web resource capable of building requests
            // to send to the Web resource and processing responses returned from the Web resource.
            WebResource webResource = client.resource("http://localhost:8080/JaxRsProject/rest/users/testgetjson");

            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            String output = response.getEntity(String.class);

            System.out.println("Output from Server .... \n");
            System.out.println(output);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}

/*
 O/P:
 Output from Server .... 

{"name":"Tushar"}
 */