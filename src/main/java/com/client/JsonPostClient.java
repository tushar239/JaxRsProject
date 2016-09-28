package com.client;

import javax.ws.rs.core.MediaType;

import com.pojo.Person;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

//Example: http://www.mkyong.com/webservices/jax-rs/restful-java-client-with-jersey-client/
public class JsonPostClient {
    public static void main(String[] args) {
        try {

            Client client = Client.create();

            // WebResource is an encapsulation of a Web resource capable of building requests
            // to send to the Web resource and processing responses returned from the Web resource.
            WebResource webResource = client.resource("http://localhost:8080/JaxRsProject/rest/users/testpostjson");

            Person person = new Person();
            person.setName("Tushar");
            //String input = createJsonString(person);
            String input = "{\"name\":\"Tushar\"}";

            ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, input);
            
            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }

            System.out.println("Output from Server .... \n");
            String output = response.getEntity(String.class);
            System.out.println(output);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    /*public static String createJsonString(Object object) throws IOException {
        Writer writer = new StringWriter();
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createJsonGenerator(writer);
        generator.setCodec(new ObjectMapper());
        generator.writeObject(object);
        generator.close();
        System.out.println("Json string " + writer.toString());
        return writer.toString();
       }*/
    
}

/*
O/P:

Output from Server .... 

Person [Name=Tushar]
*/