package org.company.simple_rest.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.company.simple_rest.domain.SimpleBean;

@Path("/simple")
public class SimpleRest {
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public SimpleBean getBean(){
        return new SimpleBean("Simple","Get");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String[] getStringArray(){
        return new String[]{"hello", "rest"};
    }

    @GET
    @Path("/string")
    @Produces(MediaType.TEXT_PLAIN)
    public String getString(){
        return "hello";
    }
}
