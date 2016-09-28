package com.client;

import com.pojo.Planet;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class JaxBSupportClient {

    // https://jersey.java.net/documentation/1.17/xml.html
    public static void main(String[] args) {
        try {

            Client client = Client.create();
            {
                WebResource webResource = client.resource("http://localhost:8080/JaxRsProject/rest/users/testjaxbget");

                Planet planet = webResource.get(Planet.class);

                System.out.println(planet.getName());
                // O/P: Earth
            }
            {
                WebResource webResource = client.resource("http://localhost:8080/JaxRsProject/rest/users/testjaxbgetproducexml");
                String planetInXmlwebResource = webResource.get(String.class);
                System.out.println(planetInXmlwebResource);
                //O/P: <?xml version="1.0" encoding="UTF-8" standalone="yes"?><planet><id>1</id><name>Earth</name><radius>1.0</radius></planet>

            }
            {
                WebResource webResource = client.resource("http://localhost:8080/JaxRsProject/rest/users/testjaxbpostproducexml");
                Planet planet = new Planet();
                planet.name="hi";
                
                String planetInXml = webResource.post(String.class, planet);
                System.out.println("planetInXml: "+planetInXml);// O/P: <?xml version="1.0" encoding="UTF-8" standalone="yes"?><planet><id>1</id><name>Earth</name><radius>1.0</radius></planet>
                Planet planetInXmlwebResource = webResource.post(Planet.class, planet);
                System.out.println(planetInXmlwebResource.getName());// O/P: Earth
            }
            

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
