package com.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.pojo.Person;
import com.pojo.Planet;

@Path("/users")
public class UserRestService {
    // http://localhost:8080/JaxRsProject/rest/users/trygetmethod/Tushar
    @GET
    @Path("trygetmethod/{name}")
    //@Produces(MediaType.TEXT_PLAIN)
    public Response getUserByName(@PathParam("name") String name) {
 
        return Response.status(200)
            .entity("getUserByName is called, name : " + name).build();
 
    }
    
    // http://localhost:8080/JaxRsProject/rest/users/plain-text
    @GET
    @Path("plain-text")
    // With MediaType.TEXT_PLAIN text is embedded inside <pre> tag of html.
    // Text in a <pre> element is displayed in a fixed-width font (usually Courier), and it preserves both spaces and line breaks.
    @Produces(MediaType.TEXT_PLAIN) 
    public String sayPlainTextHello() {
      return "Hello Jersey";
    }
    
    // http://localhost:8080/JaxRsProject/rest/users/xml
    @GET
    @Path("xml")
    @Produces(MediaType.TEXT_XML) // This will return xml output.If there is any misformatting in xml then it will complain on browser.
    public String sayXMLHello() {
      return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
    }

    // http://localhost:8080/JaxRsProject/rest/users/html   
    @GET
    @Path("html")
    // default MediaType for brower is text/html. To see the text version you can use  "curl http://localhost:8080/JaxRsProject/rest/users/html" in ternminal
    //@Produces(MediaType.TEXT_HTML)   
    public String sayHtmlHello() {
      return "<html> " + "<title>" + "Hello Jersey" + "</title>"
          + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
    }
    
    // http://localhost:8080/JaxRsProject/rest/users/trycontextannotation 
    // In general, @Context can be used to obtain contextual Java types related to the request or response.
    @GET
    @Path("trycontextannotation")
    public Response tryContextAnnotation(@Context HttpServletRequest request, @Context HttpServletResponse servletResponse, @Context UriInfo uriInfo, @Context HttpHeaders hh) {
        String path = uriInfo.getPath();
        System.out.println("path:"+path); // path:users/trycontextannotation
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        System.out.println("queryParams:"+queryParams); // queryParams:{name=[Tushar]}
        return Response.ok("tried context annotations").build();
        //return Response.status(400).build();
    }
    
    
    // http://localhost:8080/JaxRsProject/rest/users/trypostmethod1/Tushar
    @POST
    @Path("trypostmethod1/{name}")
    //@Consumes(MediaType.TEXT_PLAIN)
    public void tryPostMethod(@PathParam("name") String name, @Context HttpServletResponse servletResponse) throws IOException {
        // do some backend logic with person
        System.out.println(name);
        servletResponse.sendRedirect("http://localhost:8080/RESTfulExample/rest/users/html");
    }
    
    // http://localhost:8080/JaxRsProject/rest/users/trypostmethod2 
    @POST
    @Path("trypostmethod2")
    //@Consumes("text/plain")// HTTP POST doesn't support text/plain. You can use content types application/x-www-form-urlencoded or multipart/form-data with POST 
    public void postClichedMessage(String message) {
        System.out.println(message);
    }

    // http://localhost:8080/JaxRsProject/rest/users/testqueryparam?from=100&to=200&orderBy=age&orderBy=name
    // Example: http://www.mkyong.com/webservices/jax-rs/jax-rs-queryparam-example/
    /*
    http://docs.oracle.com/cd/E19776-01/820-4867/6nga7f5np/index.html
    
    @QueryParam and @PathParam can only be used on the following Java types:

    1. All primitive types except char
    
    2. All wrapper classes of primitive types except Character
    
    3. Have a constructor that accepts a single String argument
    
    4. Any class with the static method named valueOf(String) that accepts a single String argument
    
    5. Any class with a constructor that takes a single String as a parameter
    
    6. List<T>, Set<T>, or SortedSet<T>, where T matches the already listed criteria. Sometimes parameters may contain more than one value for the same name. If this is the case, these types may be used to obtain all values.
    
    If @DefaultValue is not used in conjunction with @QueryParam, and the query parameter is not present in the request, then value will be an empty collection for List, Set, or SortedSet; null for other object types; and the Java-defined default for primitive types.
    */
    @GET
    @Path("/testqueryparam")
    public Response getUsers(
        @DefaultValue("1000") @QueryParam("from") int from,
        @DefaultValue("999")@QueryParam("to") int to,
        @DefaultValue("name") @QueryParam("orderBy") List<String> orderBy) {
 
        return Response
           .status(200)
           .entity("getUsers is called, from : " + from + ", to : " + to
            + ", orderBy" + orderBy.toString()).build();
 
    }
    
    //JsonGetClient.java is created to call this method
    /*
        O/P:
        {
          "name" : "Tushar"
        }
     */
    // Example: http://www.mkyong.com/webservices/jax-rs/json-example-with-jersey-jackson/
    @GET
    @Path("/testgetjson")
    @Produces(MediaType.APPLICATION_JSON) // Jersey will use Jackson to handle the JSON conversion automatically.
    public Person getTrackInJSON() {
        Person person = new Person();
        person.setName("Tushar");
        return person;
 
    }
 
    //JsonPostClient.java is created to call this method
    // Example: http://www.mkyong.com/webservices/jax-rs/json-example-with-jersey-jackson/
    @POST
    @Path("/testpostjson")
    @Consumes(MediaType.APPLICATION_JSON) // Posted json string will be converted into “Person” object automatically using Jackson.
    public Response createTrackInJSON(Person person) {
        return Response.status(201).entity(person.toString()).build();
 
    }
    
    @GET
    @Path("/testgetxml")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public String createTrackInXML() {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
 
    }
    
    
    // Example: http://www.mkyong.com/webservices/jax-rs/jax-rs-formparam-example/
    // index.jsp calls this method
    @POST
    @Path("/testformparam")
    //@Consumes("application/x-www-form-urlencoded") // It think it is default for form parameters
    public Response addUser(
        @FormParam("name") String name, @FormParam("address") String address,
        @FormParam("age") int age) {
 
        return Response.status(200)
            .entity("addUser is called, name : " + name + ", age : " + age + ", address : " + address)
            .build();
 
    }
    
    // OR this is also possible for form parameters. You can retrieve it through a map.
    /*@POST
    @Consumes("application/x-www-form-urlencoded")
    public void post(MultivaluedMap<String, String> formParams) {
        // Store the message
    }*/

    //https://jersey.java.net/documentation/1.17/xml.html
    // run JaxBSupportClient.java
    @GET
    @Path("testjaxbget")
    public Planet getPlanet() {
        Planet p = new Planet();
        p.id = 1;
        p.name = "Earth";
        p.radius = 1.0;

        return p;
    }
    //https://jersey.java.net/documentation/1.17/xml.html
    // run JaxBSupportClient.java
    @GET
    @Path("testjaxbgetproducexml")
    @Produces(MediaType.APPLICATION_XML) // Planet has JaxB annotation, so it will convert Planet object to xml before returning to the client
    public Planet getPlanetInXml() {
        Planet p = new Planet();
        p.id = 1;
        p.name = "Earth";
        p.radius = 1.0;

        return p;
    }
    
    
    @POST
    @Path("testjaxbpostproducexml")
    @Produces(MediaType.APPLICATION_XML)// Jersey converts output Planet object into xml using JaxB
    @Consumes(MediaType.APPLICATION_XML)// Jersey converts input xml object into Planet using JaxB
    public Planet postPlanetInXml(Planet p1) {
        p1.id = 1;
        p1.name = "Earth";
        p1.radius = 1.0;

        return p1;
    }
}