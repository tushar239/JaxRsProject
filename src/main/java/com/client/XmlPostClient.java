package com.client;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import com.pojo.Person;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.thoughtworks.xstream.XStream;

public class XmlPostClient {
    public static void main(String[] args) {
        try {

            Client client = Client.create();

            WebResource webResource = client.resource("http://localhost:8080/JaxRsProject/rest/users/testgetxml");

            Person person = new Person();
            person.setName("Tushar");
            String input = createXmlString(person);

            String response = webResource.accept(MediaType.APPLICATION_XML).get(String.class);

           
            System.out.println(response);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
    public static String createXmlString(Object object) throws IOException {
        XStream xstream = new XStream();
        return xstream.toXML(object);
    }
    
}

/*
O/P:

Output from Server .... 

<?xml version="1.0"?><hello> Hello Jersey</hello>
*/